<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../master/header.jsp"%>
<div class="d-flex align-items-center justify-content-center vh-75">
    <div class="text-center">
        <h1 class="display-1 fw-bold">404</h1>
        <p class="fs-3"> <span class="text-danger">Ошибка!</span> Запрошенные Вами данные отсутствуют в системе.</p>
        <p class="lead">
        <c:if test="${not empty LIST_ERRORS}">
            <p class="text-left">Причины появления данной страницы:</p>
            <c:forEach items="${LIST_ERRORS}" var="errMsg" varStatus="counter">
                <p>${counter.count}) ${errMsg}.</p>
            </c:forEach>
            </p>
        </c:if>
        <a href="#" class="btn btn-primary" onclick="history.back()">Вернуться</a>
    </div>
</div>
<%@include file="../master/footer.jsp"%>
