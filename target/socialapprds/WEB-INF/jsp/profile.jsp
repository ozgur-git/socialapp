<%@taglib uri="http://www.springframework.org/tags/form" prefix = "form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    

<html>
<body>

    <jsp:include page="header.jsp"></jsp:include>

    <%

    if (session.getAttribute("sessionParam")!=null) 
    {
    %>
        <div class="main">
        
        <h3>Your Profile</h3>

        <img src="${src}" >&emsp; ${text}<br style="clear: left;"><br>

            <form method="POST" action="profile" enctype="multipart/form-data">
            <h3>Enter or edit your details and/or upload an image</h3>
            <textarea name="text" cols="50" rows="3"></textarea><br>
            Image: <input type="file" name="file" size="14">&nbsp
            <input type="submit" value="Save Profile">
            </form>

        </div>
    <%
    }
    %>

</body>
</html>
