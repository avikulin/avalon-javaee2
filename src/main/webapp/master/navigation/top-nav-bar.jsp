<%@ page import="java.security.Principal" %>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <button type="button" id="sidebarCollapse" class="btn btn-primary">
            <i class="fa fa-bars"></i>
            <span class="sr-only">Toggle Menu</span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <p class="lead" style="margin-left: 1em; margin-bottom: 0;">Система учета телекоммуникационного оборудования</p>
        </div>
        <div class="nav navbar-nav navbar-right">
            <div class="dropdown">
                <a href="#" class="d-flex align-items-center justify-content-center link-dark text-decoration-none dropdown-toggle" id="dropdownUser" data-bs-toggle="dropdown" aria-expanded="false">
                    <i class="bi-person-square" style="font-circle: bold; font-size: 1.8rem"></i>
                    <p class="container-fluid" style="margin-bottom: 0px"> <%= getName(request) %> </p>
                </a>
                <ul class="dropdown-menu text-small shadow" aria-labelledby="dropdownUser">
                    <li><a class="dropdown-item" href="${pageContext.request.contextPath}/user_info">Профиль</a></li>
                    <li><hr class="dropdown-divider"></li>
                    <li><a class="dropdown-item" href="${pageContext.request.contextPath}/logout">Выход</a></li>
                </ul>
            </div>
        </div>
    </div>
</nav>
<%!
    private String getName(HttpServletRequest request) {
        Principal user = request.getUserPrincipal();
        if (user == null) return "<Гость>";
        String name = user.getName();
        return name;
    }
%>
