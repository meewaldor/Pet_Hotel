<%-- 
    Document   : booking_detail
    Created on : Feb 5, 2024, 10:54:05 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="css/booking_detail.css" />
        <script
            src="https://kit.fontawesome.com/11c5c7a7f7.js"
            crossorigin="anonymous"
        ></script>
    </head>
    <body>
        <%@include  file="header.jsp"%>
        <div class="booking-detail-container">
            <div class="sidebar">
                <ul>
                    <li><a href="profile.jsp"><i class="fa-regular fa-user"></i> Public Profile</a></li>
                    <li><a href="ReadPetByCustomer"><i class="fa-solid fa-dog"></i> My pet</a</li>
                    <li><a href="updatePassword.jsp"><i class="fa-solid fa-shield-halved"></i> Password</a></li>
                    <li style="background-color: #eaeef2; border-left: 3px solid #0550ae;"> <a href="ReadOrderByCustomer"><i class="fa-solid fa-cart-flatbed-suitcase"></i> Booking</a></li>
                </ul>
            </div>
            <div class="booking-detail-sub-container">
                <c:set var="order" value="${requestScope.order}"/>
                <c:if test="${not empty order}">
                    <div class="title">
                        <a href="ReadOrderByCustomer"><i class="fa-solid fa-arrow-left"></i> Return</a>
                        <p>Booking's ID: ${order.orderId}</p>
                        <p style="color:#72090f">${order.status}</p>
                    </div>
                    <div class="stepper">
                        <c:set var="records" value="${order.statusRecords}"/>
                        <c:set var="lastStep" value="0"/>
                        <c:forEach items="${records.keySet()}" var="time">
                            <c:set var="lastStep" value="${lastStep+1}"/>
                            <div class="step">
                                <c:set var="icon">
                                    <c:choose>
                                        <c:when test="${records.get(time) eq 'Pending'}">fa-solid fa-receipt</c:when>
                                        <c:when test="${records.get(time) eq 'Confirm'}">fa-solid fa-check-to-slot</c:when>
                                        <c:when test="${records.get(time) eq 'Complete'}">fa-solid fa-money-bill</c:when>
                                        <c:when test="${records.get(time) eq 'Cancel'}">fa-regular fa-star</c:when>
                                        <c:otherwise>fa-regular fa-star</c:otherwise>
                                    </c:choose>
                                </c:set>
                                <div class="step-icon" style="border-color: green">
                                    <i class="${icon}" style="color: green"></i>
                                </div>
                                <p>${records.get(time)}</p>
                                <p>${time}</p>
                            </div>
                        </c:forEach>

                        <c:forEach begin="${lastStep+1}" end="4" var="i">
                            <c:set var="s">
                                <c:choose>
                                    <c:when test="${i eq 2}">fa-solid fa-check-to-slot</c:when>
                                    <c:when test="${i eq 3}">fa-solid fa-money-bill</c:when>
                                    <c:otherwise>fa-regular fa-star</c:otherwise>
                                </c:choose>
                            </c:set>
                            <div class="step">
                                <div class="step-icon">
                                    <i class="${s}"></i>
                                </div>
                                <p>${i==2?"Confirmed":(i==3?"Complete":"Cancel")}</p>
                            </div>
                        </c:forEach>

                    </div>
                    <div class="booking">
                        <div class="items">
                            <c:set var="bDetail" value="${order.orderBoardingDetail}"/>
                            <!--Detail of Boarding-->
                            <c:if test="${not empty bDetail}">
                                <div class="item-line">
                                    <img src="${bDetail.room.boarding.img}" alt="${bDetail.room.boarding.name}">
                                    <div>
                                        <h1>${bDetail.room.boarding.name}</h1>
                                        <p>Reserve for: ${bDetail.pet.name} - ${bDetail.pet.type} - ${bDetail.pet.weight}</p>
                                        <p>Check in: ${bDetail.checkInDate} - Check out: ${bDetail.checkOutDate}</p>
                                    </div>                   
                                    <p>${bDetail.unitPrice}</p>
                                </div>
                            </c:if>

                            <!--Detail of Service-->
                            <c:set var="sDetails" value="${order.orderServiceDetailList}"/>
                            <c:if test="${not empty sDetails}">
                                <c:forEach items="${sDetails}" var="detail">
                                    <div class="item-line">
                                        <img src="${detail.service.img}" alt="${detail.service.name}">
                                        <div>
                                            <h1>${detail.service.name}</h1>
                                            <p>Reserve for: ${detail.pet.name} - ${detail.pet.type} - ${detail.pet.weight}</p>
                                            <p>Time: ${detail.time}</p>
                                        </div>                   
                                        <p>${detail.unit_price}</p>
                                    </div>
                                </c:forEach>
                            </c:if>

                            <div class="total">
                                <p>Total: ${order.total}</p>
                                <c:if test="${not empty order.voucher.value}">
                                    <p>Discount(${order.voucher.value*100}%): -${order.total*order.voucher.value}</p>
                                </c:if>
                                <p>Amount: ${(order.total)-(order.total*order.voucher.value)}</p>
                                <c:set var="status" value="${order.status}"/>
                                <button style="display: ${status eq 'Pending' or status eq 'Complete' ? 'inline-block' : 'none'}">
                                    <c:choose>
                                        <c:when test="${status eq 'Pending'}">Cancel</c:when>
                                        <c:when test="${status eq 'Complete'}">Feedback</c:when>
                                    </c:choose>
                                </button>
                            </div>
                        </div>
                        <img src="" alt="">
                    </div>
                </c:if>
            </div>
        </div>
    </body>
</html>
