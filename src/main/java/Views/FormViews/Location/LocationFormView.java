package Views.FormViews.Location;

import ApiUtils.Helpers.DataHelper;
import Common.Exceptions.DataValidationException;
import DAL.Utils.Filter.Contracts.FilterExpression;
import DAL.Utils.Filter.FilterBuilder;
import DAL.Utils.Filter.FilterBuilderImpl;
import ViewModels.FormViewModels.Location.DTO.LocationFormDTO;
import ViewModels.FormViewModels.Location.LocationFormViewModel;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "LocationItem", urlPatterns = "/location")
@ServletSecurity(@HttpConstraint(rolesAllowed = { "Operator" }))
public class LocationFormView extends HttpServlet {
    private final String DATA_ERRORS = "LIST_ERRORS";
    private final String DATA_ORG_LIST = "LIST_ORGANIZATIONS";
    private final String DATA_EQUIPMENT_TABLE = "TABLE_EQUIPMENT_UNITS";

    @EJB
    LocationFormViewModel locationFormViewModel;

    @EJB
    EquipmentTableViewModel equipmentTableViewModel;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String idStr = req.getParameter("id");

        LocationFormDTO data;
        List<EquipmentTableRowDTO> eqUnits = null;

        List<String> errors = new ArrayList<>();
        if (idStr != null) {
            try {
                Long id = DataHelper.getLong(idStr);
                data = locationFormViewModel.getData(id);

                FilterBuilder filterBuilder = new FilterBuilderImpl(equipmentTableViewModel.getFilterDef());
                Map<String, String> refValues = new HashMap<>();
                refValues.put("locationId", idStr);
                FilterExpression[] fltExpr = filterBuilder.build(refValues,"");
                eqUnits = equipmentTableViewModel.getData(fltExpr);
            }catch (DataValidationException dve){
                String msg = String.format("Ошибка в параметрах запроса по ИД. %s", dve.getMessage());
                errors.add(msg);
                req.setAttribute(DATA_ERRORS, errors);
                req.getRequestDispatcher("/errors/error404.jsp").forward(req, resp);
                data = new LocationFormDTO();
            }
        } else {
            data = new LocationFormDTO();
            eqUnits = new ArrayList<>();
        }

        Map<Long, String> orgList = locationFormViewModel.getOrganizationsList();
        req.setAttribute(DATA_ORG_LIST, orgList);
        req.setAttribute(DATA_EQUIPMENT_TABLE, eqUnits);
        req.setAttribute(DATA_ERRORS, errors);
        data.transferTo(req);

        req.getRequestDispatcher("forms/location/location_item.jsp").forward(req, resp);
        return;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        LocationFormDTO locationDTO = new LocationFormDTO();
        try {
            locationDTO.fillFrom(req);
            locationFormViewModel.putData(locationDTO);
        } catch (DataValidationException dve) {
            List<String> errors = new ArrayList<>(locationDTO.getErrorsList());
            errors.add(dve.getMessage());
            locationDTO.transferTo(req);
            Map<Long, String> orgList = locationFormViewModel.getOrganizationsList();
            req.setAttribute(DATA_ORG_LIST, orgList);
            req.setAttribute(DATA_ERRORS, errors);
            req.getRequestDispatcher("forms/location/location_item.jsp").forward(req, resp);
            return;
        }
        resp.sendRedirect("/locations");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter("id");
        List<String> errors = new ArrayList<>();
        if (idStr != null) {
            try {
                Long id = DataHelper.getLong(idStr);
                locationFormViewModel.deleteItem(id);
            }catch (DataValidationException dve){
                String msg = String.format("Ошибка в параметрах запроса по ИД. %s", dve.getMessage());
                errors.add(msg);
                req.setAttribute(DATA_ERRORS, errors);
                req.getRequestDispatcher("/errors/error404.jsp").forward(req, resp);
                return;
            }
        }
    }
}
