package ApiUtils.Servlets;

import DAL.Contracts.DataFactory.CrudDataFactory;
import DAL.Contracts.Repository.CrudRepository;
import DAL.DataEntities.Registers.User;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.util.Objects;

@WebServlet(name = "userInfo", urlPatterns = "/user_info")
public class UserInfo extends HttpServlet {
    @EJB
    private CrudDataFactory dataFactory;

    private String DATA_IMAGE_URL = "userImageUrl";
    private String DATA_FULL_NAME = "userName";
    private String DATA_USER_INFO = "userInfo";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Principal user = req.getUserPrincipal();
        if (user == null) return;
        Objects.requireNonNull(user,"No logged on users found");
        String name = user.getName();
        CrudRepository<User,String> userRepo = dataFactory.getUserRepo();
        User u =userRepo.getById(name);
        Objects.requireNonNull(user,"No user profile data found");
        req.setAttribute(DATA_FULL_NAME, u.getFullName());
        req.setAttribute(DATA_IMAGE_URL, u.getLogoUrl());
        req.setAttribute(DATA_USER_INFO, u.getDescription());
        req.getRequestDispatcher("forms/user_profile.jsp").forward(req, resp);
        return;
    }
}
