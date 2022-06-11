package Views.TableViews.Location;

import Common.Exceptions.DataValidationException;
import DAL.Utils.Filter.Contracts.FilterExpression;
import DAL.Utils.Filter.FilterBuilder;
import DAL.Utils.Filter.FilterBuilderImpl;
import ViewModels.TableViewModels.Location.DTO.LocationTableFilterDTO;
import ViewModels.TableViewModels.Location.DTO.LocationTableRowDTO;
import ViewModels.TableViewModels.Location.LocationTableViewModel;

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


@WebServlet(name = "LocationsTable", urlPatterns = "/locations")
@ServletSecurity(@HttpConstraint(rolesAllowed = { "Operator" }))
public class LocationsTableView extends HttpServlet {
    private static final String DATA_ERRORS = "LIST_ERRORS";
    private static final String DATA_ORG_LIST = "LIST_ORGANIZATIONS";
    private static final String DATA_CITIES_LIST = "LIST_CITIES";
    private static final String DATA_LOCATIONS_TABLE = "TABLE_LOCATIONS";

    private static final String MODEL_FIELD_ORGANIZATION_ID = "organizationId";
    private static final String MODEL_FIELD_CITY_NAME = "locCity";

    @EJB
    LocationTableViewModel tableViewModel;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        LocationTableFilterDTO filterDTO = new LocationTableFilterDTO();
        List<String> errors = new ArrayList<>(filterDTO.getErrorsList());

        try {
            filterDTO.fillFrom(req);
            FilterBuilder filterBuilder = new FilterBuilderImpl(tableViewModel.getFilterDef());
            filterBuilder.setScalarCriteriaOrder(Arrays.asList(
                                                                "locStreet",
                                                                "locHouseNumber",
                                                                "locBuilding",
                                                                "locApartmentNumber"
                    )
            );
            Map<String,String> refValues = new HashMap<>();
            refValues.put(MODEL_FIELD_ORGANIZATION_ID, filterDTO.getOrganizationId());
            refValues.put(MODEL_FIELD_CITY_NAME, filterDTO.getCityName());
            FilterExpression[] filterExpr = filterBuilder.build(refValues, filterDTO.getFilterString());
            List<LocationTableRowDTO> tableRows = this.tableViewModel.getData(filterExpr);
            req.setAttribute(DATA_LOCATIONS_TABLE, tableRows);
        } catch (DataValidationException dve) {
            errors.addAll(filterDTO.getErrorsList());
            errors.add(dve.getMessage());
            req.setAttribute(DATA_ERRORS, errors);
        }
        filterDTO.transferTo(req);
        Map<Long, String> orgList = tableViewModel.getOrganizationsList();
        req.setAttribute(DATA_ORG_LIST, orgList);

        Map<String, String> citiesList = tableViewModel.getCitiesList();
        req.setAttribute(DATA_CITIES_LIST, citiesList);

        req.getRequestDispatcher("forms/location/locations.jsp").forward(req, resp);
        return;
    }
}
