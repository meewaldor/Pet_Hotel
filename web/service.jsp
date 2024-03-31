<%-- 
    Document   : service
    Created on : Feb 29, 2024, 11:59:54 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="css/services.css"/>
        <link rel="preconnect" href="https://fonts.googleapis.com" />
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
        <link
            href="https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,500;1,200&family=Quicksand:wght@500&display=swap"
            rel="stylesheet"
            />
        <script src="https://kit.fontawesome.com/11c5c7a7f7.js" crossorigin="anonymous"></script>
    </head>
    <body>
        <jsp:include page="header.jsp"/>
        <div class="body">
            <div class="body">
                <div class="image">
                    <img
                        src="picture/service.jpg"
                        alt="pe Hy"
                        />
                    <h1 class="title">WELCOME TO NEKO HEE</h1>
                </div>
                <h1 class="intro">What do we offer?</h1>
                <div class="services-container">
                    <c:forEach items="${applicationScope.serviceList}" var="it">
                        <div class="service-item" style="margin: 10px">
                            <img src="${it.img}"/>
                            <div>
                                <h2>${it.name}</h2>
                                <p>${it.rate} <i class="fa-solid fa-star"></i></p>
                            </div>

                            <p>${it.description}</p>
                            <div>
                                <p class="price">From 10$</p>
                                <a href="#"><button>Book Now</button></a>
                            </div>
                        </div>
                    </c:forEach>
                </div> 
            </div>
            <jsp:include page="footer.html"/>
    </body>
</html>
