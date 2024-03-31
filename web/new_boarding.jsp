<%-- 
    Document   : add_boarding_form
    Created on : Jan 30, 2024, 8:12:45 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>New Boarding</title>
        <link rel="stylesheet" href="css/add-form.css"/>
    </head>
    <body>
        <div class="blur" id="blur"></div>
        <div class="add-form" id="add-form">
            <h2>Add new boarding</h2>
            <form action="CreateBoarding">
                <div class="sub-container1">
                    <div>
                        <p>Name:</p>
                        <input type="text" name="name" class="text-input" required=""/><br />
                    </div>
                    <div>
                        <p>Price</p>
                        <input type="number" name="price" class="text-input" required="" min="10000"/><br />
                    </div>
                </div>
                <p>Image:</p>
                <input type="file" name="img" /><br />
                <div class="sub-container">
                    <div>
                        <p>Length:</p>
                        <input type="number" name="length" step="0.1" class="text-input" min="0" required=""/>
                    </div>
                    <div>
                        <p>Height:</p>
                        <input type="number" name="height" step="0.1" class="text-input" min="0" required=""/>
                    </div>
                    <div>
                        <p>Width:</p>
                        <input type="number" name="width" step="0.1" class="text-input" min="0" required=""/>
                    </div>
                    <div>
                        <p>Max Weight:</p>
                        <input type="number" name="maxWeight" class="text-input" min="0" step="0.5" required=""/>
                    </div>
                </div>
                <p>Description:</p>
                <textarea name="description" id="description" rows="10" onkeydown="addHashOnEnter()"></textarea><br/>
                <input class="submit-button" type="submit" value="SAVE" style="height: 43px"/>
                <input type="reset" class="reset-button" value="CANCEL" style="height: 43px" onclick="hideForm()"/>
                <!--<input type="hidden" name="btAction" value="CreateBoarding"/>-->
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
