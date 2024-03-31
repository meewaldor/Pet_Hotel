<%-- 
    Document   : contact
    Created on : Mar 1, 2024, 10:28:02 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="css/contact.css"/>
        <link rel="preconnect" href="https://fonts.googleapis.com" />
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
        <link
            href="https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,500;1,200&family=Quicksand:wght@500&display=swap"
            rel="stylesheet"
            />
    </head>
    <body onload="initMap()">
        <jsp:include page="header.jsp"/>
    <center>
        <!-- Google Map-->
        <div class="map">
            <h1>Locate Us</h1>
            <p><b>Operating hours:</b> <span style="color:grey"> 11AM – 7PM every day of the year, unless otherwise stated.</span></p>
            <div id="googleMap" style="width:100%;height:400px;"></div>
        </div>
        <!--End Google Map-->                 
    </center>

    <jsp:include page="footer.html"/>

    <script>
        function myMap() {
            var mapProp = {
                center: new google.maps.LatLng(10.8368889, 106.8301667),
                zoom: 15,
            };
            var map = new google.maps.Map(document.getElementById("googleMap"), mapProp);
            var marker = new google.maps.Marker({
                position: new google.maps.LatLng(10.8368889, 106.8301667),
            });

            marker.setMap(map);
            var infowindow = new google.maps.InfoWindow({
                content: "NekoHee<br/>S201 Vinhome Grand Park - d9 - HCM<br/>(+84)12345678"
            });

            marker.addListener('mouseover', function () {
                infowindow.open(map, marker);
            });

            marker.addListener('mouseout', function () {
                infowindow.close();
            });

        }
    </script>
    <script src="//maps.googleapis.com/maps/api/js?key=AIzaSyAHVW8FKindiWJNqKUg0xsL3qqcj_WQSSM&callback=myMap" async="" defer="defer" type="text/javascript"></script>

</body>


</html>
