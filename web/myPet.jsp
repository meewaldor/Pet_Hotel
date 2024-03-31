<%-- 
    Document   : myPet
    Created on : Feb 4, 2024, 11:34:19 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="css/mypet.css" />
        <link rel="stylesheet" href="css/new_pet.css">

        <script
            src="https://kit.fontawesome.com/11c5c7a7f7.js"
            crossorigin="anonymous"
        ></script>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <div class="pet-container">
            <div class="sidebar">
                <ul>
                    <li><a href="profile.jsp"><i class="fa-regular fa-user"></i> Public Profile</a></li>
                    <li style="background-color: #eaeef2; border-left: 3px solid #0550ae;"><a href="ReadPetByCustomer"><i class="fa-solid fa-dog"></i> My pet</a</li>
                    <li><a href="updatePassword.jsp"><i class="fa-solid fa-shield-halved"></i> Password</a></li>
                    <li> <a href="ReadOrderByCustomer"><i class="fa-solid fa-cart-flatbed-suitcase"></i> Booking</a></li>
                </ul>
            </div>
            <div class="pet-sub-container">
                <div class="title">
                    <h1>My pet</h1>
                    <form action="ReadPetByCustomer" style="width: 80%">
                        <input type="text" name="searchValue" placeholder="Pet's name" value="${param.searchValue}">
                    </form>

                    <button onclick="displayAddForm()">New+</button>
                </div>

                <c:forEach var="p" items="${requestScope.data}">
                    <div class="pet">
                        <img src="picture/classic-cabin.jpg" alt="">
                        <p style="font-weight: 500;">${p.name}</p>
                        <p>${p.dob}</p>
                        <p>${p.type}</p>
                        <p>${p.gender}</p>
                        <p>${p.weight}kg</p>
                        <a href="UpdatePetUserView?petId=${p.petId}"><i class="fa-regular fa-pen-to-square"></i></a>
                        <a href="#" onclick="doDelete('${p.petId}','${p.name}','${param.searchValue}')"><i class="fa-solid fa-trash-can"></i></a>
                    </div>
                </c:forEach>
            </div>
            <!--Form add pet-->
            <div class="pet-add-form" id="add-form">
                <h2>Add New Pet</h2>
                <form action="CreatePet">
                    <p>Name</p>
                    <input type="text" name="petName" class="control" required=""> <br>
                    <p>Image</p>
                    <input type="file" name="petimg">
                    <p>Date of birth</p>
                    <input type="date" name="petdob" class="control" required=""><br>

                    <p>Weight</p>
                    <input type="number" name="petWeight" class="control" step="0.1" min="0" required=""><br>
                    <input type="radio" name="petGender" value="Male" checked="check"> Male
                    <input type="radio" name="petGender" value="Female"> Female <br>
                    <input type="radio" name="type" value="Cat" checked="check"> Cat
                    <input type="radio" name="type" value="Dog"> Dog <br>
                    <input type="submit" class="submit-button" value="Add Pet" class="save-button">             
                    <input type="reset" class="reset-button" value="Cancel" onclick="hideForm()">             
                </form>
            </div>
            <!--End form add pet-->

            <!--Form update pet's information-->
            <c:set var="c" value="${requestScope.pet}"/>
            <c:if test="${not empty c}">
                <div class="blur" id="blur" style="display:block"></div>
                <div class="pet-add-form" id="update-form" style="display: block">
                    <h2>Update Pet</h2>
                    <form action="UpdatePetUserView" method="POST">
                        <input type="hidden" id="petId" name="petId" value="${c.petId}" />
                        <p>Name</p>
                        <input type="text" id="petName" name="petName" class="control" value="${c.name}" required=""/> <br>
                        <p>Image</p>
                        <input type="file" id="petimg" name="petimg" /> 
                        <p>Date of birth</p>
                        <input type="date" id="petdob" name="petdob" class="control" value="${c.dob}" required=""/><br>

                        <p>Weight</p>
                        <input type="number" id="petWeight" name="weight" class="control" value="${c.weight}" required="" min="0" step="0.1" /><br>
                        <input type="radio" id="male" name="gender" value="Male" ${c.gender == 'male' ? 'checked' : ''} /> Male
                        <input type="radio" id="female" name="gender" value="Female" ${c.gender == 'female' ? 'checked' : ''} /> Female<br>
                        <input type="radio" name="type" value="Cat" ${c.type == 'Cat' ? 'checked' : ''} /> Cat
                        <input type="radio" name="type" value="Dog" ${c.type == 'Dog' ? 'checked' : ''} /> Dog<br>
                        <input type="submit" class="submit-button" value="Save" />             
                        <input type="reset" class="reset-button" value="Cancel" onclick="hideForm()" />             
                    </form>
                </div>
            </c:if>

            <!--End Form update pet's information-->

            <div class="blur" id="blur"></div>
        </div>
        <script src="js/ManageForm.js"></script>
    </body>

    <script>
                            function doDelete(id,name,searchValue) {
                                var url = ("DeletePet?id=" + id)
                                if (searchValue !== null && searchValue !== "")
                                    url += ("&searchValue=" + searchValue);
                                if (confirm("Are you sure to delete "+name+"?")) {
                                    window.location = url;
                                }
                            }
    </script>
</html>
