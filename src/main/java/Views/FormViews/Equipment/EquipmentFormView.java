package Views.FormViews.Equipment;

import ApiUtils.Helpers.DataHelper;
import Common.Exceptions.DataValidationException;
import ViewModels.FormViewModels.Equpment.DTO.EquipmentFormDTO;
import ViewModels.FormViewModels.Equpment.EquipmentFormViewModel;

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
import java.util.List;
import java.util.Map;

@WebServlet(name = "equipment", urlPatterns = "/equipment_unit")
@ServletSecurity(@HttpConstraint(rolesAllowed = { "Operator" }))
public class EquipmentFormView extends HttpServlet {
    private final String DATA_ERRORS = "LIST_ERRORS";
    private final String DATA_LOCATIONS_LIST = "LIST_LOCATIONS";
    private final String DATA_MODELS_LIST = "LIST_MODELS";

    @EJB
    private EquipmentFormViewModel equipmentFormViewModel;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter("id");
        EquipmentFormDTO data;
        List<String> errors = new ArrayList<>();
        if (idStr != null) {
            try {
                Long id = DataHelper.getLong(idStr);
                data =equipmentFormViewModel.getData(id);
            }catch (DataValidationException dve){
                String msg = String.format("Ошибка в параметрах запроса по ИД. %s", dve.getMessage());
                errors.add(msg);
                req.setAttribute(DATA_ERRORS, errors);
                req.getRequestDispatcher("/errors/error404.jsp").forward(req, resp);
                data = new EquipmentFormDTO();
            }
        } else {
            data = new EquipmentFormDTO();
        }

        Map<String, String> modelsList = equipmentFormViewModel.getModelsList();
        req.setAttribute(DATA_MODELS_LIST, modelsList);

        Map<Long, String> locationsList = equipmentFormViewModel.getLocationsList();
        req.setAttribute(DATA_LOCATIONS_LIST, locationsList);

        req.setAttribute(DATA_ERRORS, errors);
        data.transferTo(req);
        req.getRequestDispatcher("forms/equipment/equipment_unit_item.jsp").forward(req, resp);
        return;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        EquipmentFormDTO equipmentFormDTO = new EquipmentFormDTO();
        try {
            equipmentFormDTO.fillFrom(req);
            this.equipmentFormViewModel.putData(equipmentFormDTO);
        } catch (DataValidationException dve) {
            List<String> errors = new ArrayList<>(equipmentFormDTO.getErrorsList());
            errors.add(dve.getMessage());
            equipmentFormDTO.transferTo(req);

            Map<String, String> modelsList = equipmentFormViewModel.getModelsList();
            req.setAttribute(DATA_MODELS_LIST, modelsList);

            Map<Long, String> locationsList = equipmentFormViewModel.getLocationsList();
            req.setAttribute(DATA_LOCATIONS_LIST, locationsList);

            req.setAttribute(DATA_ERRORS, errors);
            req.getRequestDispatcher("forms/equipment/equipment_unit_item.jsp").forward(req, resp);
            return;
        }
        resp.sendRedirect("/equipment_units");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter("id");
        List<String> errors = new ArrayList<>();
        if (idStr != null) {
            try {
                Long id = DataHelper.getLong(idStr);
                equipmentFormViewModel.deleteItem(id);
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
