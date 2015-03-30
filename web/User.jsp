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
<table>
    <tr>
        <h3>Старые заказы</h3>
    </tr>
    <tr>
        <td></td>
        <td></td>
    </tr>
    <tr><h3>Новые заказы</h3></tr>
    <tr>
        <td></td>
        <td></td>
    </tr>
    <tr><h3>Добавить заказ</h3></tr>
    <tr>
        <form method="post">
            <tr><td><label>Район</label></td></tr>
            <tr><td><select name="places">
                <c:forEach items="${places}" var="pl">
                    <option value="${pl.id}">${pl.name}</option>
                </c:forEach>
            </select></td></tr>
            <tr><td><input type="submit" value="Submit"> </td></tr>
        </form>
    </tr>
</table>



</body>
</html>
