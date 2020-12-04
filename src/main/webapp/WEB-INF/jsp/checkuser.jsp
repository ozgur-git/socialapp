<%@taglib uri="http://www.springframework.org/tags/form" prefix = "form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!Doctype html>

<head>
    <link rel="stylesheet" href="<c:url value="/resources/theme/styles.css" />" >
</head>

<html>

    <%
        int attr=(int)request.getAttribute("thisList");

        if (attr!=0)
        {

    %>

        <span class="taken">&nbsp;&#x2718; This username is taken</span>

    <%

        }

        else

        {

    %>
    
        <span class="available">&nbsp;&#x2714; This username is available</span>

    <%        

        }

    %>
    
</html>
