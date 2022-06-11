<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../../master/header.jsp"%>

<h2>Перечень зарегистрированных местоположений</h2>
<p>
<div class="d-flex">
    <div class="p-2">
        <button class="btn btn-primary" type="button" data-toggle="collapse" data-target="#filterMenu" aria-expanded="false" aria-controls="filterMenu" onclick="toggleFilters()">
            Настроить фильтр
        </button>
    </div>
    <div class="ml-auto p-2">
        <button class="btn btn-info" type="button" onclick="location.href='/location'">
            Создать расположение
        </button>
    </div>
</div>
</p>
<div class="collapse" id="filterMenu">
    <div class="card card-body">
        <form action="${pageContext.request.contextPath}/locations" method="get">
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
                <label for="organizationId">Населенный пункт</label>
                <select id="cityName" name="cityName" class="form-control h-100 w-100 form-select">
                    <option <c:if test="${empty cityName}"> selected = "true"</c:if> value=""></option>
                    <c:forEach items="${LIST_CITIES.entrySet()}" var="city">
                        <option value="${city.getKey()}"
                                <c:if test="${city.getKey() == cityName}">
                                    selected = "true"
                                </c:if>
                        >
                                ${city.getValue()}
                        </option>
                    </c:forEach>
                </select>
            </div>

            <div class="form-group">
                <label for="searchString">Строка поиска (в порядке: улица, дом, строение, квартира)</label>
                <input type="text" maxlength="100" class="form-control" id="searchString" name="searchString" aria-describedby="searchStringHelp" placeholder="Улица, Дом, Строение, Квартира" value="${searchString}">
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
    <table id="locationsTable" class="table table-hover" style="width:100%">
        <thead>
            <tr>
                <th>Название</th>
                <th>Организация</th>
                <th>Город</th>
                <th>Улица</th>
                <th>Дом</th>
                <th>Строение</th>
                <th>Квартира</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${TABLE_LOCATIONS}" var="loc">
                <tr>
                    <td><a href="/location?id=${loc.getId()}">${loc.getName()}</a></td>
                    <td>${loc.getOrganizationName()}</td>
                    <td>${loc.getLocCity()}</td>
                    <td>${loc.getLocStreet()}</td>
                    <td>${loc.getLocHouseNumber()}</td>
                    <td>${loc.getLocBuilding()}</td>
                    <td>${loc.getLocApartmentNumber()}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
<script type="text/javascript">
    var _filterPaneId = 'filterMenu';
    var _storageKey = 'locations_filter_pane';

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
        var table = $('#locationsTable').DataTable({
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