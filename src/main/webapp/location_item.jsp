<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="master/header.jsp"%>

<form action="${pageContext.request.contextPath}/location" method="post">
    <h2>Ввод данных расположения</h2>

    <input type="hidden" id="locationId" name="id" value="${FORM.getId()}">
    <input type="hidden" id="clientId">

    <div class="alert alert-danger" role="alert"  <%    List<String> err = (List<String>) request.getAttribute("LIST_ERRORS");
                                                        if (err == null || err.isEmpty()) {%>
                                                        style="display: none"
                                                  <%}%>
        <p>В ходе выполнения операции возникли ошибки</p>
        <c:forEach items="${LIST_ERRORS}" var="errMsg" varStatus="counter">
        <p>${counter.count}) ${errMsg}.</p>
        </c:forEach>
    </div>

    <div class="form-group">
        <label for="locationName">Название местоположения</label>
        <input type="text" maxlength="50" class="form-control" id="locationName" name="locationName" aria-describedby="locationNameHelp" placeholder="Название местоположения" value="${locationName}">
        <small id="locationNameHelp" class="form-text text-muted">Введите название местоположения</small>
    </div>

    <div class="form-group col-md-4">
        <div class="form-group">
        <label for="orgName">Организация</label>
        <select id="orgName" name="organizationId" class="form-control h-100 w-100" data-live-search="true">
            <c:forEach items="${LIST_ORGANIZATIONS.entrySet()}" var="org">
                <option data-tokens="${org.getKey()}" <c:if test="${org.getKey()} == ${organizationId}">selected</c:if> >
                        ${org.getValue()}
                </option>
            </c:forEach>
        </select>
        </div>
    </div>

    <div class="form-group">
        <label for="cityAddress">Населенный пункт</label>
        <input type="text" maxlength="100" class="form-control" id="cityAddress" name="cityAddress" aria-describedby="cityAddressHelp" placeholder="Населенный пункт" value="${cityAddress}">
        <small id="cityAddressHelp" class="form-text text-muted">Введите название населенного пункта.</small>
    </div>

    <div class="form-row">
        <div class="form-group col-md-6">
            <div class="form-group">
                <label for="streetAddress">Название улицы</label>
                <input type="text" maxlength="100" class="form-control" id="streetAddress" name="streetAddress" aria-describedby="streetAddressHelp" placeholder="Название улицы" value="${streetAddress}">
                <small id="streetAddressHelp" class="form-text text-muted">Введите название улицы.</small>
            </div>
        </div>

        <div class="form-group col-md-2">
            <div class="form-group">
                <label for="houseNumberAddress">Номер дома</label>
                <input type="number" class="form-control" id="houseNumberAddress" name="houseNumberAddress" aria-describedby="homeNumberAddressHelp" placeholder="Номер дома" value="${houseNumberAddress}">
                <small id="houseNumberAddressHelp" class="form-text text-muted">Введите номер дома.</small>
            </div>
        </div>

        <div class="form-group col-md-2">
            <div class="form-group">
                <label for="buildingNumberAddress">Номер строения (корпуса)</label>
                <input type="number" class="form-control" id="buildingNumberAddress" name="buildingNumberAddress" aria-describedby="buildingAddressHelp" placeholder="Номер строения (корпуса)" value="${buildingNumberAddress}">
                <small id="buildingAddressHelp" class="form-text text-muted">Введите номер строения (корпуса).</small>
            </div>
        </div>
        <div class="form-group col-md-2">
            <div class="form-group">
                <label for="apartmentNumberAddress">Номер квартиры</label>
                <input type="number" class="form-control" id="apartmentNumberAddress" name ="apartmentNumberAddress" aria-describedby="apartmentAddressHelp" placeholder="Номер квартиры" value="${apartmentNumberAddress}">
                <small id="apartmentAddressHelp" class="form-text text-muted">Введите номер квартиры.</small>
            </div>
        </div>
    </div>

    <div class="form-group">
        <label for="additionalInfoAddress">Дополнительная информация</label>
        <textarea type="text" rows ="5" maxlength="200" class="form-control" id="additionalInfoAddress"  name="additionalInfoAddress" aria-describedby="additionalAddressHelp" placeholder="Дополнительная адресная информация" value="${additionalInfoAddress}">
        </textarea>
        <small id="additionalAddressHelp" class="form-text text-muted">Введите дополнительную адресную информацию (если необходимо).</small>
    </div>

    <button type="submit" class="btn btn-primary">Сохранить</button>
</form>
<script type="javascript">
    $(function () {
        $('#orgName').selectpicker();
    });
</script>
<%@include file="master/footer.jsp"%>