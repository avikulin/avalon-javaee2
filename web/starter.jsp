<%--
  Created by IntelliJ IDEA.
  User: a.vikulin
  Date: 07.05.2022
  Time: 23:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <title>Sidebar 07</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link href="https://fonts.googleapis.com/css?family=Poppins:300,400,500,600,700,800,900" rel="stylesheet">

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">
    <link rel="stylesheet" href="static/css/style.css">
    <link rel="stylesheet" href="static/css/search-bar.css">

    <script src="static/js/jquery.min.js"></script>
    <script src="static/frameworks/bootstrap/js/bootstrap.bundle.min.js"></script>
<%--    <script src="static/js/bootstrap.min.js"></script>--%>


</head>
<body>

<div class="wrapper d-flex align-items-stretch">
    <nav id="sidebar" class="active">

        <ul class="list-unstyled components mb-5">
            <li class="active">
                <a href="#"><span class="fa fa-home"></span> Начало</a>
            </li>

            <li>
                <a href="#"><span class="fa fa-sticky-note"></span> Модели</a>
            </li>

            <li>
                <a href="#"><span class="fa fa-wifi"></span> Устройства</a>
            </li>

            <li>
                <a href="#"><span class="fa fa-user"></span> Клиенты</a>
            </li>

            <li>
                <a href="#"><span class="fa fa-paper-plane"></span> Контакты</a>
            </li>
        </ul>

        <div class="footer">
            <p>
                Copyright &copy;<script>document.write(new Date().getFullYear());</script> All rights reserved <i class="icon-heart" aria-hidden="true"></i></a>
            </p>
        </div>
    </nav>

    <!-- Page Content  -->
    <div id="content" class="p-4">

        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <div class="container-fluid">

                <button type="button" id="sidebarCollapse" class="btn btn-primary">
                    <i class="fa fa-bars"></i>
                    <span class="sr-only">Toggle Menu</span>
                </button>

                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item"><a href="#">Модели</a></li>
                        <li class="breadcrumb-item"><a href="#">Cisco</a></li>
                        <li class="breadcrumb-item active" aria-current="page">Cisco WPAN 500</li>
                    </ol>
                </div>
                <div class="nav navbar-nav navbar-right">
                    <div class="dropdown">
                        <a href="#" class="d-flex align-items-center justify-content-center link-dark text-decoration-none dropdown-toggle" id="dropdownUser" data-bs-toggle="dropdown" aria-expanded="false">
<%--                            <img src="https://github.com/mdo.png" alt="mdo" width="32" height="32" class="rounded-circle">--%>
                            <i class="bi-person-square" style="font-circle: bold; font-size: 1.8rem"></i>
                            <p class="container-fluid" style="margin-bottom: 0px"> UserName </p>
                        </a>

                        <ul class="dropdown-menu text-small shadow" aria-labelledby="dropdownUser">
                            <li><a class="dropdown-item" href="#">Профиль</a></li>
                            <li><hr class="dropdown-divider"></li>
                            <li><a class="dropdown-item" href="#">Выход</a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </nav>

        <h2 class="mb-4">Sidebar #07</h2>
        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>
        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>

    </div>
</div>
<%--<script src="static/js/popper.js"></script>--%>
<script src="static/js/main.js"></script>

</body>
</html>
