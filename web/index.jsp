<%--
  Created by IntelliJ IDEA.
  User: mikhail
  Date: 06.03.15
  Time: 23:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>
   <%--   <c:out value="JSTL"/>--%>
  </head>
  <body>

  <%--   <select name="places1">
      <c:forEach items="${places}" var="pl">
          <option value="${pl.id}">${pl.name}</option>
      </c:forEach>
  </select>--%>

   <form action="Servlet" method="post" >
   <table style="width:100%">
       <tr style="color: red">
           ${err}
       </tr>
       <tr>
        <td><label>Login</label></td>
       <td><input type="text" name="login" value="null"></td>
       </tr>
       <tr>
           <td><label>Пароль</label></td>
           <td><input type="password" name="pass" value="null"></td>
       </tr>
       <tr>
           <td><input type="submit" value="Submit"> </td>
           <td><A HREF="Next.jsp">Registration</A></td>
       </tr>
   </table>


 </form>

  <%--<jsp:forward page="Next.jsp"></jsp:forward>--%>

  </body>
</html>
