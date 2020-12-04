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
        
        <h3>${userName}</h3>

        <img src="${src}" >&emsp; ${text}<br style="clear: left;"><br>

        ${linkMessage} 

        <h3>${membersText}</h3>

        <table>
            <c:forEach var="index" items="${modelList}">

                <tr><td><a href="members?view=${index.get(0)}"><c:out value="${index.get(0)}"/></td><td>${index.get(1)}</td><td>${index.get(2)}</td></tr>

            </c:forEach>
        </table>

        </ul>

       </div>
    <%
    }
    %>

</body>
</html>
