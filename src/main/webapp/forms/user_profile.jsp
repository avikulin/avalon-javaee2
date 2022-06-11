<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../master/header.jsp"%>
<div class="container">
    <div class="row vh-100">
        <div class="col-6 mx-auto">
            <div class="card" style="width: 18rem;">
                <img src="${userImageUrl}" class="card-img-top" alt="...">
                <div class="card-body">
                    <h5 class="card-title">${userName}</h5>
                    <p class="card-text">${userInfo}</p>
                    <a href="#" class="btn btn-primary" onclick="history.back()">Назад</a>
                </div>
            </div>
        </div>
    </div>
</div>
<%@include file="../master/footer.jsp"%>