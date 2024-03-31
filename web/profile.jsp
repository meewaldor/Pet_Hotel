<%-- 
    Document   : profile
    Created on : Feb 4, 2024, 10:05:27 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="css/profile.css" />
        <script src="https://kit.fontawesome.com/11c5c7a7f7.js" crossorigin="anonymous"></script>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <div class="profile-container">
            <div class="sidebar">
                <ul>
                    <li style="background-color: #eaeef2; border-left: 3px solid #0550ae;"><a href="profile.jsp"><i class="fa-regular fa-user"></i> Public Profile</a></li>
                    <li><a href="ReadPetByCustomer"><i class="fa-solid fa-dog"></i> My pet</a</li>
                    <li><a href="updatePassword.jsp"><i class="fa-solid fa-shield-halved"></i> Password</a></li>
                    <li> <a href="ReadOrderByCustomer"><i class="fa-solid fa-cart-flatbed-suitcase"></i> Booking</a></li>
                </ul>
            </div>
            <div class="profile-sub-container">
                <c:set var="user" value="${sessionScope.user}"/>
                <h1>Public profile</h1>
                <div class="avatar">
                    <img src="${user.img != null ? user.img : "picture/avatar.png"}" alt="avatar" />
                    <input type="file" name="changeAvatar" id="changeAvatar" class="btAvatar" />
                </div>
                <form action="UpdateProfile" class="user-info" method="post">
                    <p class="member">${user.member} <span> - ${user.point} points</span></p>
                    <p>Email</p>
                    <input type="text" name="email" readonly="" value="${user.email}"/>
                    <p>First Name</p>
                    <input type="text" name="firstName" value="${user.firstName}"/>
                    <p>Last Name</p>
                    <input type="text" name="lastName" value="${user.lastName}"/>                    
                    <p>Phone</p>
                    <input type="text" name="phone" value="${user.phone}"/> <br>
                    <input type="submit" value="Update">
                    <input type="hidden" name="customerId" value="${user.customerID}"/>
                    <!--<input type="hidden" name="btAction" value="UpdateProfile"/>-->
                </form>
            </div>
        </div>
    </body>
</html>
