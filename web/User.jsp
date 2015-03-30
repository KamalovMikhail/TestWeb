<%--
  Created by IntelliJ IDEA.
  User: mikhail
  Date: 30.03.15
  Time: 13:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title></title>

</head>
<body>
<table >
    <tr style=" border: 1px solid black;">
        <h3>Старые заказы</h3>
    </tr>
    <table>
    <tr style=" border: 1px solid black;">
        <c:forEach items="${zakaz2}" var="zk2">
            <tr><td>${zk2.address}</td> <td>${zk2.date}</td></tr>
        </c:forEach>
    </tr>
    </table>
    <tr><h3>Новые заказы</h3></tr>
    <tr>
<table>
        <c:forEach items="${zakaz1}" var="zk1">
    <tr><td>${zk1.address}</td> <td>${zk1.date}</td></tr>
    </c:forEach>
    </tr>
</table>
    <tr><h3>Добавить заказ</h3></tr>
    <tr>
        <form action="UserControl"  method="post">
            <tr><td><label>Район</label></td></tr>
            <tr><td><select name="places">
                <c:forEach items="${places}" var="pl">
                    <option value="${pl.id}">${pl.name}</option>
                </c:forEach>
            </select></td></tr>
            <input type="hidden" value="${idu}" name="idu">
            <tr><td><input type="submit" value="Submit"> </td></tr>
        </form>
    </tr>
</table>



</body>
</html>
