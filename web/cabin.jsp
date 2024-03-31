<%-- 
    Document   : cabin
    Created on : Jan 21, 2024, 11:05:04 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="dto.BoardingDTO" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="css/cabin.css" />
        <link rel="stylesheet" href="css/calendar.css" />

        <link rel="preconnect" href="https://fonts.googleapis.com" />
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
        <link
            href="https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,500;1,200&family=Quicksand:wght@500&display=swap"
            rel="stylesheet"
            />

        <!-- Include FullCalendar CSS and JS -->
        <!-- Include FullCalendar CSS and JS -->
        <link
            rel="stylesheet"
            href="https://cdnjs.cloudflare.com/ajax/libs/fullcalendar/3.10.2/fullcalendar.min.css"
            />
        <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.1/moment.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/fullcalendar/3.10.2/fullcalendar.min.js"></script>
    </head>
    <body>
        <!--Header-->
        <jsp:include page="header.jsp"/>
        <div class="body-container" style="padding: 5%; grid-template-columns: 3fr 2fr">
            <div>
                <c:set var="boarding" scope="session" value="${requestScope.boarding}"/>
                <c:if test="${not empty boarding}">
                    <div>
                        <img
                            src="${boarding.img}"
                            alt="${boarding.name}"
                            />
                    </div>

                    <div>
                        <div class="title">
                            <h1>${boarding.name}</h1>
                            <p class="price">From ${boarding.price}</p>
                        </div>

                        <ul class="description">
                            <li>Lodges 1 cat only</li>
                            <li>The cat's weight less than ${boarding.maxWeight}kg</li>
                            <li>Size: ${boarding.length}m x ${boarding.width}m x ${boarding.height}m</li>
                                <c:forEach items="${boarding.description}" var="line">
                                <li>${line}</li>
                                </c:forEach> 
                        </ul>
                    </div>

                    <div class="feedback">

                    </div>
                </c:if>

            </div>

            <div>
                <div class="note" id="note">
                    <div class="box1"></div>
                    <p>Available</p>
                    <div class="box2"></div>
                    <p>Booked</p>
                    <div class="box3"></div>
                    <p>Selecting</p>
                </div>
                <div id="calendar"></div>
                <!-- Pet information -->
                <div class="pet-information" id="pet">
                    <c:set var="petList" value="${requestScope.petList}"/>
                    <h2>About your pet</h2>
                    <c:if test="${empty petList}">
                        <i>You haven't add any pet yet!</i> <br /> 
                    </c:if>

                    <c:if test="${not empty petList}">
                        <div class="pet-list">
                            <c:forEach items="${petList}" var="pet">
                                <div class="pet" style="border-radius: 0;border-bottom: 1px solid grey;border-top: 1px solid grey">
                                    <img src="${pet.img!=null?"${pet.img}":"picture/default_cat.jpg"}" alt="${pet.name}" />
                                    <div>
                                        <h3>${pet.name}</h3>
                                        <p>${pet.dob}</p>
                                        <p>${pet.type} - ${pet.gender}</p>
                                        <p>${pet.weight}kg</p>
                                    </div>
                                    <input type="radio" name="choosePet" value="${pet.petId}" checked=""/>
                                </div>
                            </c:forEach>
                        </div>
                    </c:if>
                    <button><a href="add-pet.jsp">Add more</a></button>
                </div>
                <!-- end pet information -->
                <!-- Add ons -->
                <div class="add-ons" id="addons">
                    <h2>Add-ons</h2>
                    <ul>
                        <c:forEach items="${applicationScope.serviceList}" var="service">
                            <li><input type="checkbox" name="addOns" onclick="addOnsListener(this)" value="${service.name}"/>${service.name}</li>
                            </c:forEach>
                    </ul>
                </div>
                <!-- end add ons -->
                <!-- start voucher -->
                <div class="voucher" id="voucher">
                    <h2>Voucher</h2>
                    <p>Voucher code:</p>
                    <form action="">
                        <input type="text" name="voucher" />
                        <input type="submit" value="Apply" />
                    </form>
                    <!--<i style="color: red">Invalid voucher code!</i>-->
                </div>
                <!-- end voucher -->
                <!-- review booking -->
                <div class="review-booking">
                    <h2>Review your booking</h2>
                    <table id="review-booking">
                        <tr>
                            <th>Service</th>
                            <th>Total</th>
                        </tr>
                        <tr>
                            <td>Nights x ${sessionScope.difference}</td>
                            <td>${sessionScope.totalPrice}</td>
                        </tr>
                    </table>
                    <div class="total">
                        <p>TOTAL</p>
                        <p>${sessionScope.totalBill}</p>
                    </div>
                </div>
                <!-- end review booking -->
                <!-- check out -->
                <div class="check-out">
                    <button onclick="payment()"id="checkout">Proceed to Checkout</button>

                    <!--start payment with vnpay-->
                    <form action="PaymentWithVnpay">
                        <input type="hidden" name="amount" value="${sessionScope.totalBill}"/>
                        <input type="hidden" name="petId" id="choosePet"/>
                        <input type="submit" value="Pay with VnPay" id="vnpay" 
                               style="border: none; background-color: #72090f; color: white; height: 50px; width: 150px; border-radius: 5px; font-weight: bold; cursor: pointer; display:none"/>
                    </form>
                    <!--end payment with vnpay-->

                </div>
            </div>
        </div>
        <!-- FOOTER -->
        <jsp:include page="footer.html"/>

        <script>
            $(document).ready(function () {
                $("#calendar").fullCalendar({
                    header: {
                        left: "prev,next today",
                        center: "title",
                        right: "month,agendaWeek,agendaDay",
                    },
                    // googleCalendarApiKey: "YOUR_GOOGLE_API_KEY",
                    // events: {
                    //   googleCalendarId: "YOUR_GOOGLE_CALENDAR_ID",
                    // },
                });
            });
            var checkin;
            var checkout;
            document.addEventListener("DOMContentLoaded", function () {
                var isCheckin = true;
                var calendar = $("#calendar").fullCalendar({
                    header: {
                        left: "prev,next today",
                        center: "title",
                        right: "month,agendaWeek,agendaDay",
                    },

                    dayClick: function (date, jsEvent, view) {
                        if (isCheckin) {
                            checkin = date;
                            var checkinElement = jsEvent.target;
                            checkinElement.style.backgroundColor = "rgb(246, 236, 94)";
                        } else {
                            checkout = date;
                            selectDaysInRange(checkin, checkout);
                        }
                        isCheckin = !isCheckin;
                    },
                });
                function selectDaysInRange(startDate, endDate) {
                    var current = moment(startDate);

                    while (!current.isAfter(endDate)) {
                        var dayElement = calendar.find(
                                '.fc-day[data-date="' + current.format() + '"]'
                                );

                        if (dayElement.length > 0) {
                            var dayDOMElement = dayElement[0];
                            dayDOMElement.style.backgroundColor = "rgb(246, 236, 94)";
                        }

                        current.add(1, "days");
                    }
                }
            });

            function addOnsListener(checkbox) {
                var addOns = document.getElementById("review-booking");
                if (checkbox.checked) {
                    var s = addOns.innerHTML;
                    addOns.innerHTML = s + "<tr><td>" + checkbox.value + "</td></tr>";
                } else {
                    var s = addOns.innerHTML;
                    addOns.innerHTML = s.replace("<tr><td>" + checkbox.value + "</td></tr>", "");
                }

            }

            function payment() {
                document.getElementById("note").style.display = "none";
                document.getElementById("calendar").style.display = "none";
                document.getElementById("pet").style.display = "none";
                document.getElementById("addons").style.display = "none";
                document.getElementById("voucher").style.display = "none";
                document.getElementById("checkout").style.display = "none";
                document.getElementById("vnpay").style.display = "block";
                var selectedRadio = document.querySelector('input[name="choosePet"]:checked');
                var petId = selectedRadio.value;
                document.getElementById("choosePet").value = petId;
            }

        </script>
    </body>
</html>
