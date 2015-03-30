<%--
  Created by IntelliJ IDEA.
  User: mikhail
  Date: 30.03.15
  Time: 16:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title></title>
</head>
<body>
<table>
    <tr>
        <td> <h3>Ваше текущее местоположение</h3></td>

    </tr>
    <tr><td>${current}</td></tr>
    </tr>
    <tr>
        <td> <h3>Добавить новый путь</h3></td>
        <form action="AdminControl" method="post">
            <tr><td><label>Новый пункт</label> </td></tr>
            <tr><td><input type="text" name="place" value=""> </td></tr>
            <tr><td><input type="submit"  > </td></tr>
            <input type="hidden" name="index" value="1">
        </form>
    </tr>

    <tr >
        <td> <h3>Добавить новый маршрут</h3></td>
    </tr>
    <form action="AdminControl" method="post">
    <table >

        <tr><td><select name="places1">
            <c:forEach items="${places1}" var="pl">
                <option value="${pl.id}">${pl.name}</option>
            </c:forEach>
        </select></td></tr>

        <tr><td>
                <input type="text" name="time" value="">
            </td></tr>
        <tr><td><select name="places2">
            <c:forEach items="${places2}" var="pl">
                <option value="${pl.id}">${pl.name}</option>
            </c:forEach>
        </select></td></tr>
        <input type="hidden" name="index" value="2">
        <tr><td><input type="submit"></td></tr>

    </table>
    </form>

    </tr>
</table>

</body>
</html>
