<%@taglib uri="http://www.springframework.org/tags/form" prefix = "form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!Doctype html>
<html>

    <%
        if (session.getAttribute("sessionParam")!=null) 
        {

    %>
        <jsp:include page="loggedinheader.jsp"></jsp:include>
     <%    
        }

        else

        {
    %>
        <jsp:include page="unloggedheader.jsp"></jsp:include>
  <%
        }

    %>  
</html>
