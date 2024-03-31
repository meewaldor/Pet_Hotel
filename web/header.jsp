<%-- 
    Document   : header
    Created on : Jan 20, 2024, 4:49:39 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="css/header.css" />
    </head>
    <body>
        <header class="container">
            <div class="logo"></div>
            <div class="menu">
                <ul>
                    <li><a href="Home">Home</a></li>
                    <li><a href="#">About</a></li>
                    <li class="dropdown">
                        <a href="">Cat Boarding</a>
                        <ul>
                            <li><a href="#">Boarding Requirements</a></li>
                            <li><a href="#">Boarding Guide</a></li>
                            <li><a href="boarding.jsp">Boarding Rates</a></li>
                        </ul>
                    </li>
                    <li><a href="service.jsp">Service</a></li>
                    <li><a href="contact.jsp">Contact</a></li>
                </ul>
            </div>
            <div class="booking">
                <form action="Home">
                    <input class="bookingbtn" type="submit" value="Book Now" style="cursor:pointer" />
                </form>
                
            </div>
            <div class="user">
                <c:if test="${sessionScope.user!=null}">
                    <c:set var="url" value="profile.jsp"/>
                </c:if>
                <c:if test="${sessionScope.user==null}">
                    <c:set var="url" value="login.jsp"/>
                </c:if>
                <a href="${url}" class="login">Hello, ${sessionScope.user != null ? (sessionScope.user.firstName.concat(" ").concat(sessionScope.user.lastName)) : "Sign in"}</a>
                <c:if test="${sessionScope.user != null}">
                     <a href="Logout" class="logout">Log out</a>
                </c:if>
                
            </div>
        </header>
    </body>
</html>
