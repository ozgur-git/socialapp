<%@taglib uri="http://www.springframework.org/tags/form" prefix = "form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    

<html>
<body>

    <jsp:include page="header.jsp"></jsp:include>
    
    <h3>You are now logged in. Please <a href="members?view=${sessionScope.sessionParam}">click here</a> to continue</h3>
  
   </body>
</html>
