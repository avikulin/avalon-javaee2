<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../../master/header.jsp"%>

<div class="card">
    <div class="card-body">
        <form action="${pageContext.request.contextPath}/location" method="post">
            <input type="hidden" name="locationId" value="${locationId}">
            <c:set var="main_form" value="location" scope="request"/>
            <h2>
                <c:choose>
                    <c:when test="${empty locationId}">Ввод</c:when>
                    <c:otherwise>Редактирование</c:otherwise>
                </c:choose>  данных расположения</h2>

            <div class="alert alert-danger" role="alert"  <%    List<String> err = (List<String>) request.getAttribute("LIST_ERRORS");
                                                                if (err == null || err.isEmpty()) {%>
                                                                style="display: none"
                                                          <%}%> >
                <p>В ходе выполнения операции возникли ошибки:</p>
                <c:forEach items="${LIST_ERRORS}" var="errMsg" varStatus="counter">
                <p>${counter.count}) ${errMsg}.</p>
                </c:forEach>
            </div>
        <c:if test="${not empty locationId}">
            <ul class="nav nav-tabs" id="mainTabs" role="tablist">
                <li class="nav-item" role="location">
                    <button class="nav-link active" id="location-tab-tag" data-bs-toggle="tab" data-bs-target="#location-tab" type="button" role="tab" aria-controls="location-tab" aria-selected="true">Местоположение</button>
                </li>
                <li class="nav-item" role="equipment">
                    <button class="nav-link" id="equipment-tab-tag" data-bs-toggle="tab" data-bs-target="#equipment-tab" type="button" role="tab" aria-controls="equipment-tab" aria-selected="false">Единица оборудования</button>
                </li>
            </ul>

            <div class="tab-content" id="myTabContent">
                <div class="tab-pane fade show active" id="location-tab" role="tabpanel" aria-labelledby="location">
        </c:if>
                    <div class="form-group">
                        <label for="locationName">Название местоположения</label>
                        <input type="text" maxlength="50" class="form-control" id="locationName" name="locationName" aria-describedby="locationNameHelp" placeholder="Название местоположения" value="${locationName}">
                        <small id="locationNameHelp" class="form-text text-muted">Введите название местоположения</small>
                    </div>

                    <div class="form-group">
                        <label for="organizationId">Организация</label>
                        <select id="organizationId" name="organizationId" class="form-control h-100 w-100 form-select" data-live-search="true">
                            <option disabled <c:if test="${empty organizationId}"> selected = "true"</c:if> value="">... Выберите значение ...</option>
                            <c:forEach items="${LIST_ORGANIZATIONS.entrySet()}" var="org">
                                <option value="${org.getKey()}"
                                        <c:if test="${org.getKey() == organizationId}">
                                            selected = "true"
                                        </c:if>
                                >
                                        ${org.getValue()}
                                </option>
                            </c:forEach>
                        </select>
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
                        <textarea type="text" rows ="5" maxlength="200" class="form-control" id="additionalInfoAddress"  name="additionalInfoAddress" aria-describedby="additionalAddressHelp" placeholder="Дополнительная адресная информация">${additionalInfoAddress}</textarea>
                        <small id="additionalAddressHelp" class="form-text text-muted">Введите дополнительную адресную информацию (если необходимо).</small>
                    </div>
            </div> <!-- ENT OF LOCATION TAB--->

        <c:if test="${not empty locationId}">
            <div class="tab-pane fade" id="equipment-tab" role="tabpanel" aria-labelledby=equipment-tab-tag">
                <p/>
                <table id="equipmentUnitsTable" class="table table-hover" style="width:100%">
                    <thead>
                    <tr>
                        <th>Код ЕО</th>
                        <th>Производитель</th>
                        <th>Модель</th>
                        <th>Уровень OSI</th>
                        <th>IP-адрес</th>
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
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div><!-- ENT OF EQUIPMENT TAB--->
            <script type="text/javascript">
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
        </c:if>

            <div class="d-flex">
                <div class="p-2">
                    <button type="button" class="btn btn-secondary" onclick="history.back()">Закрыть</button>
                </div>
                <div class="p-2">
                    <button type="submit" class="btn btn-primary">
                        <c:choose><c:when test="${not empty locationId}">Сохранить</c:when>
                        <c:otherwise>Создать</c:otherwise>
                        </c:choose>
                    </button>
                </div>
                <c:if test="${not empty locationId}">
                    <div class="ml-auto p-2">
                        <button type="button"  class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#deleteItemDlg">
                            Удалить
                        </button>
                    </div>
                </c:if>
            </div>
        <c:if test="${not empty locationId}">
        </div><!-- ENT OF MAINT TAB--->
        </c:if>

        </form>

        <!-- Delete item popup -->
        <c:if test="${not empty locationId}">
            <div class="modal" id="deleteItemDlg" tabindex="-1" role="dialog" aria-labelledby="deleteItemDlgLabel" aria-hidden="true">
                <div class="modal-dialog modal-dialog-centered" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="deleteItemDlgLabel">Удаление элемента</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close" onclick="hideModal()">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <p class="fs-1">Удалить местоположение <mark>"${locationName}"</mark> из базы данных?</p>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal" onclick="hideModal()">Закрыть</button>
                                <input type="hidden" name="locationId" value="${locationId}">
                                <button type="submit" class="btn btn-danger" onclick="performDelete()">Удалить</button>
                        </div>
                    </div>
                </div>
            </div>
            <script type="text/javascript">
                function hideModal() {
                    $("#deleteItemDlg").modal('hide');
                }

                function performDelete() {
                    $.ajax({
                        url: "${pageContext.request.contextPath}/location?id=${locationId}",
                        type: "delete",
                        success: function(data){
                            $(location).prop("href", "${pageContext.request.contextPath}/locations")
                        },
                        error: function(){
                            $(location).prop("href", "${pageContext.request.contextPath}/errors/error500.jsp")
                        }
                    });
                }
            </script>
        </c:if>
    </div>
</div>
<%@include file="../../master/footer.jsp"%>