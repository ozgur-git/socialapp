<%@taglib uri="http://www.springframework.org/tags/form" prefix = "form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    

<html>
<body>

    <jsp:include page="header.jsp"></jsp:include>
    
    <p>${message}</p>

    <h3>Please enter your details to log in</h3>
  
    <form:form action="login" method="post" modelAttribute="something">
        <table>
            <tr><td>UserName:</td><td><form:input name="isim" path="name" /></td></tr>
            <tr><td>Password:</td><td><form:input type="password" path="password"/></td></tr>
          
            <tr><td><input type="submit" value="Login"/></td></tr>    
        </table>
    </form:form>
</body>
</html>
