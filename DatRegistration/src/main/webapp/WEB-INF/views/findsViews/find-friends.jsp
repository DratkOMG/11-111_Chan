<%--
  Created by IntelliJ IDEA.
  User: DratkOMG
  Date: 10/26/2022
  Time: 00:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Find Your Friends</title>
</head>
<body>

<form action="${pageContext.request.contextPath}/find_by_email" method="get">
    <input type="submit" value="Find by email">
</form>

<form action="${pageContext.request.contextPath}/find_by_number_phone" method="get">
    <input type="submit" value="Find by number phone">
</form>

<form action="${pageContext.request.contextPath}/find_by_username" method="get">
    <input type="submit" value="Find by username">
</form>

<form action="${pageContext.request.contextPath}/sign_in_controller" method="post">
    <input type="submit" value="Back to main page">
</form>

</body>
</html>
