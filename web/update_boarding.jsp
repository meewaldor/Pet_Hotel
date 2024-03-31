<%-- 
    Document   : add_boarding_form
    Created on : Jan 30, 2024, 8:12:45 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="css/add-form.css"/>
    </head>
    <body>
        <c:set var="c" value="${requestScope.boarding}"/>
        <c:if test="${not empty c}">
            <div class="blur" id="blur" style="display:block"></div>
            <div class="add-form" id="update-form">
                <h2>Update boarding</h2>
                <form action="UpdateBoarding" method="POST">
                    <div class="sub-container1">
                        <input type="hidden" name="id" value="${c.boardingId}"/>
                        <div>
                            <p>Name:</p>
                            <input type="text" name="name" class="text-input" value="${c.name}"/><br />
                        </div>
                        <div>
                            <p>Price</p>
                            <input type="number" min="10000" name="price" class="text-input" value="${c.price}"/><br />
                        </div>
                    </div>
                    <p>Image:</p>
                    <input type="file" name="img" value="${c.img}"/><br />
                    <input type="hidden" name="rate" value="${c.rate}"/>
                    <div class="sub-container">
                        <div>
                            <p>Length:</p>
                            <input type="number" name="length" min="0" class="text-input" step="0.1" value="${c.length}"/>
                        </div>
                        <div>
                            <p>Height:</p>
                            <input type="number" name="height" min="0" class="text-input" step="0.1" value="${c.height}"/>
                        </div>
                        <div>
                            <p>Width:</p>
                            <input type="number" name="width" min="0" class="text-input" step="0.1" value="${c.width}"/>
                        </div>
                        <div>
                            <p>Max Weight:</p>
                            <input type="number" name="maxWeight" min="0" class="text-input" step="0.1" value="${c.maxWeight}"/>
                        </div>
                    </div>
                    <p>Description:</p>
                    <textarea name="description" id="description" rows="10" onkeydown="addHashOnEnter()">${fn:join(c.description, '#')}</textarea><br />
                    <!--<input type="hidden" name="btAction" value="UpdateBoarding"/>-->
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
                    <input type="hidden" name="searchValue" value="${param.searchValue}"/>
                    <input type="hidden" name="type" value="${param.type}"/>
                    <!--<input type="hidden" name="btAction" value="UpdateBoarding"/>-->
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
