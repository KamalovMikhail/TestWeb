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
   Test
      <jsp:text>JSPText</jsp:text>
      <c:out value="JSTL"/>
  </head>
  <body>

  <select name="places1">
      <c:forEach items="${places}" var="pl">
          <option value="${pl.id}">${pl.name}</option>
      </c:forEach>
  </select>

<form action="Servlet" method="post" >
    <input type="text" name="place" value="">
    <input type="submit" value="Submit">
</form>


  <%--<jsp:forward page="Next.jsp"></jsp:forward>--%>

  </body>
</html>
