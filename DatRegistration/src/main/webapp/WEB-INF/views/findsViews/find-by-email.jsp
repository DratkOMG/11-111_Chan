<%--
  Created by IntelliJ IDEA.
  User: DratkOMG
  Date: 10/26/2022
  Time: 00:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Find by email</title>
</head>
<body>

<form action="${pageContext.request.contextPath}/find_by_email" method="post">
    <input type="text" name="search" placeholder="Email" value="${email}"> <br>
    <p>${result}</p>
    <input type="submit" value="Search">
</form>
<form action="${pageContext.request.contextPath}/find_friends_controller" method="post">
    <input type="submit" value="Back">
</form>

</body>
</html>
