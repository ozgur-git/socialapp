<%@taglib uri="http://www.springframework.org/tags/form" prefix = "form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!Doctype html>
<html>

<head>
    <title>SOCIAL APP (${sessionScope.sessionParam})</title>
    <link rel="stylesheet" href="<c:url value="/resources/theme/styles.css" />" >
</head>

<body>

    <center><canvas id='logo' width='624' height='96'>SOCIAL APP></canvas></center>
    <!-- <div class="socialapp">SOCIAL APP (${userString})</div>  -->
    <script src="<c:url value="/resources/script/banner.js"/>"></script>
    <div class="socapp">SOCIAL APP (${sessionScope.sessionParam})</div> 
    
    
    <br><ul class='menu'>

        <li><a href="members?view=${sessionScope.sessionParam}">Home</a></li>
        <li><a href="members">Members</a></li>
        <li><a href="friends">Friends</a></li>
        <li><a href="messages">Messages</a></li>
        <li><a href="profile">Edit Profile</a></li>
        <li><a href="logout">Log out</a></li>

    </ul><br>
   
</body>
</html>
