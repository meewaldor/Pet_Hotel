<%-- 
    Document   : new_service
    Created on : Feb 1, 2024, 5:51:39 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="css/add-form.css"/>
    </head>
    <body>
        <div class="blur" id="blur"></div>
        <div class="add-form" id="add-form">
            <h2>Add new service</h2>
            <form action="CreateService">
                <div class="sub-container1">
                    <div>
                        <p>Name:</p>
                        <input type="text" name="name" class="text-input" /><br />
                    </div>
                </div>
                <p>Image:</p>
                <input type="file" name="img" /><br />
                <p>Description:</p>
                <textarea name="description" rows="10"></textarea><br />
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
                <!--<input type="hidden" name="btAction" value="CreateService"/>-->
            </form>
        </div>
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
