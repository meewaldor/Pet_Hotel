<%-- 
    Document   : new_service
    Created on : Feb 1, 2024, 5:51:39 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="css/add-form.css"/>
    </head>
    <body>
        <c:set var="s" value="${requestScope.service}"/>
        <c:if test="${not empty s}">
            <div class="blur" id="blur" style="display:block"></div>
            <div class="update-form" id="update-form">
                <h2>Update service</h2>
                <form action="UpdateService" method="POST">
                    <div class="sub-container1">
                        <div>
                            <p>Name:</p>
                            <input type="text" name="name" class="text-input" value="${s.name}"/><br />
                        </div>
                    </div>
                    <p>Image:</p>
                    <input type="file" name="img" /><br />
                    <p>Description:</p>
                    <textarea name="description" id="description" rows="10" value="${s.description}">${s.description}</textarea><br />
                    
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
                    <input type="hidden" name="rate" value="${s.rate}"/>
                    <input type="hidden" name="id" value="${s.serviceId}"/>
                    <input type="hidden" name="searchValue" value="${param.searchValue}"/>
                    <input type="hidden" name="type" value="${param.type}"/>
                    <!--<input type="hidden" name="btAction" value="UpdateService"/>-->
                </form>
            </div>
        </c:if>

        <script>
            function addHashOnEnter() {
                if (event.key === 'Enter') {
                    event.preventDefault();
                    document.getElementById("description").value += "#";
                }
            }
        </script> 
    </body>
</html>
