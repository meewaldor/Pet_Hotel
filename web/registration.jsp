<%-- 
    Document   : registration
    Created on : Jan 19, 2024, 10:11:12 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="css/login-form.css" />

        <link rel="preconnect" href="https://fonts.googleapis.com" />
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
        <link
            href="https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,500;1,200&family=Quicksand:wght@500&display=swap"
            rel="stylesheet"
            />
        <style>
            .error {text-align: left; color:red;font-size:15px}
        </style>
    </head>
    <body>
    <center class="login-page">
        <div class="login-form registration-form">
            <form action="Registration" method="POST">
                <h1>NEKO HEE</h1>
                <div class="container">
                    <p>Email*:</p>
                    <input type="text" name="email" class="input" value="${requestScope.error.emailIsExisted != null ? "" : param.email}" /> 
                    <p class="error"><i>${requestScope.error.emailIsEmpty}</i></p>
                    <p class="error"><i>${requestScope.error.emailIsExisted}</i></p>
                    <p>First Name*:</p>
                    <input type="text" name="firstName" class="input" value="${param.firstName}"/>
                    <p class="error"><i>${requestScope.error.firstNameIsEmpty}</i></p>
                    <p>Last Name*:</p>
                    <input type="text" name="lastName" class="input" value="${param.lastName}"/>
                    <p class="error"><i>${requestScope.error.lastNameIsEmpty}</i></p>
                    <p>Phone:</p>
                    <input type="text" name="phone" class="input" value="${param.phone}"/> 
                    <p class="error"><i>${requestScope.error.phoneNotValid}</i></p>
                    <p>Password*:</p>
                    <input type="password" name="password" class="input"/>
                    <p class="error"><i>${requestScope.error.passwordIsEmpty}</i></p>
                    <p>Confirm password*:</p>
                    <input type="password" name="confirmPassword" class="input" />
                    <p class="error"><i>${requestScope.error.confirmPasswordNotValid}</i></p>
                </div>

                <input type="submit" value="Registration" class="button" name="btAction" />
            </form>

            <p>
                ---------------------------------OR-----------------------------------
            </p>
            <button><a href="">Continue with Google</a></button>
            <div><a href="">Forgot password?</a></div>
        </div>
        <div class="login">
            <p>Already have an account? <a href="login.jsp">Log in</a></p>
        </div>
    </center>
</body>
</html>
