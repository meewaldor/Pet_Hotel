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
    <body>
        <div class="container">
            <!--BODY  -->
            <jsp:include page="sidebar.jsp"/>
            <center class="body">
                <div class="header">
                    <h1>Customers</h1>
                    <div class="account">
                        <a href=""><img src="" alt="" /></a>
                    </div>
                </div>
                <!--Search-->
                <div class="tool">
                    <form action="ReadCustomer" class="search">
                        <!--<input type="hidden" name="btAction" value="ReadCustomers"/>-->
                        <select id="selectedType" name="type">
                            <option value="ID">ID</option>
                            <option value="Name">Name</option>
                            <option value="Phone">Phone</option>
                            <option value="Email">Email</option>
                        </select>
                        <input style="width: 30vw" type="text" name="searchValue" value="${param.searchValue}" placeholder="Search..."/>


                    </form>
                    <div>
                        <a href=""><button><i class="fa fa-sort"></i> Sort</button></a>
                        <a href="ReadCustomer"><button style="padding:1px 10px"><i class="fa-solid fa-list"></i> All</button></a>
                    </div>
                </div>
                <div class="table" style="height: 70vh;border-bottom: 1px solid rgb(199, 198, 198);">
                    <table>
                        <tr class="head">
                            <th>Customer's ID</th>
                            <th>Full name</th>
                            <th>Email</th>
                            <th>Phone</th>
                            <th>Point</th>
                            <th>Member</th>
                            <th>Status</th>
                            <th></th>
                            <th></th>
                        </tr>
                        <c:set var="customerList" value="${requestScope.customerList}"/>
                        <c:if test="${not empty customerList}">
                            <c:forEach items="${customerList}" var="customer" >
                                <tr>
                                    <td style="font-weight: bold">${customer.customerID}</td>
                                    <td style="font-weight: bold">${customer.firstName} ${customer.lastName}</td>
                                    <td>${customer.email}</td>
                                    <td>${customer.phone}</td>
                                    <td>${customer.point}</td>
                                    <td>${customer.member}</td>
                                    <td>${customer.status ? "Enable" : "Disable"}</td>

                                    <!--Delete function-->
                                    <td style="text-align: center;">
                                        <a href="#" onclick="doDelete('${customer.customerID}', '${param.searchValue}', '${param.type}')">
                                            <i class="fa fa-trash-o" style="color: grey"></i>
                                        </a>
                                    </td>

                                    <!--Update function-->
                                    <td style="text-align: center; cursor: pointer">
                                        <c:url var="updateLink" value="UpdateCustomer">
                                            <%--<c:param name="btAction" value="UpdateCustomer"/>--%>
                                            <c:param name="CustomerId" value="${customer.customerID}"/>
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
                        <c:if test="${empty customerList}">
                            <tr>
                                <td colspan="10" style="text-align: center"><i>Empty List</i></td>
                            </tr>
                        </c:if>
                    </table>
                </div>
                <div class="pagination-container">
                    <button onclick="prevPage()">&#9665;</button>
                    <span id="currentPage">1</span>
                    <button onclick="nextPage()">&#9655;</button>
                </div>
            </center>
            <jsp:include page="update_customer.jsp"/>
        </div>

        <script type="text/javascript" src="js/paging.js"></script>
        <!--        <script type="text/javascript" src="js/ManageForm.js"></script>-->
        <script type="text/javascript">
                        function doDelete(id, searchValue, type) {
                            var url = ("DeleteCustomer?customerId=" + id)
                            if (searchValue !== null && searchValue !== "")
                                url += ("&searchValue=" + searchValue + "&type=" + type);
                            if (confirm("Are you sure to delete customer with ID = " + id)) {
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
                        function hideForm() {
                            if (document.getElementById("add-form") !== null)
                                document.getElementById("add-form").style.display = "none";
                            if (document.getElementById("update-form") !== null)
                                document.getElementById("update-form").style.display = "none";
                            document.getElementById("blur").style.display = "none";
                        }

        </script>
    </body>
</html>
