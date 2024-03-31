<%-- 
    Document   : sidebar
    Created on : Jan 24, 2024, 3:40:02 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>

        <link rel="stylesheet" type="text/css" href="css/sidebar.css" />
        <script src="https://kit.fontawesome.com/11c5c7a7f7.js" crossorigin="anonymous"></script>
        <link rel="preconnect" href="https://fonts.googleapis.com" />
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
        <link
            href="https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,500;1,200&family=Quicksand:wght@500&display=swap"
            rel="stylesheet"
            />
    </head>
    <body>
        <nav class="main-menu">
            <div class="bar">
                <ul>  

                    <li>                                 
                        <a href="ReadOrder">
                            <i class="fa fa-shopping-cart fa-lg"></i>
                            <p class="nav-text">Orders</p>
                        </a>
                    </li>   


                    <li>                                 
                        <a href="ReadCustomer">
                            <i class="fa fa-users fa-lg"></i>
                            <p class="nav-text">Customers</p>
                        </a>
                    </li>   
                    
                    <li class="">
                        <a href="ReadPet?page=1">
                            <i class="fa-solid fa-dog"></i>
                            <p class="nav-text">Pet</p>
                        </a>
                    </li>

                    
                    <li>
                        <a href="ReadService">
                            <i class="fa-solid fa-boxes-stacked"></i>
                            <p class="nav-text">Services</p>
                        </a>
                    </li>

                    <li class="">
                        <a href="ReadBoarding">
                            <i class="fa fa-building-o"></i>
                            <p class="nav-text">Boardings</p>
                        </a>
                    </li>
                    
                    <li class="">
                        <a href="ReadTransaction?page=1">
                            <i class="fa fa-building-o"></i>
                            <p class="nav-text">Transaction</p>
                        </a>
                    </li>

                </ul>
                <ul>
                    <li class="">
                        <a href="admin_profile.jsp">
                            <i class="fa fa-user"></i>
                            <span class="nav-text">Account</span>
                        </a>
                    </li>
                    <li class="">
                        <a href="Logout">
                            <i class="fa fa-sign-out"></i>
                            <p class="nav-text">Log out</p>
                        </a>
                    </li>
                </ul>
            </div>
        </nav>
    </body>
</html>
