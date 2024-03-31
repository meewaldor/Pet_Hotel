<%-- 
    Document   : admin_profile
    Created on : Mar 11, 2024, 12:32:18 PM
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
        <link rel="stylesheet" type="text/css" href="css/customer-list.css" />
        <script src="https://kit.fontawesome.com/11c5c7a7f7.js" crossorigin="anonymous"></script>
        <link rel="preconnect" href="https://fonts.googleapis.com" />
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
        <link
            href="https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,300;1,200&family=Quicksand:wght@500&display=swap"
            rel="stylesheet"
            />
    </head>
    <body>
        <div class="container">
            <jsp:include page="sidebar.jsp"/>
            <div>
                <div class="profile-sub-container" style="padding-top: 0">
                    <c:set var="user" value="${sessionScope.admin}"/>
                    <h1 style="font-family: Poppins;font-weight: 350;">Profile - ${user.role == 1 ? "Manager": "Staff"}</h1>
                    <div class="avatar">
                        <img src="${user.img != null ? user.img : "picture/avatar.png"}" alt="avatar" />
                        <input type="file" name="changeAvatar" id="changeAvatar" class="btAvatar" />
                    </div>
                    <form action="UpdateStaffProfile" class="user-info" method="post">
                        <p>Email</p>
                        <input type="text" name="email" readonly="" value="${user.email}"/>
                        <p>Name</p>
                        <input type="text" name="name" value="${user.name}"/>
                        <p>Phone</p>
                        <input type="text" name="phone" value="${user.phone}"/> <br>
                        <p> Date of birth </p>
                        <input type="date" name="dob" value="${user.dob}"/> <br/>
                        <input type="submit" value="Update">
                        <input type="hidden" name="id" value="${user.id}"/>
                        <!--<input type="hidden" name="btAction" value="UpdateStaffProfile"/>-->
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
