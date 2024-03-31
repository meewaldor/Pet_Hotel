<%-- 
    Document   : customer_list.jsp
    Created on : Jan 24, 2024, 3:39:34 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" type="text/css" href="css/customer-list.css" />

        <link rel="preconnect" href="https://fonts.googleapis.com" />
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
        <link
            href="https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,300;1,200&family=Quicksand:wght@500&display=swap"
            rel="stylesheet"
            />

    </head>
    <body onLoad="initPage()">

        <div class="container">
            <!--BODY  -->
            <jsp:include page="sidebar.jsp"/>
            <center class="body">
                <div class="header">
                    <h1>Services</h1>
                    <div class="account">
                        <a href=""><img src="" alt="" /></a>
                    </div>
                </div>
                <div class="tool">
                    <!--Search-->
                    <form action="ReadService" class="search">
                        <select name="type" id="selectedType" style="height: 25px; width: 100px;">
                            <option value="ID">ID</option>
                            <option value="Name">Name</option>
                        </select>
                        <input style="width: 30vw" type="text" name="searchValue" value="${param.searchValue}" placeholder="Search..."/>
                        <!--<input type="hidden" name="btAction" value="ReadServices"/>-->
                    </form>

                    <div>
                        <!--Add-->
                        <button onclick="displayAddForm()"><i class="fa fa-plus"></i> New</button>
                        <a href=""><button><i class="fa fa-sort"></i> Sort</button></a>
                        <!--Filter-->
                        <a href="ReadService"><button style="padding:2px 10px"><i class="fa-solid fa-list"></i> All</button></a>
                    </div>
                </div>
                <div class="table">
                    <c:set var="displayStyle" value="block" />
                    <c:if test="${requestScope.servicePriceList != null}">
                        <c:set var="displayStyle" value="grid" />
                    </c:if>
                    <div class="table-container" style="display: ${displayStyle}">
                        <div class="table-sub-container">
                            <table id="table">
                                <tr class="head">
                                    <th>ID</th>
                                    <th>Name</th>
                                    <th>Rate</th>
                                    <th>Description</th>
                                    <th>Status</th>
                                    <th></th>
                                    <th></th>
                                </tr>
                                <c:set var="serviceList" value="${requestScope.serviceList}"/>
                                <c:if test="${not empty serviceList}">
                                    <c:forEach items="${serviceList}" var="service" >
                                        <c:url var="detailLink" value="ReadServicePrice">
                                            <%--<c:param name="btAction" value="ReadServicePrice"/>--%>
                                            <c:param name="serviceId" value="${service.serviceId}"/>
                                            <c:if test="${not empty param.searchValue}">
                                                <c:param name="searchValue" value="${param.searchValue}"/>
                                            </c:if>
                                            <c:if test="${not empty param.type}">
                                                <c:param name="type" value="${param.type}"/>
                                            </c:if>
                                        </c:url>
                                        <tr style="cursor: pointer; background-color: ${service.serviceId == param.serviceId ? "#F3F4F5":"white"}" 
                                            onclick="window.location = '${detailLink}'">
                                            <td style="font-weight: bold">${service.serviceId}</td>
                                            <td style="font-weight: bold">${service.name}</td>
                                            <td>${service.rate}</td>
                                            <td>${service.description}</td>
                                            <td>${service.status ? "Enable" : "Disable"}</td>

                                            <!--Update-->
                                            <td style="text-align: center; cursor: pointer">
                                                <c:url var="updateLink" value="UpdateService">
                                                    <%--<c:param name="btAction" value="UpdateService"/>--%>
                                                    <c:param name="ServiceId" value="${service.serviceId}"/>
                                                    <c:if test="${not empty param.searchValue}">
                                                        <c:param name="searchValue" value="${param.searchValue}"/>
                                                        <c:param name="type" value="${param.type}"/>
                                                    </c:if>
                                                </c:url>
                                                <a href="${updateLink}"><i class="fa fa-solid fa-ellipsis-vertical" style="color: grey"></i></a>
                                            </td>

                                            <!--Delete-->
                                            <td style="text-align: center;">
                                                <a href="#" onclick="doDelete('${service.serviceId}', '${param.searchValue}', '${param.type}')">
                                                    <i class="fa fa-trash-o" style="color: grey"></i>
                                                </a>
                                            </td>
                                        </tr>                                 
                                    </c:forEach>
                                </c:if>
                                <c:if test="${empty serviceList}">
                                    <tr>
                                        <td colspan="10" style="text-align: center"><i>Empty List</i></td>
                                    </tr>
                                </c:if>
                            </table>
                        </div>
                        <c:set var="servicePriceList" value="${requestScope.servicePriceList}"/>
                        <c:if test="${servicePriceList != null}">
                            <div class="table-sub-container">
                                <table id="table2">
                                    <tr class="head">
                                        <th>Pet Type</th>
                                        <th>Pet Weight</th>
                                        <th>Price</th>
                                        <th>Status</th>
                                        <th></th>
                                    </tr>
                                    <c:set var="pre_weight" value="0"/>
                                    <c:set var="pre_type" value="0"/>
                                    <c:forEach items="${servicePriceList}" var="servicePrice" >
                                        <c:if test="${pre_type != servicePrice.type}">
                                            <c:set var="pre_weight" value="0"/>
                                        </c:if>
                                        <tr>
                                            <td>${servicePrice.type}</td>
                                            <td>${pre_weight} - ${servicePrice.weight}</td>
                                            <!--Update Service Price-->
                                            <td>
                                                <form action="CreateServicePrice">
                                                    <input type="hidden" name="serviceId" value="${servicePrice.serviceId}"/>
                                                    <input type="hidden" name="petType" value="${servicePrice.type}"/>
                                                    <input type="hidden" name="petWeight" value="${servicePrice.weight}"/>
                                                    <input type="number" name="price" value="${servicePrice.price}" required="" min="10000"/>
                                                    <c:if test="${not empty param.searchValue}">
                                                        <input type="hidden" name="searchValue" value="${param.searchValue}"/>
                                                        <input type="hidden" name="type" value="${param.type}"/>
                                                    </c:if>
                                                    <c:if test="${not empty param.typeFilter}">
                                                        <input type="hidden" name="typeFilter" value="${param.typeFilter}"/>
                                                    </c:if>
                                                </form>
                                            </td>
                                            <td>${servicePrice.status?"Enable":"Disable"}</td>

                                            <!--Delete service price-->
                                            <td style="text-align: center;">
                                                <a href="#" 
                                                   onclick="doDeletePrice('${servicePrice.serviceId}', '${servicePrice.type}', '${servicePrice.weight}',
                                                                   '${param.searchValue}', '${param.type}')">
                                                    <i class="fa fa-trash-o" style="color: grey"></i>
                                                </a>
                                            </td>

                                        </tr>
                                        <c:set var="pre_weight" value="${servicePrice.weight}"/>
                                        <c:set var="pre_type" value="${servicePrice.type}"/>

                                    </c:forEach>
                                    <!--Add a service price-->
                                    <form action="CreateServicePrice">
                                        <tr>
                                            <td>
                                                <select name="petType" style="height: 20px; width: 100px">
                                                    <option value="Cat">Cat</option>
                                                    <option value="Dog">Dog</option>
                                                </select>
                                            </td>
                                            <td><input type="number" name="petWeight"placeholder="Pet's Weight" min="1" step="0.5" required=""/></td>
                                            <td><input type="number" name="price"placeholder="Price" min="10000" required=""/></td>
                                            <td><input type='Submit' value='ADD'/></td>
                                            <td><input type="hidden" name="serviceId" value="${param.serviceId}"/></td>
                                        <input type="hidden" name="searchValue" value="${param.searchValue}"/>
                                        <input type="hidden" name="type" value="${param.type}"/>
                                        </tr>
                                    </form>

                                </table>
                            </div>
                        </c:if>

                    </div>                   
                    <div class="pagination-container">
                        <button onclick="previousPage()">&#9665;</button>
                        <span id="currentPage">1</span>
                        <button onclick="nextPage()">&#9655;</button>
                    </div>
                </div>

            </center>
            <jsp:include page="update_service.jsp"/>
            <jsp:include page="new_service.jsp"/>
        </div>
        <script type="text/javascript" src="js/paging.js"></script>
        <script type="text/javascript" src="js/ManageForm.js"></script>
        <script type="text/javascript">
                            function doDelete(id, searchValue, type) {
                                var url = ("DeleteService?serviceId=" + id)
                                if (searchValue !== null && searchValue !== "")
                                    url += ("&searchValue=" + searchValue + "&type=" + type);
                                if (confirm("Are you sure to delete service with ID = " + id)) {
                                    window.location = url;
                                }
                            }
                            function setType(paramType) {
                                var selectElement = document.getElementById("selectedType");
                                for (var i = 0; i < selectElement.options.length; i++) {
                                    if (selectElement.options[i].value === paramType) {
                                        selectElement.options[i].selected = true;
                                    }
                                }
                            }
                            function doDeletePrice(id, petType, weight, searchValue, type) {
                                var url = ("DeleteServicePrice?serviceId=" + id + "&petType=" + petType + "&weight=" + weight)
                                if (searchValue !== null && searchValue !== "")
                                    url += ("&searchValue=" + searchValue + "&type=" + type);
                                if (confirm("Are you sure to delete service with ID = " + id + "\nFor " + petType + " weight " + weight + " kg")) {
                                    window.location = url;
                                }
                            }
                            setType('${param.type}');
        </script>
    </body>
</html>
