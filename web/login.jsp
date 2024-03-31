<%-- 
    Document   : login.jsp
    Created on : Jan 19, 2024, 10:10:58 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="css/login.css" />

        <link rel="preconnect" href="https://fonts.googleapis.com" />
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
        <link
            href="https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,500;1,200&family=Quicksand:wght@500&display=swap"
            rel="stylesheet"
            />
        <script src="https://www.gstatic.com/firebasejs/ui/6.0.1/firebase-ui-auth.js"></script>
        <link type="text/css" rel="stylesheet" href="https://www.gstatic.com/firebasejs/ui/6.0.1/firebase-ui-auth.css" />
    </head>
    <body>
    <center class="login-page">
        <div class="login-form">
            <form action="Login" method="POST">
                <h1>NEKO HEE</h1>
                <i style="color:red">${requestScope.error}</i>
                <i style="color:green">${requestScope.msg}</i>
                <input type="text" name="email" placeholder="Email" class="input" />
                <br />
                <input
                    type="password"
                    name="password"
                    placeholder="Password"
                    class="input"
                    />
                <br />
                <input type="submit" value="Login" class="button" name="btAction" />
            </form>

            <p>
                ---------------------------------OR---------------------------------
            </p>
            <!--            <div id="firebaseui-auth-container" style="width: 100%"></div>
                        <div id="loader">Loading...</div>-->
            <button><a href="https://accounts.google.com/o/oauth2/auth?scope=profile&redirect_uri=http://localhost:8080/pet_hotel/GoogleSignInServlet&response_type=code&client_id=721716917988-dejcsnlfoh4rlag9krnmem58b0qd76u2.apps.googleusercontent.com&approval_prompt=force">
                    Continue with Google
                </a>
            </button>
            <div><a href="#">Forgot password?</a></div>
        </div>
        <div class="signin">
            <p>
                You don't have an account?
                <a href="registration.jsp">Sign up</a>
            </p>
        </div>
    </center>
    <script src="https://www.gstatic.com/firebasejs/4.8.1/firebase.js"></script>
    <!--<script type="module" src="js/OAuth.js"></script>-->

</body>
</html>
