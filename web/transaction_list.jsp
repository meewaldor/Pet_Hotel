<%-- 
    Document   : transaction_list
    Created on : Mar 22, 2024, 11:07:50 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
                    <h1>Transaction</h1>
                </div>
                <div class="tool">
                    <!--Search function-->
                    <form action="ReadTransaction" class="search">
                        <select id="selectedType" name="type">
                            <option value="transactionID">Transaction's ID</option>
                            <option value="orderID">Order's ID</option>
                        </select>
                        <input style="width: 30vw" type="text" name="searchValue" value="${param.searchValue}" placeholder="Search..."/>

                        <!--<input type="hidden" name="btAction" value="ReadBoardings"/>-->
                    </form>
                    <div>
                        <!--Get all-->
                        <a href="ReadTransaction"><button style="padding:2px 10px"><i class="fa-solid fa-list"></i> All</button></a>
                    </div>
                </div>
                <div class="table" style="height: 70vh;border-top: 1px solid rgb(199, 198, 198)">
                    <table id="table">
                        <tr class="head">
                            <th>Transaction ID</th>
                            <th>Order's ID</th>
                            <th>Customer's Name</th>
                            <th>Customer's Phone</th>
                            <th>Created Time</th>
                            <th>Value</th>
                        </tr>
                        <c:set var="list" value="${requestScope.transactionList}"/>
                        <c:if test="${not empty list}">
                            <c:forEach items="${requestScope.transactionList}" var="b">
                                <tr>
                                    <td style="font-weight: bold">${b.transactionID.toUpperCase()}</td>
                                    <td style="font-weight: bold">${b.order.orderId}</td>
                                    <td>${b.customer.firstName} ${b.customer.lastName}</td>

                                    <td>${b.customer.phone}</td>
                                    <td>${b.createdTime}</td>
                                    <td>${b.value}</td>
                                </tr>
                            </c:forEach>
                        </c:if>
                        <c:if test="${empty transactionList}">
                            <tr>
                                <td colspan="6" style="text-align: center"><i>Empty List</i></td>
                            </tr>
                        </c:if>
                    </table>
                </div>
                <div class="pagination-container">
                    <a href="ReadTransaction?page=${param.page - 1}"><button>&#9665;</button></a>
                    <span id="currentPage">${param.page}</span>
                    <a href="ReadTransaction?page=${param.page + 1}"><button>&#9655;</button></a>
                    
                </div>
            </center>
    </body>
    <script>
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
</html>
