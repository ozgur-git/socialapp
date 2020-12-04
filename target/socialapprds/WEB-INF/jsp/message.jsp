<%@taglib uri="http://www.springframework.org/tags/form" prefix = "form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%@ page import="java.util.*" %>
<%@ page import="com.ozgs.*" %>

<html>
<body>

    <jsp:include page="header.jsp"></jsp:include>

    <%

    if (session.getAttribute("sessionParam")!=null) 
    {
        String user=(String) session.getAttribute("sessionParam");

        if (user!=null) 
        {
    %>
        <div class="main">
        
        <h3>${nameString} Messages</h3>
            <form method="POST" action="messages?view=${view}" >
            <h3>Type here to leave a message:</h3>
            <textarea name="text" cols="40" rows="3"></textarea><br>
             Public<input type="radio" name="pm" value='0' checked="checked">
             Private<input type="radio" name="pm" value="1">&nbsp
            <input type="submit" value="Post Message"><br>
            </form>

            <%
                List<Message> messageRow=(List<Message>) request.getAttribute("messageRow");

                if (messageRow.size()!=0)
                {

                    for(Message m:messageRow) {

                        if ((!m.isPm())|(m.getAuthor().equals(user))||(m.getRecipient().equals(user)))
                        {
            %>
                <li><%=m.getTimestamp()%>&nbsp<a href="messages?view=<%=m.getAuthor()%>"><%=m.getAuthor()%></a>
            <%

                    if (!m.isPm())
                    {
            %>
                <span> wrote: &quot;<%=m.getText()%>&quot; </span>
            <%    
                    }

                    else
                    {
            %>
                 <span> whispered: &quot;<%=m.getText()%>&quot; </span>
            <%    
                    }

                    if (m.getRecipient().equals(user))
                    {
            %>
                &nbsp<a href="messages?view=${view}&erase=<%=m.getID()%>">[erase]</a></li><br>
            <%    
                        }
                    }
                }
            }

            else 

            {

            %> 
                <span class='info'> No messages yet</span><br><br>

            <% 

            }

            %>

            <p></p>

            <br><a class='button' href="messages?view=${view}">Refresh Messages</a>

            <%
    }
}
    %>

</div>
</body>
</html>
