<%-- 
    Document   : order_list
    Created on : Feb 4, 2024, 8:32:41 PM
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
        <link rel="stylesheet" href="css/booking.css">

        <link rel="preconnect" href="https://fonts.googleapis.com" />
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
        <link
            href="https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,300;1,200&family=Quicksand:wght@500&display=swap"
            rel="stylesheet"
            />
        <style>
            .order-detail {
                width: 100%;
                padding: 10px;
                display: grid;
                grid-template-columns: 2fr 1fr;
                height: 150px;
            }
            .order-detail > p {
                justify-self: center;
            }
            .order-detail h1 {
                margin: 0;
                padding: 0;
                font-size: 17px;
                color: #72090f;
            }
            .content {
                display: none;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <!--BODY  -->
            <jsp:include page="sidebar.jsp"/>
            <center class="body">
                <div class="header">
                    <h1>Orders</h1>
                    <div class="account">
                        <a href=""><img src="" alt="" /></a>
                    </div>
                </div>
                <div class="tool">
                    <!--Search-->
                    <form action="ReadOrder" class="search">
                        <select id="selectedType" name="type" style="height: 25px; width: 100px;">
                            <option value="oID">Order's ID</option>
                            <option value="cusID">Customer's ID</option>
                            <option value="phone">Customer's Phone</option>
                        </select>
                        <input style="padding: 12px; width: 30vw" type="text" name="searchValue" value="${param.searchValue}" placeholder="Search..."/>
                        <!--<input type="hidden" name="btAction" value="ReadOrders"/>-->
                    </form>

                    <!--status filter-->
                    <form action="ReadOrder">
                        <select id="statusFilter" name="statusFilter" style="padding: 2px;">
                            <option value="All">All</option>
                            <option value="Pending">Pending</option>
                            <option value="Confirm">Confirm</option>
                            <option value="Complete">Complete</option>
                            <option value="Cancel">Cancel</option>
                        </select>
                        <input type="submit" value="Filter"/>
                        <!--<input type="hidden" name="btAction" value="StatusFilter"/>-->
                    </form>
                </div>
                <div class="table" style="height: 80vh;border-top: 1px solid rgb(199, 198, 198);overflow: scroll">
                    <table>
                        <tr class="head">
                            <th>Order's ID</th>
                            <th>Customer's ID</th>
                            <th>Created Time</th>
                            <th>Status</th>
                            <th>Total amount</th>
                            <th></th>
                            <th></th>
                        </tr>
                        <c:set var="orderList" value="${requestScope.orderList}"/>
                        <c:if test="${not empty orderList}">
                            <c:forEach items="${orderList}" var="order" >
                                <c:set var="status" value="${order.status}"/>
                                <tr  class="orderLine" style="border-bottom: 1px solid rgb(199, 198, 198);" > 
                                    <td style="font-weight: bold">${order.orderId}</td>
                                    <td style="font-weight: bold">${order.customer.customerID}</td>
                                    <td>${order.createdDate}</td>
                                    <td>${order.status}</td>
                                    <td>${order.total}</td>
                                    <td style="cursor:pointer" onclick="listen('${order.orderId}')"><i class="fa-solid fa-caret-down"></i></td>
                                    <c:if test="${order.status eq 'Pending'}">
                                        <td style="width: 50px">
                                            <form action="UpdateStatusOrder" method="post">
                                                <input type="hidden" name="OrderID" value="${order.orderId}"/>
                                                <input type="hidden" name="status" value="Confirm"/>
                                                <input type="hidden" name="searchValue" value="${param.searchValue}"/>
                                                <input type="hidden" name="type" value="${param.type}"/>
                                                <input type="hidden" name="statusFilter" value="${param.statusFilter}"/>
                                                <!--<input type="hidden" name="btAction" value="UpdateStatusOrder"/>-->
                                                <input type="submit" value="Confirm" style="cursor:pointer;border: none; padding:10px; background-color: #1170CF; color: white; width: 100%"/>
                                            </form>
                                        </td>
                                    </c:if>
                                    <c:if test="${order.status eq 'Confirm'}">
                                        <td style="width: 50px">
                                            <form action="UpdateStatusOrder" method="post">
                                                <input type="hidden" name="OrderID" value="${order.orderId}"/>
                                                <input type="hidden" name="status" value="Complete"/>
                                                <input type="hidden" name="searchValue" value="${param.searchValue}"/>
                                                <input type="hidden" name="type" value="${param.type}"/>
                                                <input type="hidden" name="statusFilter" value="${param.statusFilter}"/>
                                                <!--<input type="hidden" name="btAction" value="UpdateStatusOrder"/>-->
                                                <input type="submit" value="Complete" style="cursor:pointer;border: none; padding:10px; background-color: #E76A30; color: white; width: 100%"/>
                                            </form>
                                        </td>
                                    </c:if>
                                </tr>

                                <c:set var="orderBoardingDetail" value="${order.orderBoardingDetail}"/>
                                <c:if test="${not empty orderBoardingDetail}">
                                    <tr class="${order.orderId} content" style="border-bottom: 1px solid rgb(199, 198, 198);" style="display: none">
                                        <td colspan="5">
                                            <div class="order-detail">
                                                <div>
                                                    <h1>${orderBoardingDetail.room.boarding.name}</h1>
                                                    <p>Booked by: ${order.customer.firstName} ${order.customer.lastName}</p>
                                                    <p>Reserve for: ${orderBoardingDetail.pet.name} - ${orderBoardingDetail.pet.type} - ${orderBoardingDetail.pet.weight}</p>
                                                    <p>Check in: ${orderBoardingDetail.checkInDate} - Check out: ${orderBoardingDetail.checkOutDate}</p>
                                                </div>
                                                <p>${orderBoardingDetail.unitPrice}</p>
                                            </div>
                                        </td>
                                    </tr>
                                </c:if>

                            </c:forEach>
                        </c:if>
                        <c:if test="${empty orderList}">
                            <tr>
                                <td colspan="10" style="text-align: center"><i>Empty List</i></td>
                            </tr>
                        </c:if>
                    </table>
                </div>
<!--                <div class="pagination-container">
                    <button onclick="prevPage()">&#9665;</button>
                    <span id="currentPage">1</span>
                    <button onclick="nextPage()">&#9655;</button>
                </div>-->
            </center>
        </div>
        <!--<script type="text/javascript" src="js/paging.js"></script>-->
        <script>
                        function listen(orderId) {
                            var content = document.querySelector('.' + orderId);
                            if (content.style.display === 'none') {
                                content.style.display = 'table-row';
                            } else {
                                content.style.display = 'none';
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
                        function setLastFilter(lastFilter) {
                            var selectElement = document.getElementById("statusFilter");
                            for (var i = 0; i < selectElement.options.length; i++) {
                                if (selectElement.options[i].value === lastFilter) {
                                    selectElement.options[i].selected = true;
                                }
                            }
                        }
                        setLastFilter('${param.statusFilter}');
                        setType('${param.type}');
        </script>
    </body>
</html>
