<%-- 
    Document   : payment
    Created on : Feb 29, 2024, 6:16:03 PM
    Author     : Admin
--%>

<%@page import="dto.BoardingDTO"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="css/cabin.css" />
        <link rel="preconnect" href="https://fonts.googleapis.com" />
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
        <link
            href="https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,500;1,200&family=Quicksand:wght@500&display=swap"
            rel="stylesheet"
            />
    </head>
    <body>
        <jsp:include page="header.jsp"/>
        <div>
            <c:set var="boarding" value="${requestScope.boarding}"/>
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
            </c:if>

        </div>
        <!-- Review -->
        <div class="review-booking">
            <h2>Payment Confirmation</h2>
            <p>Your booking</p>
            <table id="review-booking">
                <% double total=0;%>
                <tr>
                    <th>Service</th>
                    <th>Total</th>
                </tr>
                <tr>
                    <%
                    String checkInDate = request.getParameter("checkin");
                    String checkOutDate = request.getParameter("checkout");

                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    Date checkIn = sdf.parse(checkInDate);
                    Date checkOut = sdf.parse(checkOutDate);

                    long difference = (checkOut.getTime() - checkIn.getTime()) / (1000 * 60 * 60 * 24);
                    double totalPrice = difference * ((BoardingDTO)request.getAttribute("boarding")).getPrice();
                    total += totalPrice;
                    %>
                    <td>Nights x <%= difference %></td>
                    <td><%=  totalPrice%></td>
                </tr>
            </table>
            <div class="total">
                <p>TOTAL</p>
                <p><%= total%></p>
            </div>
        </div> 
        <jsp:include page="footer.html"/>
    </body>
</html>
