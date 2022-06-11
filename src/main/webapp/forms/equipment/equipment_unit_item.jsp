<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../../master/header.jsp"%>

<form action="${pageContext.request.contextPath}/equipment_unit" method="post">
    <input type="hidden" name="equipmentId" value="${equipmentId}">

    <h2>
        <c:choose>
            <c:when test="${empty equipmentId}">Ввод</c:when>
            <c:otherwise>Редактирование</c:otherwise>
        </c:choose>  данных единицы оборудования</h2>

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
        <label for="locationId">Местоположение</label>
        <select id="locationId" name="locationId" class="form-control h-100 w-100 form-select">
            <option disabled <c:if test="${empty locationId}"> selected = "true"</c:if> value="">... Выберите значение ...</option>
            <c:forEach items="${LIST_LOCATIONS.entrySet()}" var="locationItem">
                <option value="${locationItem.getKey()}"
                        <c:if test="${locationItem.getKey() == locationId}">
                            selected = "true"
                        </c:if>
                >
                        ${locationItem.getValue()}
                </option>
            </c:forEach>
        </select>
    </div>

    <div class="form-group">
        <label for="equipmentCode">Код единицы оборудования</label>
        <input type="text" maxlength="50" class="form-control" id="equipmentCode" name="equipmentCode" aria-describedby="equipmentCodeHelp" placeholder="Внутренний код" value="${equipmentCode}">
        <small id="locationNameHelp" class="form-text text-muted">Введите внутренний учетный код единицы оборудования</small>
    </div>

    <div class="form-group">
        <label for="ipAddress">Сетевой адрес доступа</label>
        <input type="text" maxlength="15" class="form-control" id="ipAddress" name="ipAddress" aria-describedby="ipAddressHelp" placeholder="IP-адрес" value="${ipAddress}">
        <small id="ipAddressHelp" class="form-text text-muted">Введите ip-адрес административного интерфейса управления</small>
    </div>

    <div class="form-group">
        <label for="modelId">Модель оборудования</label>
        <select id="modelId" name="modelId" class="form-control h-100 w-100 form-select">
            <option disabled <c:if test="${empty modelId}"> selected = "true"</c:if> value="">... Выберите значение ...</option>
            <c:forEach items="${LIST_MODELS.entrySet()}" var="modelItem">
                <option value="${modelItem.getKey()}"
                        <c:if test="${modelItem.getKey() == modelId}">
                            selected = "true"
                        </c:if>
                >
                        ${modelItem.getValue()}
                </option>
            </c:forEach>
        </select>
    </div>

    <div class="form-group">
        <label for="equipmentDesc">Описание единицы оборудования</label>
        <textarea type="text" rows ="5" maxlength="200" class="form-control" id="equipmentDesc"  name="equipmentDesc" aria-describedby="aequipmentDescHelp" placeholder="Описание предназначения данной единицы оборудования">${equipmentDesc}</textarea>
        <small id="equipmentDescelp" class="form-text text-muted">Введите дополнительную адресную информацию (если необходимо).</small>
    </div>

    <div class="d-flex">
        <div class="p-2">
            <button type="button" class="btn btn-secondary" onclick="history.back()">Закрыть</button>
        </div>
        <div class="p-2">
            <button type="submit" class="btn btn-primary">
                <c:choose><c:when test="${not empty equipmentId}">Сохранить</c:when>
                    <c:otherwise>Создать</c:otherwise>
                </c:choose>
            </button>
        </div>
        <c:if test="${not empty equipmentId}">
            <div class="ml-auto p-2">
                <button type="button"  class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#deleteItemDlg">
                    Удалить
                </button>
            </div>
        </c:if>
    </div>
</form>

<!-- Delete item popup -->
<c:if test="${not empty equipmentId}">
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
                    <p class="fs-1">Удалить местоположение <mark>"${equipmentId}"</mark> из базы данных?</p>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal" onclick="hideModal()">Закрыть</button>
                    <input type="hidden" name="equipmentId" value="${equipmentId}">
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
                url: "${pageContext.request.contextPath}/equipment_unit?id=${equipmentId}",
                type: "delete",
                success: function(data){
                    $(location).prop("href", "${pageContext.request.contextPath}/equipment_units")
                },
                error: function(){
                    $(location).prop("href", "${pageContext.request.contextPath}/errors/error500.jsp")
                }
            });
        }
    </script>
</c:if>
<%@include file="../../master/footer.jsp"%>
