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
        
        <h3>Your Mutual Friends</h3>

        <c:forEach var="index" items="${mutualList}">

           <li><a href="members?view=${index}"><c:out value="${index}"/></a></li>

        </c:forEach>

         <h3>Your Followers</h3>

        <c:forEach var="index" items="${followersList}">

           <li><a href="members?view=${index}"><c:out value="${index}"/></a></li>

        </c:forEach>
        
        <h3>Your are following</h3>

        <c:forEach var="index" items="${followingList}">

           <li><a href="members?view=${index}"><c:out value="${index}"/></a></li>

        </c:forEach>

       </div>
    <%
    }
    %>

</body>
</html>
