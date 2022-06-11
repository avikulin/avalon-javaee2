<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../../master/header.jsp"%>

<h2>Перечень зарегистрированных единиц оборудования</h2>
<p>
<div class="d-flex">
    <div class="p-2">
        <button class="btn btn-primary" type="button" data-toggle="collapse" data-target="#filterMenu" aria-expanded="false" aria-controls="filterMenu" onclick="toggleFilters()">
            Настроить фильтр
        </button>
    </div>
    <div class="ml-auto p-2">
        <button class="btn btn-info" type="button" onclick="location.href='/equipment_unit'">
            Создать запись ЕО
        </button>
    </div>
</div>
</p>
<div class="collapse" id="filterMenu">
    <div class="card card-body">
        <form action="${pageContext.request.contextPath}/equipment_units" method="get">
            <div class="alert alert-danger" role="alert"  <%    List<String> err = (List<String>) request.getAttribute("LIST_ERRORS");
                if (err == null || err.isEmpty()) {%>
                 style="display: none"
                    <%}%> >
                <p>В ходе выполнения операции возникли ошибки:</p>
                <c:forEach items="${LIST_ERRORS}" var="errMsg" varStatus="counter">
                    <p>${counter.count}) ${errMsg}.</p>
                </c:forEach>
            </div>
            <div class="form-group">
                <label for="organizationId">Организация</label>
                <select id="organizationId" name="organizationId" class="form-control h-100 w-100 form-select">
                    <option <c:if test="${empty organizationId}"> selected = "true"</c:if> value=""></option>
                    <c:forEach items="${LIST_ORGANIZATIONS.entrySet()}" var="org">
                        <option value="${org.getKey()}"
                                <c:if test="${org.getKey() == organizationId}">
                                    selected = "true"
                                </c:if> >
                                ${org.getValue()}
                        </option>
                    </c:forEach>
                </select>
            </div>

            <div class="form-group">
                <label for="locationId">Местоположение</label>
                <select id="locationId" name="locationId" class="form-control h-100 w-100 form-select">
                    <option <c:if test="${empty locationId}"> selected = "true"</c:if> value=""></option>
                    <c:forEach items="${LIST_ORG_LOCATIONS.entrySet()}" var="locationEntry">
                        <option value="${locationEntry.getKey()}"
                                <c:if test="${locationEntry.getKey() == locationId}">
                                    selected = "true"
                                </c:if>
                        >
                                ${locationEntry.getValue()}
                        </option>
                    </c:forEach>
                </select>
            </div>

            <div class="form-group">
                <label for="vendorId">Производитель</label>
                <select id="vendorId" name="vendorId" class="form-control h-100 w-100 form-select">
                    <option <c:if test="${empty vendorId}"> selected = "true"</c:if> value=""></option>
                    <c:forEach items="${LIST_VENDORS.entrySet()}" var="vendorEntry">
                        <option value="${vendorEntry.getKey()}"
                                <c:if test="${vendorEntry.getKey() == vendorId}">
                                    selected = "true"
                                </c:if>
                        >
                                ${vendorEntry.getValue()}
                        </option>
                    </c:forEach>
                </select>
            </div>


            <div class="form-group">
                <label for="modelId">Модель оборудования</label>
                <select id="modelId" name="modelId" class="form-control h-100 w-100 form-select">
                    <option <c:if test="${empty modelId}"> selected = "true"</c:if> value=""></option>
                    <c:forEach items="${LIST_MODELS.entrySet()}" var="modelEntry">
                        <option value="${modelEntry.getKey()}"
                                <c:if test="${modelEntry.getKey() == modelId}">
                                    selected = "true"
                                </c:if>
                        >
                                ${modelEntry.getValue()}
                        </option>
                    </c:forEach>
                </select>
            </div>

            <div class="form-group">
                <label for="layer">Уровень сетевой модели</label>
                <select id="layer" name="layer" class="form-control h-100 w-100 form-select">
                    <option <c:if test="${empty layer}"> selected = "true"</c:if> value=""></option>
                    <c:forEach items="${LIST_OSI_LAYERS.entrySet()}" var="layerEntry">
                        <option value="${layerEntry.getKey()}"
                                <c:if test="${layerEntry.getKey() == layer}">
                                    selected = "true"
                                </c:if>
                        >
                                ${layerEntry.getValue()}
                        </option>
                    </c:forEach>
                </select>
            </div>

            <div class="form-group">
                <label for="searchString">Строка поиска (в порядке: IP-адрес, код ЕО, описание)</label>
                <input type="text" maxlength="100" class="form-control" id="searchString" name="searchString" aria-describedby="searchStringHelp" placeholder="IP-адрес, код ЕО, описание" value="${searchString}">
                <small id="locationNameHelp" class="form-text text-muted">Введите поисковые значения через запятую</small>
            </div>

            <div class="d-flex">
                <div class="p-2">
                    <button type="submit" class="btn btn-primary">Применить</button>
                </div>
            </div>
        </form>
    </div>
    <p></p>
</div>

<div class="card card-body">
    <table id="equipmentUnitsTable" class="table table-hover" style="width:100%">
        <thead>
        <tr>
            <th>Код ЕО</th>
            <th>Производитель</th>
            <th>Модель</th>
            <th>Уровень OSI</th>
            <th>IP-адрес</th>
            <th>Организация</th>
            <th>Местоположение</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${TABLE_EQUIPMENT_UNITS}" var="eq_unit">
            <tr>
                <td><a href="/equipment_unit?id=${eq_unit.getUnitId()}">${eq_unit.getUnitCode()}</a></td>
                <td>${eq_unit.getVendorName()}</td>
                <td>${eq_unit.getModelCode()}</td>
                <td>${eq_unit.getLayer()}</td>
                <td>${eq_unit.getNetworkAddress()}</td>
                <td>${eq_unit.getOrganization()}</td>
                <td>${eq_unit.getLocationName()}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<script type="text/javascript">
    var _filterPaneId = 'filterMenu';
    var _storageKey = 'equipment_units_filter_pane';

    function toggleFilters(){
        var filterPane = $('#'+ _filterPaneId);
        var command = filterPane.hasClass('show')?'hide':'show';
        localStorage.setItem(_storageKey, command);
        filterPane.collapse(command);
    }

    function restoreFiltersPaneState(){
        var last_cmd = localStorage.getItem(_storageKey);
        if (last_cmd === 'show'){
            $('#'+ _filterPaneId).addClass('show')
        }
    }

    $(document).ready(function () {
        //restoreFiltersPaneState();
        var table = $('#equipmentUnitsTable').DataTable({
            searching: false,
            language: {
                processing:    "Идет обработка...",
                search:        "Искать:",
                lengthMenu:    "Отображать по _MENU_ записей",
                info:          "Показано с _START_ по _END_ из _TOTAL_ записей",
                infoEmpty:     "Показано с _START_ по _END_ из _TOTAL_ записей",
                infoFiltered:  "(отфильтровано из _MAX_ записей)",
                infoPostFix:   "",
                loadingRecords:"Идет загрузка...",
                zeroRecords:   "Нет данных для отображения",
                emptyTable:    "Нет данных для отображения",
                paginate: {
                    first:     " ◀◀ ",
                    previous:  " ◀ ",
                    next:      " ▶ ",
                    last:      " ▶▶ "
                },
                aria: {
                    sortAscending:  ": Сортировать по возрастанию",
                    sortDescending: ": Сортировать по убыванию"
                }
            }
        });
    });
</script>
<p>
<div class="d-flex">
    <div class="p-2">
        <button type="button" class="btn btn-secondary" onclick="history.back()">Закрыть</button>
    </div>
</div>
</p>
<%@include file="../../master/footer.jsp"%>