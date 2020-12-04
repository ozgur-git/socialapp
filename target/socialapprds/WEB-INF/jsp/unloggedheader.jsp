<%@taglib uri="http://www.springframework.org/tags/form" prefix = "form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!Doctype html>
<html>

<head>
    <title>SOCIAL APP (${userString})</title>
    <link rel="stylesheet" href="<c:url value="/resources/theme/styles.css" />" >
</head>

<body>
    <center><canvas id='logo' width='624' height='96'>SOCIAL APP></canvas></center>
    <script src="<c:url value="/resources/script/banner.js"/>"></script>
    <div class="socapp">SOCIAL APP ${userString}</div> 
    <br><ul class='menu'>

        <li><a href="/socialapprds">Home</a></li>
        <li><a href="signup">Sign up</a></li>
        <li><a href="login">Log in</a></li></ul><br>
        <span class="info">&#8658; You must be logged in to view this page</span><br><br>
    
</body>
</html>
