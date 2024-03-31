<%-- 
    Document   : update_password
    Created on : Feb 4, 2024, 11:33:43 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
                    <li><a href="profile.jsp"><i class="fa-regular fa-user"></i> Public Profile</a></li>
                    <li><a href="ReadPetByCustomer"><i class="fa-solid fa-dog"></i> My pet</a</li>
                    <li style="background-color: #eaeef2; border-left: 3px solid #0550ae;"><a href="updatePassword.jsp"><i class="fa-solid fa-shield-halved"></i> Password</a></li>
                    <li> <a href="ReadOrderByCustomer"><i class="fa-solid fa-cart-flatbed-suitcase"></i> Booking</a></li>
                </ul>
            </div>
            <div class="profile-sub-container">
                <h1>Change password</h1>
                <form action="DispatchServlet" class="user-info">
                    <p>Old Password</p>
                    <input type="text" name="oldpassword" />
                    <p>New Password</p>
                    <input type="text" name="newpassword" />
                    <p>Confirm New Password</p>
                    <input type="text" name="confirmpassword" /> <br>
                    <input type="submit" value="Update password" name="btAction" style="width: 150px">
                    <a href="#">I forgot my password</a>
                </form>
            </div>
        </div>
    </body>
</html>
