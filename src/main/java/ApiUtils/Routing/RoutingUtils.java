package ApiUtils.Routing;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class RoutingUtils {
    private static final String PROP_ERROR_MSG = "ERROR_MSG";
    private static final String PROP_SUCCESS_MSG = "SUCCESS_MSG";
    private static final String PROP_EXCEPTION_DATA = "EXCEPTION_DATA";
    private static final String PROP_LIST_DATA = "LIST_DATA";
    private static final String PROP_OPERATION_TYPE = "OPERATION_TYPE";
    private static final String ROUTE_ERROR_PAGE = "error.jsp";

    public static void forward(String page, HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        RequestDispatcher rd = request.getRequestDispatcher(page);
        System.out.println(page);
        rd.forward(request, response);
    }

    public static void redirect(String page, HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        response.sendRedirect(page);
    }

    public static void handleException(Exception e, HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        request.setAttribute(PROP_EXCEPTION_DATA, e);
        RoutingUtils.forward(ROUTE_ERROR_PAGE, request, response);
        e.printStackTrace();
    }

    public static String getErrorMessage(String property, HttpServletRequest request) {
        String val = (String) request.getAttribute(property);
        if (val == null) {
            return "";
        } else {
            return val;
        }
    }

    public static String getMessage(String property, HttpServletRequest request) {
        String val = (String) request.getAttribute(property);
        if (val == null) {
            return "";
        } else {
            return val;
        }
    }

    public static void setErrorMessage(String msg, HttpServletRequest request) {
        request.setAttribute(PROP_ERROR_MSG, msg);
    }

    public static String getErrorMessage(HttpServletRequest request) {
        String val = (String) request.getAttribute(PROP_ERROR_MSG);
        if (val == null) {
            return "";
        } else {
            return val;
        }
    }

    public static void setSuccessMessage(String msg, HttpServletRequest request) {
        request.setAttribute(PROP_SUCCESS_MSG, msg);
    }

    public static String getSuccessMessage(HttpServletRequest request) {
        String val = (String) request.getAttribute(PROP_SUCCESS_MSG);
        if (val == null) {
            return "";
        } else {
            return val;
        }
    }

    public static String getParameter(String property, HttpServletRequest request) {
        String val = (String) request.getParameter(property);
        if (val == null) {
            return "";
        } else {
            return val;
        }
    }

    public static void setOperation(String msg, HttpServletRequest request) {
        request.setAttribute(PROP_OPERATION_TYPE, msg);
    }
    public static String getOperation(HttpServletRequest request) {
        String val = (String) request.getAttribute(PROP_OPERATION_TYPE);
        if (val == null) {
            return "";
        } else {
            return val;
        }
    }
}
