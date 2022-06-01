<%@ page import="java.util.List" %>
<%@ page import="ViewModels.ListView.DTO.Read.OrganizationListItemDTO" %>
<%@ page import="ViewModels.FormView.DTO.LocationReadDTO" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="master/header.jsp"%>

<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-select@1.14.0-beta3/dist/css/bootstrap-select.min.css">
<script src="https://cdn.jsdelivr.net/npm/bootstrap-select@1.14.0-beta3/dist/js/bootstrap-select.min.js"></script>

<form action="${pageContext.request.contextPath}/locations/item" method="post">
    <h2>Ввод данных расположения</h2>
    <input type="hidden" id="locationId">
    <input type="hidden" id="clientId">

    <div class="form-group">
        <label for="locationName">Название местоположения</label>
        <input type="text" class="form-control" id="locationName" name="locationName" aria-describedby="locationNameHelp" placeholder="Название местоположения">
        <small id="locationNameHelp" class="form-text text-muted">Введите название местоположения</small>
    </div>

    <div class="form-group col-md-4">
        <div class="form-group">
        <label for="orgName">Организация</label>
        <select id="orgName" name="orgName" class="form-control h-100 w-100" data-live-search="true" >
            <c:forEach items="${BEAN.getOrganizationList()}" var="org">
                <option data-tokens="${org.getId()}">${org.getName()}</option>
            </c:forEach>
        </select>
        </div>
    </div>

    <div class="form-group">
        <label for="cityAddress">Населенный пункт</label>
        <input type="text" maxlength="100" class="form-control" id="cityAddress" name="cityAddress" aria-describedby="cityAddressHelp" placeholder="Населенный пункт">
        <small id="cityAddressHelp" class="form-text text-muted">Введите название населенного пункта.</small>
    </div>

    <div class="form-row">
        <div class="form-group col-md-6">
            <div class="form-group">
                <label for="streetAddress">Название улицы</label>
                <input type="text" maxlength="100" class="form-control" id="streetAddress" name="streetAddress" aria-describedby="streetAddressHelp" placeholder="Название улицы">
                <small id="streetAddressHelp" class="form-text text-muted">Введите название улицы.</small>
            </div>
        </div>

        <div class="form-group col-md-2">
            <div class="form-group">
                <label for="houseNumberAddress">Номер дома</label>
                <input type="number" class="form-control" id="houseNumberAddress" name="houseNumberAddress" aria-describedby="homeNumberAddressHelp" placeholder="Номер дома">
                <small id="houseNumberAddressHelp" class="form-text text-muted">Введите номер дома.</small>
            </div>
        </div>

        <div class="form-group col-md-2">
            <div class="form-group">
                <label for="buildingAddress">Номер строения (корпуса)</label>
                <input type="number" class="form-control" id="buildingAddress" name="buildingAddress" aria-describedby="buildingAddressHelp" placeholder="Номер строения (корпуса)">
                <small id="buildingAddressHelp" class="form-text text-muted">Введите номер строения (корпуса).</small>
            </div>
        </div>
        <div class="form-group col-md-2">
            <div class="form-group">
                <label for="apartmentAddress">Номер квартиры</label>
                <input type="number" class="form-control" id="apartmentAddress" name ="apartmentAddress" aria-describedby="apartmentAddressHelp" placeholder="Номер квартиры">
                <small id="apartmentAddressHelp" class="form-text text-muted">Введите номер квартиры.</small>
            </div>
        </div>
    </div>

    <div class="form-group">
        <label for="additionalAddress">Дополнительная информация</label>
        <textarea type="text" rows ="5" maxlength="200" class="form-control" id="additionalAddress"  name="additionalAddress" aria-describedby="additionalAddressHelp" placeholder="Дополнительная адресная информация">
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