<%@taglib uri="http://www.springframework.org/tags/form" prefix = "form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!Doctype html>
<html>

    <jsp:include page="header.jsp"></jsp:include>

    <br><span class="main">Welcome to SOCIAL APP, 
    
    <%
        if (session.getAttribute("sessionParam")!=null) 
        {
    %>
        (${sessionScope.sessionParam}) you are now logged in.
    <%    
        }

        else

        {
    %>

        please sign up and/or log in to join in. 
    <%
        }

    %>  

    </span><br><br>

</html>
