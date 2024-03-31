<%-- 
    Document   : boarding
    Created on : Jan 19, 2024, 10:11:36 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>

        <link rel="stylesheet" href="css/boarding-rate.css" />
        <link rel="stylesheet" href="css/header.css" />
        <link rel="stylesheet" href="css/footer.css" />

        <link rel="preconnect" href="https://fonts.googleapis.com" />
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
        <link
            href="https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,500;1,200&family=Quicksand:wght@500&display=swap"
            rel="stylesheet"
            />
        <style>
            .feedback {
                text-align: left;


            }
            .feedback h2 {
                color: #450505;
                font-weight: lighter;
            }
            .feedback-container {
                padding-left: 40px;
            }
            .cusname {
                font-weight: bold;
                padding: 0;
                margin: 0;
            }
            .feedback-line {
                border-bottom: 1px solid grey;
                margin-bottom: 20px;
            }
            .cus-feedback {
                display: flex;
                justify-content: flex-start;
            }
            .reply {
                padding-left: 70px;
            }

        </style>
    </head>
    <body>
        <!-- HEADER -->
        <jsp:include page="header.jsp"/>
        <!-- BODY -->
        <div class="body">
            <center>
                <div class="intro">
                    <h1>Boarding Rates</h1>
                    <p>-----------------------------------------</p>
                    <p>At NekoHee, we understand that your feline friend is not just a pet; they're a cherished member of your family.<br/>
                        That's why our hotels are built to resemble home, tailor-made to cater to their every need. 
                        So you'll always travel with the peace of mind, knowing they feel loved, safe, and right at home when you can't be there.</p>
                    <i>Prices stated include GST and will be effective 1 January 2024</i>
                </div>
                <div class="room-category">
                    <c:set var="boardingList" value="${applicationScope.boardingList}"/>
                    <c:if test="${not empty boardingList}">
                        <c:forEach items="${boardingList}" var="room">
                            <div>
                                <h1>${room.name}</h1>

                                <div class="detail">
                                    <img src="${room.img}" alt="${room.name}" />
                                    <ul class="description">
                                        <li>Lodges 1 cat only</li>
                                        <li>The cat's weight less than ${room.maxWeight}kg</li>
                                        <li>Size: ${room.length}m x ${room.width}m x ${room.height}m</li>
                                            <c:forEach items="${room.description}" var="line">
                                            <li>${line}</li>
                                            </c:forEach>                                           
                                        <h2>From ${room.price}VND</h2>
                                    </ul>

                                    <c:if test="${not empty room.feedback}">
                                        <div class="feedback">
                                            <h2>Feedback</h2>
                                            <div class="feedback-container">
                                                <c:forEach items="${room.feedback}" var="f">
                                                    <c:if test="${not empty f.feedback}">
                                                        <div class="feedback-line">
                                                            <div class="cus-feedback">
                                                                <img src="${f.customer.img}" alt="alt" style="width:50px; height:50px; border-radius: 50%; padding-right: 20px"/>
                                                                <div>
                                                                    <p class="cusname">${f.customer.firstName} ${f.customer.lastName}</p>
                                                                    <p>${f.feedback}</p>
                                                                </div>
                                                            </div>
                                                        </div>

                                                    </c:if>
                                                </c:forEach>
                                            </div>

                                        </div>
                                    </c:if>

                                </div>

                            </div>
                        </c:forEach>
                    </c:if>

                    <!--  -->
                </div>
                <!-- end room category -->
                <!-- Add ons -->
                <!--                <div class="add-ons">
                                    <div>
                                        <h3>ADD - ONS</h3>
                                        <h4>Customise your NekoHee Experience</h4>
                                    </div>
                                    <div class="detail">
                                        <img src="picture/z5079006647600_6799daa354879a3665e325c5e41f8676.jpg" alt="pe hy">
                                        <div>
                                             chen JSP vao day 
                                            <div class="sub-detail">
                                                <div class="title">
                                                    <p>Extend Playtime</p>
                                                    <p>15$/hr</p>
                                                </div>
                                                 <h5>Extended Playtime.............15$</h5> 
                                                <p>Full hour of uninterrupted, free roam of our indoor play lounge and hotel. 
                                                    If more than one of your cats is lodging with us, we'll let them out all at once for the same flat fee!</p>
                                            </div>
                                              
                                            <div class="sub-detail">
                                                <div class="title">
                                                    <p>Media Updates</p>
                                                    <p>5$/day</p>
                                                </div>
                                                 <h5>Extended Playtime.............15$</h5> 
                                                <p>Receive a minimum of three photos or videos of your cat(s) to your mobile device of choice.</p>
                                            </div>
                                        </div>                       
                                    </div>
                                </div>-->
            </center>
        </div>
        <!-- FOOTER -->
        <jsp:include page="footer.html"/>
    </body>
</html>
