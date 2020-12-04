<%@taglib uri="http://www.springframework.org/tags/form" prefix = "form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!Doctype html>
<html>

    <jsp:include page="header.jsp"></jsp:include>

    
    <%
        if (session.getAttribute("sessionParam")!=null) 
        {
            session.invalidate();
    %>

        <div class='main'> You have been logged out, Please <a href="/socialapprds">click here</a> to  refresh the page</div> 
    <%    
        }

        else

        {
    %>

        <div class='main'> You can not log out because you are not logged in</div> 
    <%
        }

    %>  

    </span><br><br>

</html>
