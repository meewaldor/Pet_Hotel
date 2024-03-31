<%-- 
    Document   : new_pet
    Created on : Mar 13, 2024, 9:35:10 AM
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
            <h2>Add Pet</h2>
            <form action="CreatePetAdmin">
                <div>
                    <p>Customer's phone:</p>
                    <input type="text" name="phone" class="text-input" /><br />
                </div>
                <div>
                    <p>Name:</p>
                    <input type="text" name="name" class="text-input" /><br />
                </div>
                <div>
                    <p>Date of birth:</p>
                    <input type="date" name="dob" class="text-input" required="" /><br /><br />
                </div>
                <div style="display:flex; justify-content: start">
                    <input type="radio" name="gender" value="Male" checked=""/>Male
                    <input type="radio" name="gender" value="Female"/>Female
                </div><br />
                <div style="display:flex; justify-content: start">
                    <input type="radio" name="type" value="Cat" checked=""/>Cat
                    <input type="radio" name="type" value="Dog"/>Dog
                </div><br />
                <div>
                    <p>Weight:</p>
                    <input type="number" name="weight" min="0.5" step="0.1" class="text-input" required=""/><br /><br />
                </div>

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
                <!--<input type="hidden" name="btAction" value="AdminCreatePet"/>-->
            </form>
        </div>
    </body>
</html>
