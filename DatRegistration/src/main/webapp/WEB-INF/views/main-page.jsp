<%--
  Created by IntelliJ IDEA.
  User: DratkOMG
  Date: 10/26/2022
  Time: 00:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Main Page</title>
</head>
<body>
<h1>
    DatRegistration
</h1>

<form action="${pageContext.request.contextPath}/profile_controller" method="post">
    <input type="submit" value="My Profile">
</form>
<form action="${pageContext.request.contextPath}/find_friends_controller" method="post">
    <input type="submit" value="Find your friends">
</form>
<form action="${pageContext.request.contextPath}/sign_in_controller" method="get">
    <input type="submit" value="Log Out">

</form>

</body>
</html>
