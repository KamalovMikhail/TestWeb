<%--
  Created by IntelliJ IDEA.
  User: mikhail
  Date: 07.03.15
  Time: 9:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>NEXT</title>
</head>
<body>

<form action="Registration" method="post" >
    <table style="width:100%">
        <tr style="color: red">
            ${err}
        </tr>
        <tr>
            <td><label>Имя</label></td>
            <td><input type="text" name="name" value="null"></td>
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
        </tr>
    </table>


</form>

</body>
</html>
