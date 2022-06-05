package Views.FormViews.Location;

import Common.Exceptions.DataValidationException;
import ViewModels.FormView.DTO.LocationReadDTO;
import ViewModels.FormView.LocationFormViewModel;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@WebServlet(name = "Locations", urlPatterns = "/location")
public class LocationFormView extends HttpServlet {
    private final String DATA_ERRORS = "LIST_ERRORS";
    private final String DATA_ORG_LIST = "LIST_ORGANIZATIONS";
    private final String DATA_OBJECT = "FORM";

    @EJB
    LocationFormViewModel locationFormViewModel;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter("id");
        LocationReadDTO data;
        if (idStr != null) {
            Long id = Long.parseLong(idStr);
            data = locationFormViewModel.getData(id);
        } else {
            data = new LocationReadDTO();
        }

        Map<Long, String> orgList = locationFormViewModel.getOrganizationsList();
        req.setAttribute(DATA_ORG_LIST, orgList);
        req.setAttribute(DATA_OBJECT, data);
        req.getRequestDispatcher("/location_item.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        LocationReadDTO locationReadDTO = new LocationReadDTO();
        try {
            locationReadDTO.fillFrom(req);
            locationFormViewModel.putData(locationReadDTO);
        } catch (DataValidationException dve) {
            List<String> errors = new ArrayList<>(locationReadDTO.getErrorsList());
            errors.add(dve.getMessage());
            locationReadDTO.transferTo(req);
            Map<Long, String> orgList = locationFormViewModel.getOrganizationsList();
            req.setAttribute(DATA_ORG_LIST, orgList);
            req.setAttribute(DATA_ERRORS, errors);
            req.getRequestDispatcher("/location_item.jsp").forward(req, resp);
            return;
        }
        resp.sendRedirect("/locations.jsp");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }
}
