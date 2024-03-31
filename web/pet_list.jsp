<%-- 
    Document   : pet_list
    Created on : Mar 11, 2024, 5:21:10 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" type="text/css" href="css/customer-list.css" />

        <link rel="preconnect" href="https://fonts.googleapis.com" />
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
        <link
            href="https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,300;1,200&family=Quicksand:wght@500&display=swap"
            rel="stylesheet"
            />
    </head>
    <body>
        <div class="container">
            <!--BODY  -->
            <jsp:include page="sidebar.jsp"/>
            <center class="body">
                <div class="header">
                    <h1>Pets</h1>
                    <div class="account">
                        <a href=""><img src="" alt="" /></a>
                    </div>
                </div>
                <div class="tool">
                    <!--search-->
                    <form action="ReadPet" class="search">
                        <!--<input type="hidden" name="btAction" value="ReadPets"/>-->
                        <select id="selectedType" name="type">
                            <option value="ID">ID</option>
                            <option value="Name">Name</option>
                            <option value="Phone">Customer's Phone</option>
                        </select>
                        <input style="width: 30vw" type="text" name="searchValue" value="${param.searchValue}" placeholder="Search..."/>

                    </form>
                    <div style="display: flex">
                        <button onclick="displayAddForm()" style="margin-right: 13px;height: 23px;"><i class="fa fa-plus"></i> New</button>
                        <!--Filter-->
                        <form action="ReadPet">
                            <select id="typeFilter" name="typeFilter" style="padding: 2px;">
                                <option value="All">All</option>
                                <option value="Cat">Cat</option>
                                <option value="Dog">Dog</option>
                            </select>
                            <input type="submit" value="Filter"/>
                            <!--<input type="hidden" name="btAction" value="ReadPets"/>-->
                        </form>   
                    </div>
                    <!--Add-->

                </div>
                <div class="table" style="height: 70vh;border-bottom: 1px solid rgb(199, 198, 198);">
                    <table>
                        <tr class="head">
                            <th>Pet's ID</th>
                            <th>Pet's Name</th>
                            <th>Customer's Name</th>
                            <th>Date of birth</th>
                            <th>Gender</th>
                            <th>Type</th>
                            <th>Weight</th>
                            <th>Status</th>
                            <th></th>
                        </tr>
                        <c:set var="list" value="${requestScope.data}"/>
                        <c:if test="${not empty list}">
                            <c:forEach items="${list}" var="pet" >
                                <tr>
                                    <td style="font-weight: bold">${pet.petId}</td>
                                    <td style="font-weight: bold">${pet.name}</td>
                                    <td>${pet.customer.firstName} ${pet.customer.lastName}</td>
                                    <td>${pet.dob}</td>
                                    <td>${pet.gender}</td>
                                    <td>${pet.type}</td>

                                    <!--update weight-->
                                    <td>
                                        <form action="UpdatePet">
                                            <!--<input type="hidden" name="btAction" value="UpdatePet"/>-->
                                            <input type="number" name="weight" value="${pet.weight}" required="" step="0.1" min="0.5" style="width: 50px"/>
                                            <input type="hidden" name="id" value="${pet.petId}"/>
                                            <c:if test="${not empty param.searchValue}">
                                                <input type="hidden" name="searchValue" value="${param.searchValue}"/>
                                                <input type="hidden" name="type" value="${param.type}"/>
                                            </c:if>
                                            <c:if test="${not empty param.typeFilter}">
                                                <input type="hidden" name="typeFilter" value="${param.typeFilter}"/>
                                            </c:if>
                                        </form>
                                    </td>
                                    <td>${pet.status ? "Enable" : "Disable"}</td>

                                    <!--Delete link-->
                                    <td style="text-align: center;">
                                        <a href="#" onclick="doDelete('${pet.petId}', '${param.searchValue}', '${param.type}', '${param.typeFilter}')">
                                            <i class="fa fa-trash-o" style="color: grey"></i>
                                        </a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </c:if>
                        <c:if test="${empty list}">
                            <tr>
                                <td colspan="10" style="text-align: center"><i>Empty List</i></td>
                            </tr>
                        </c:if>
                    </table>
                </div>
                <div class="pagination-container">
                    <a href="ReadPet?page=${param.page - 1}"><button>&#9665;</button></a>
                    <span id="currentPage">${param.page}</span>
                    <a href="ReadPet?page=${param.page + 1}"><button>&#9655;</button></a>

                </div>
            </center>
            <jsp:include page="new_pet.jsp"/>
        </div>

        <script type="text/javascript" src="js/ManageForm.js"></script>
        <script type="text/javascript">
                                            function doDelete(id, searchValue, type, typeFilter) {
                                                var url = ("DeletePet?id=" + id)
                                                if (searchValue !== null && searchValue !== "")
                                                    url += ("&searchValue=" + searchValue + "&type=" + type);
                                                if (typeFilter !== null && typeFilter !== "")
                                                    url += ("&typeFilter=" + typeFilter);
                                                if (confirm("Are you sure to delete Pet with ID = " + id)) {
                                                    window.location = url;
                                                }
                                            }
                                            function setType(paramType) {
                                                var selectElement = document.getElementById("selectedType");
                                                for (var i = 0; i < selectElement.options.length; i++) {
                                                    if (selectElement.options[i].value === paramType) {
                                                        selectElement.options[i].selected = true;
                                                    }
                                                }
                                            }
                                            setType('${param.type}');
                                            function hideForm() {
                                                if (document.getElementById("add-form") !== null)
                                                    document.getElementById("add-form").style.display = "none";
                                                if (document.getElementById("update-form") !== null)
                                                    document.getElementById("update-form").style.display = "none";
                                                document.getElementById("blur").style.display = "none";
                                            }
                                            function setLastFilter(lastFilter) {
                                                var selectElement = document.getElementById("typeFilter");
                                                for (var i = 0; i < selectElement.options.length; i++) {
                                                    if (selectElement.options[i].value === lastFilter) {
                                                        selectElement.options[i].selected = true;
                                                    }
                                                }
                                            }
                                            setLastFilter('${param.typeFilter}');

        </script>
    </body>
</html>
