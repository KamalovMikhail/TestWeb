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

    <label>Имя</label>
    <input type="text" name="name" value="null"><h1 >${err}</h1>
    <label>Пароль</label>
    <input type="password" name="pass" value="null">
    <input type="submit" value="Submit">
    <input type="hidden" name="type" value="param">
    <A HREF="Next.jsp">Registration</A>
</form>

  <%--<jsp:forward page="Next.jsp"></jsp:forward>--%>

  </body>
</html>
