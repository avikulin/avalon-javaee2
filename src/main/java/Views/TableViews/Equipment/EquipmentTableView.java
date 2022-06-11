package Views.TableViews.Equipment;

import Common.Exceptions.DataValidationException;
import DAL.Utils.Filter.Contracts.FilterExpression;
import DAL.Utils.Filter.FilterBuilder;
import DAL.Utils.Filter.FilterBuilderImpl;
import ViewModels.TableViewModels.Equipment.DTO.EquipmentFilterDTO;
import ViewModels.TableViewModels.Equipment.DTO.EquipmentTableRowDTO;
import ViewModels.TableViewModels.Equipment.EquipmentTableViewModel;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@WebServlet(name = "EquipmentUnits", urlPatterns = "/equipment_units")
@ServletSecurity(@HttpConstraint(rolesAllowed = { "Operator" }))
public class EquipmentTableView extends HttpServlet {
    private static final String DATA_ERRORS = "LIST_ERRORS";
    private static final String DATA_ORG_LIST = "LIST_ORGANIZATIONS";
    private static final String DATA_LOCATIONS_LIST = "LIST_ORG_LOCATIONS";
    private static final String DATA_VENDORS_LIST = "LIST_VENDORS";
    private static final String DATA_MODELS_LIST = "LIST_MODELS";
    private static final String DATA_OSI_LAYERS_LIST = "LIST_OSI_LAYERS";
    private static final String DATA_EQUIPMENT_TABLE = "TABLE_EQUIPMENT_UNITS";

    private static final String VENDOR_ID = "vendorId";
    private static final String MODEL_ID = "modelId";
    public static final String ORGANIZATION_ID = "organizationId";
    private static final String LOCATION_ID = "locationId";
    private static final String OSI_LAYER_ID = "layer";

    @EJB
    private EquipmentTableViewModel tableViewModel;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        EquipmentFilterDTO filterDTO = new EquipmentFilterDTO();
        List<String> errors = new ArrayList<>(filterDTO.getErrorsList());

        try {
            filterDTO.fillFrom(req);
            FilterBuilder filterBuilder = new FilterBuilderImpl(tableViewModel.getFilterDef());
            filterBuilder.setScalarCriteriaOrder(Arrays.asList(
                    "networkAddress",
                    "unitCode",
                    "description"
                    )
            );
            Map<String,String> refValues = new HashMap<>();
            refValues.put(ORGANIZATION_ID, filterDTO.getOrganizationId());
            refValues.put(LOCATION_ID, filterDTO.getLocationId());
            refValues.put(VENDOR_ID, filterDTO.getVendorId());
            refValues.put(MODEL_ID, filterDTO.getModelId());
            refValues.put(OSI_LAYER_ID, filterDTO.getOsiLayerId());
            FilterExpression[] filterExpr = filterBuilder.build(refValues, filterDTO.getFilterString());
            List<EquipmentTableRowDTO> tableRows = this.tableViewModel.getData(filterExpr);
            req.setAttribute(DATA_EQUIPMENT_TABLE, tableRows);
        } catch (DataValidationException dve) {
            errors.addAll(filterDTO.getErrorsList());
            errors.add(dve.getMessage());
            req.setAttribute(DATA_ERRORS, errors);
        }
        filterDTO.transferTo(req);

        Map<Long, String> orgList = tableViewModel.getOrganizationsList();
        req.setAttribute(DATA_ORG_LIST, orgList);

        Map<Long, String> locList = tableViewModel.getLocationsList();
        req.setAttribute(DATA_LOCATIONS_LIST,locList);

        Map<Long, String> vendorsList = tableViewModel.getVendorsList();
        req.setAttribute(DATA_VENDORS_LIST,vendorsList);

        Map<String, String> modelsList = tableViewModel.getModelsList();
        req.setAttribute(DATA_MODELS_LIST,modelsList);

        Map<String, String> layersList = tableViewModel.getOsiLayers();
        req.setAttribute(DATA_OSI_LAYERS_LIST,layersList);

        req.getRequestDispatcher("forms/equipment/equipment_units.jsp").forward(req, resp);
        return;
    }
}
