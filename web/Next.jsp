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
<h4>NEXT</h4>
<form action="Registration" method="post" >

    <label>Имя</label>
    <input type="text" name="name" value="null"><h1 ></h1>
    <label>Login</label>
    <input type="text" name="login" value="null"><h5>${err}</h5>
    <label>Пароль</label>
    <input type="password" name="pass" value="null">
    <input type="submit" value="Submit">
    <input type="hidden" name="type" value="param">

</form>
</body>
</html>
