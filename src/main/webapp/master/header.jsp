<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!doctype html>
<html lang="en">
<head>
    <c:set var="baseURL" value="http://${pageContext.request.serverName}:${pageContext.request.serverPort}" />

    <title>Avalon Telecom</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link href="https://fonts.googleapis.com/css?family=Poppins:300,400,500,600,700,800,900" rel="stylesheet">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">
    <link rel="stylesheet" href="${baseURL}/static/css/style.css">
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/v/bs5/dt-1.12.1/fh-3.2.3/datatables.min.css"/>
    <link rel="icon" href="/docs/4.0/assets/img/favicons/favicon.ico">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="${baseURL}/static/frameworks/bootstrap/js/bootstrap.bundle.min.js"></script>
    <script type="text/javascript" src="https://cdn.datatables.net/v/bs5/dt-1.12.1/fh-3.2.3/datatables.min.js"></script>
</head>
<body>
<div class="wrapper d-flex align-items-stretch">
    <%@ include file="navigation/left-nav-bar.jsp"%>
    <div id="content" class="p-4">
        <%@ include file="navigation/top-nav-bar.jsp"%>
