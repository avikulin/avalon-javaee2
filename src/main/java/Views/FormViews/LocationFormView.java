package Views.FormViews;

import ViewModels.FormView.DTO.LocationReadDTO;
import ViewModels.FormView.LocationFormViewModel;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@WebServlet(name = "Locations", urlPatterns = "/locations")
public class LocationFormView extends HttpServlet {
    private final String DATA_OBJ_ATTR = "BEAN";

    @EJB
    LocationFormViewModel locationFormViewModel;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter("id");
        Objects.requireNonNull(idStr, "Id must be not null");
        Long id = Long.parseLong(idStr);
        LocationReadDTO data = locationFormViewModel.getData(id);
        req.setAttribute(DATA_OBJ_ATTR, data);
        req.getRequestDispatcher("/edit_location.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
