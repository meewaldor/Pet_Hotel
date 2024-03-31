<%-- 
    Document   : home
    Created on : Jan 19, 2024, 10:10:24 PM
    Author     : Admin
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home Page</title>

        <link rel="stylesheet" href="css/home.css" />
        <link rel="stylesheet" href="css/boarding-process.css" />
        <link rel="stylesheet" href="css/cabin-types.css" />

        <link rel="preconnect" href="https://fonts.googleapis.com" />
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
        <link
            href="https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,500;1,200&family=Quicksand:wght@500&display=swap"
            rel="stylesheet"
            />
    </head>
    <body>
        <!-- HEADER -->
        <jsp:include page="header.jsp"/>
        <!-- BODY -->
        <div class="body">
            <div class="image">
                <img
                    src="picture/service.jpg"
                    alt="pe Hy"
                    />
                <!--<iframe width="100%" height="600px" src="https://www.youtube.com/embed/Q4ZjynO4bHw?autoplay=1&loop=1&controls=0&showinfo=0&autohide=1&mute=1" frameborder="0" allowfullscreen></iframe>-->
                <h1 class="title">WELCOME TO NEKO HEE</h1>
            </div>
            <!-- BOOKING FORM -->
            <div class="booking-form">
                <div class="search">
                    <form action="SearchBoarding">
                        <div class="checkin">
                            <p>Check In</p>
                            <%
                                Date date = new Date();
                                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                                String date_string = format.format(date);
                            %>
                            <input type="date" id="checkin" name="checkin" value="${param.checkin}" required="" min="<%= date_string%>"/>
                        </div>


                        <div class="checkout">
                            <p>Check Out</p>
                            <input type="date" id="checkout" name="checkout" value="${param.checkout}" required="" min="<%= date_string%>"/>
                        </div>
                        <div class="btn-search">
                            <input type="submit" value="SEARCH" name="btAction" />
                        </div>
                    </form>

                    <!-- search result -->
                    <div class="search-result">
                        <c:set var="result" value="${requestScope.result}"/>
                        <c:if test="${not empty result}">
                            <h2>Search results</h2>
                            <div class="container">
                                <c:forEach items="${result}" var="item">
                                    <div>
                                        <a href="Cabin?id=${item.boardingId}&checkin=${param.checkin}&checkout=${param.checkout}">
                                            <img
                                                src="${item.img}"
                                                alt="${item.name}"
                                                style="height: inherit"/>
                                            <h3>${item.name}</h3>
                                            <button style="cursor:pointer">VIEW</button>
                                        </a>
                                    </div>
                                </c:forEach>
                            </div>
                        </c:if>
                    </div>
                </div>
            </div>
            <!-- CABIN TYPE -->
            <center class="cabin-type">
                <h2>Cabin Types</h2>
                <div class="container">
                    <c:set var="boardingList" value="${applicationScope.boardingList}"/>
                    <c:if test="${not empty boardingList}">
                        <c:forEach items="${boardingList}" var="room">
                            <div>
                                <img
                                    src="${room.img}"
                                    alt="${room.name}"
                                    />
                                <h3>${room.name}</h3>
                                <p>From ${room.price}</p>
                            </div>
                        </c:forEach>
                    </c:if>
                </div>
                <button><a href="boarding.jsp">See Full Pricing</a></button>
            </center>
            <!-- BOARDING PROCCESS -->
            <center class="boarding-process">
                <h2>Our Boarding Process</h2>
                <div class="container">
                    <img src="picture/CatGuest06.jpg" alt="" />
                    <div class="steps">
                        <div class="step">
                            <p>Step 1</p>
                            <h3>Check Eligibility</h3>
                            <p>Check boarding eligibility here!</p>
                        </div>
                        <div class="step">
                            <p>Step 2</p>
                            <h3>Book a Viewing (Optional)</h3>
                        </div>
                        <div class="step">
                            <p>Step 3</p>
                            <h3>Book Date & Cabin Type</h3>
                        </div>
                        <div class="step">
                            <p>Step 4</p>
                            <h3>Pack Cat's Boarding Bag</h3>
                        </div>
                        <div class="step">
                            <p>Step 5</p>
                            <h3>Check in/out</h3>
                            <p>Check in: 2PM – 5.30PM</p>
                            <br />
                            <p>Check out: 11AM – 12.30PM</p>
                        </div>
                    </div>
                </div>
            </center>
            <!-- DESCRIPTION -->
            <!-- SERVICE -->
        </div>
        <!-- FOOTER -->
        <jsp:include page="footer.html"/>

        <script>
//            var checkin = document.getElementById("checkin");
//            var checkout = document.getElementById("checkout");
//            checkin.addEventListener("change", function () {
//                checkout.min = checkin.value;
//            });
            var checkin = document.getElementById("checkin");
            var checkout = document.getElementById("checkout");

            checkin.addEventListener("change", function () {
                // Chuyển đổi giá trị ngày checkin thành đối tượng Date
                var checkinDate = new Date(checkin.value);

                // Tăng giá trị của ngày checkin thêm 1 ngày
                checkinDate.setDate(checkinDate.getDate() + 1);

                // Chuyển đổi ngày mới thành chuỗi có định dạng ngày tháng "YYYY-MM-DD"
                var nextDay = checkinDate.toISOString().split("T")[0];

                // Gán giá trị mới cho thuộc tính "min" của ngày checkout
                checkout.min = nextDay;
            });
        </script>
    </body>
</html>
