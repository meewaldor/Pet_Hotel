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
                    <h1>Boardings</h1>
                    <div class="account">
                        <a href=""><img src="" alt="" /></a>
                    </div>
                </div>
                <div class="tool">
                    <!--Search function-->
                    <form action="ReadBoarding" class="search">
                        <select id="selectedType" name="type">
                            <option value="ID">ID</option>
                            <option value="Name">Name</option>
                        </select>
                        <input style="width: 30vw" type="text" name="searchValue" value="${param.searchValue}" placeholder="Search..."/>

                        <!--<input type="hidden" name="btAction" value="ReadBoardings"/>-->
                    </form>
                    <div>
                        <!--Add function-->
                        <a href="#" onclick="displayAddForm()"><button><i class="fa fa-plus"></i> New</button></a>
                        <a href=""><button><i class="fa fa-sort"></i> Sort</button></a>
                        
                        <!--Get all-->
                        <a href="ReadBoarding"><button style="padding:2px 10px"><i class="fa-solid fa-list"></i> All</button></a>
                    </div>
                </div>
                <div class="table" style="height: 70vh;border-bottom: 1px solid rgb(199, 198, 198);">
                    <table id="table">
                        <tr class="head">
                            <th>Boarding ID</th>
                            <th>Name</th>
                            <th>Rate</th>
                            <th>Description</th>
                            <th>Length</th>
                            <th>Height</th>
                            <th>Width</th>
                            <th>Max Weight</th>
                            <th>Price(VND)</th>
                            <th>Status</th>
                            <th></th>
                            <th></th>
                        </tr>
                        <c:set var="boardingList" value="${requestScope.boardingList}"/>
                        <c:if test="${not empty boardingList}">
                            <c:forEach items="${requestScope.boardingList}" var="b">
                                <tr>
                                    <td style="font-weight: bold">${b.boardingId.toUpperCase()}</td>
                                    <td style="font-weight: bold">${b.name}</td>
                                    <td>${b.rate}</td>
                                    <td>
                                        <c:forEach items="${b.description}" var="line">
                                            ${line}<br/>
                                        </c:forEach> 
                                    </td>
                                    <td>${b.length}</td>
                                    <td>${b.height}</td>
                                    <td>${b.width}</td>
                                    <td>${b.maxWeight}</td>
                                    <td>${b.price}</td>
                                    <td>${b.status ? "Enable" : "Disable"}</td>

                                    <!--Delete function-->
                                    <td style="text-align: center; cursor: pointer">
                                        <a href="#" onclick="doDelete('${b.boardingId}', '${param.searchValue}', '${param.type}')">
                                            <i class="fa fa-trash-o" style="color: grey"></i>
                                        </a>
                                    </td>

                                    <!--Update function-->
                                    <td style="text-align: center; cursor: pointer">
                                        <c:url var="updateLink" value="UpdateBoarding">
                                            <%--<c:param name="btAction" value="UpdateBoarding"/>--%>
                                            <c:param name="BoardingId" value="${b.boardingId}"/>
                                            <c:if test="${not empty param.searchValue}">
                                                <c:param name="searchValue" value="${param.searchValue}"/>
                                                <c:param name="type" value="${param.type}"/>
                                            </c:if>
                                        </c:url>
                                        <a href="${updateLink}"><i class="fa fa-solid fa-ellipsis-vertical" style="color: grey"></i></a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </c:if>
                        <c:if test="${empty boardingList}">
                            <tr>
                                <td colspan="12" style="text-align: center"><i>Empty List</i></td>
                            </tr>
                        </c:if>
                    </table>
                </div>
                <div class="pagination-container">
                    <button onclick="previousPage()">&#9665;</button>
                    <span id="currentPage">1</span>
                    <button onclick="nextPage()">&#9655;</button>
                </div>
            </center>
            <jsp:include page="update_boarding.jsp"/>
            <jsp:include page="new_boarding.jsp"/>

        </div>
        <script type="text/javascript" src="js/paging.js"></script>
        <script type="text/javascript" src="js/ManageForm.js"></script>
        <script type="text/javascript">
                        function doDelete(id, searchValue, type) {
                            var url = ("DeleteBoarding?boardingId=" + id)
                            if (searchValue !== null && searchValue !== "")
                                url += ("&searchValue=" + searchValue + "&type=" + type);
                            if (confirm("Are you sure to delete boarding with ID = " + id)) {
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
                        setType('${param.type}');
        </script>
    </body>
</html>
