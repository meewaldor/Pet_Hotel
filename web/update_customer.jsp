<%-- 
    Document   : update_customer
    Created on : Mar 10, 2024, 9:32:23 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="css/add-form.css"/>
    </head>
    <body>
        <c:set var="c" value="${requestScope.customer}"/>
        <c:if test="${not empty c}">
            <div class="blur" id="blur" style="display:block"></div>
            <div class="add-form" id="update-form">
                <h2>Update Customer</h2>
                <form action="UpdateCustomer" method="POST">
                    <div class="sub-container1">
                        <div>
                            <p>First Name:</p>
                            <input type="text" name="fname" class="text-input" value="${c.firstName}"/><br />
                        </div>
                        <div>
                            <p>Last Name</p>
                            <input type="text" name="lname" class="text-input" value="${c.lastName}"/><br /><br/>
                        </div>
                    </div>
                    <p>Image:</p>
                    <input type="file" name="img" value="${c.img}"/><br /><br/>
                    <p>Phone:</p>
                    <input type="text" name="phone" class="text-input" value="${c.phone}"/><br /><br/>
                    <i style="color:red">${requestScope.error.PhoneNotValid}</i>

                    <input
                        class="submit-button"
                        type="submit"
                        value="SAVE"
                        style="height: 43px"
                        />
                    <input
                        type="reset"
                        class="reset-button"
                        value="CANCEL"
                        style="height: 43px"
                        onclick="hideForm()"
                        />
                    <!--<input type="hidden" name="btAction" value="UpdateCustomer"/>-->
                    <input type="hidden" name="CustomerId" value="${c.customerID}"/>
                    <c:if test="${not empty param.searchValue}">
                        <input type="hidden" name="searchValue" value="${param.searchValue}"/>
                        <input type="hidden" name="type" value="${param.type}"/>
                    </c:if>
                </form>
            </div>
        </c:if>
    </body>
</html>
