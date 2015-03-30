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
    <tr >
        <td> <h3>Старые заказы</h3></td>
    </tr>
    <table >
    <tr >
        <c:forEach items="${zakaz2}" var="zk2">
            <tr><td style=" border: 1px solid black;">${zk2.address}</td> <td style=" border: 1px solid black;">${zk2.date}</td></tr>
        </c:forEach>
    </tr>
    </table>
    <tr><td><h3>Новые заказы</h3></td></tr>
    <tr>
<table >
        <c:forEach items="${zakaz1}" var="zk1">
    <tr><td style=" border: 1px solid black;">${zk1.address}</td> <td style=" border: 1px solid black;">${zk1.date}</td></tr>
    </c:forEach>
    </tr>
</table>
    <tr><td ><h3>Добавить заказ</h3></td></tr>
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
