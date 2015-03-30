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

    <tr>
        <td> <h3>Добавить новый маршрут</h3></td>


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
    <tr>

        <td> <h3>Новые заказы</h3></td>
        <table > <c:forEach items="${zakaz1}" var="zk1">
    <tr><td style=" border: 1px solid black;">${zk1.address}</td> <td style=" border: 1px solid black;">${zk1.date}</td><td></td></tr>

    </c:forEach></table>
     <form action="AdminControl" method="post">

            <input type="hidden" name="index" value="3">
            <tr><td><input type="submit"></td></tr>
        </form>
    </tr>

    <tr>

        <td> <h3>Выбор маршрута</h3></td>
        <form action="AdminControl" method="post">
            <h4>${way}</h4>
            <input type="hidden" name="index" value="4">
            <tr><td><input type="submit"></td></tr>
        </form>

    </tr>

</table>

</body>
</html>
