<%-- 
    Document   : booking_list
    Created on : Feb 4, 2024, 11:34:39 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="css/booking.css">
        <script src="https://kit.fontawesome.com/11c5c7a7f7.js" crossorigin="anonymous"></script>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <div class="booking-container">
            <div class="sidebar">
                <ul>
                    <li><a href="profile.jsp"><i class="fa-regular fa-user"></i> Public Profile</a></li>
                    <li><a href="ReadPetByCustomer"><i class="fa-solid fa-dog"></i> My pet</a</li>
                    <li><a href="updatePassword.jsp"><i class="fa-solid fa-shield-halved"></i> Password</a></li>
                    <li style="background-color: #eaeef2; border-left: 3px solid #0550ae;"> <a href="ReadOrderByCustomer"><i class="fa-solid fa-cart-flatbed-suitcase"></i> Booking</a></li>
                </ul>
            </div>
            <div class="booking-sub-container">
                <div class="title">
                    <h1>Booking</h1>
                    <form action="ReadOrderByCustomer" style="width: 100%">
                        <input type="text" name="txtSearch" placeholder="Booking's ID" value="${param.txtSearch}">
                    </form>

                </div>
                <div class="filter">
                    <ul>
                        <c:set var="status" value="${param.status}"/>
                        <li style="border-bottom: ${status == null ? '3px solid #72090f' : '' }"><a href="ReadOrderByCustomer">All</a></li>
                        <li style="border-bottom: ${status eq 'Pending' ? '3px solid #72090f' : '' }">
                            <a href="ReadOrderByCustomer?status=Pending">Pending</a>
                        </li>
                        <li style="border-bottom: ${status eq 'Confirm' ? '3px solid #72090f' : '' }">
                            <a href="ReadOrderByCustomer?status=Confirm">Confirm</a>
                        </li>
                        <li style="border-bottom: ${status eq 'Complete' ? '3px solid #72090f' : '' }">
                            <a href="ReadOrderByCustomer?status=Complete">Complete</a>
                        </li>
                        <li style="border-bottom: ${status eq 'Cancel' ? '3px solid #72090f' : '' }">
                            <a href="ReadOrderByCustomer?status=Cancel">Cancel</a>
                        </li>
                    </ul>
                </div>

                <div class="bookings">
                    <c:set var="bookingList" value="${requestScope.orderList}"/>
                    <c:if test="${not empty bookingList}">
                        <c:forEach items="${bookingList}" var="booking">
                            <a href="ReadOrderDetails?orderId=${booking.orderId}">
                                <div class="items" style="margin-top: 20px">
                                    <div class="items-title">
                                        <p>Booking's ID: ${booking.orderId}</p>
                                        <p>${booking.status}</p>
                                    </div>
                                    <c:set var="orderBoardingDetail" value="${booking.orderBoardingDetail}"/>
                                    <c:if test="${not empty orderBoardingDetail}">
                                        <div class="item-line">
                                            <img src="${orderBoardingDetail.room.boarding.img}" alt="${orderBoardingDetail.room.boarding.name}">
                                            <div>
                                                <h1>${orderBoardingDetail.room.boarding.name}</h1>
                                                <p>Reserve for: ${orderBoardingDetail.pet.name} - ${orderBoardingDetail.pet.type} - ${orderBoardingDetail.pet.weight}</p>
                                                <p>Check in: ${orderBoardingDetail.checkInDate} - Check out: ${orderBoardingDetail.checkOutDate}</p>
                                            </div>
                                            <p>${orderBoardingDetail.unitPrice}</p>
                                        </div>
                                    </c:if>
                                    <c:set var="orderServiceDetailList" value="${booking.orderServiceDetailList}"/>
                                    <c:if test="${not empty orderServiceDetailList}">
                                        <c:forEach items="${orderServiceDetailList}" var="orderServiceDetail">
                                            <div class="item-line">
                                                <img src="${orderServiceDetail.service.img}" alt="${orderServiceDetail.service.name}">
                                                <div>
                                                    <h1>${orderServiceDetail.service.name}</h1>
                                                    <p>Reserve for: ${orderServiceDetail.pet.name} - ${orderServiceDetail.pet.type} - ${orderServiceDetail.pet.weight}kg</p>
                                                    <p>Time: ${orderServiceDetail.time}</p>
                                                </div>                   
                                                <p>${orderServiceDetail.unit_price}</p>
                                            </div>
                                        </c:forEach>
                                    </c:if>

                                    <div class="total">
                                        <p>Amount: ${(booking.total)-(booking.total*booking.voucher.value)}</p>
                                        <c:set var="status" value="${booking.status}"/>
                                        <c:if test="${status eq 'Pending'}">
                                            <a href="UpdateStatusOrder?OrderID=${booking.orderId}&status=Cancel">
                                                <button style="display: inline-block">
                                                    Cancel
                                                </button>
                                            </a>
                                        </c:if>
                                        
                                        <c:if test="${status eq 'Complete' && (empty booking.orderBoardingDetail.feedback)}">
                                            <a href=#" onclick="displayFeedbackForm('${booking.orderId}')">
                                                <button style="display: inline-block">
                                                    Feedback
                                                </button>
                                            </a>
                                        </c:if>
                                        <br/>
                                        <form id="${booking.orderId}" action="SendFeedback" style="width: 100%; display:none">
                                            <textarea id="content" name="content" style="width: 100%" required=""></textarea>
                                            <input type="hidden" name="id" value="${booking.orderId}"/>
                                            <input type="hidden" name="status" value="${param.status}"/>
                                            <input type="hidden" name="roomID" value="${booking.orderBoardingDetail.room.roomId}"/>
                                            <input type="submit" value="Send"/>
                                        </form>
                                       

                                    </div>
                                </div>
                            </a>
                        </c:forEach>
                    </c:if>

                </div>
            </div>

        </div>
                    <script>
                        function displayFeedbackForm(orderID) {
                            document.getElementById(orderID).style.display = "block";
                        }
                    </script>
    </body>
</html>
