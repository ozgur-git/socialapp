<%@taglib uri="http://www.springframework.org/tags/form" prefix = "form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    

<html>
<body>

    <jsp:include page="header.jsp"></jsp:include>
    
    <h3>Please enter your details to sign up</h3>
    <script>

        function checkUser(user){

            var params="user="+user.value

            var req=new XMLHttpRequest();


            console.log(params+" checkuser call")

            req.onreadystatechange=function(){

                if (this.readyState==4 && this.status==200){

                    console.log("ajax call")
                    document.getElementById("info").innerHTML=this.responseText

                }
            }

           req.open("POST","http://127.0.0.1:8080/socialapp/checkuser",true)   //TO BE CHANGED 

           req.setRequestHeader("Content-type","application/x-www-form-urlencoded")

           req.send(params)
            // req.send()

        }

    </script>
  
    <form:form action="signup" method="post" modelAttribute="signup">
        <table>
        <tr><td class="fieldname">Username:</td><td><form:input type="text" maxLength="15" path="name" onblur="checkUser(this)"/></td><td><span id="info"></span></td></tr>
            <tr><td><form:errors path="name"/></td></tr>
            <tr><td class="fieldname">Password:</td><td><form:input type="password" path="password" maxLength="16" value=""/></td></tr>
            <tr><td><form:errors path="password"/></td></tr>
            <tr></tr>
            <tr><td><input type="submit" value="Sign up"/></td></tr>    
        </table>
    </form:form>

    <h4>${account}</h4>
    <h4>${error}</h4>
</body>
</html>
