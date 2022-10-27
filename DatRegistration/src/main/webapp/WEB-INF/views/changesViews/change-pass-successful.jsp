<%--
  Created by IntelliJ IDEA.
  User: DratkOMG
  Date: 10/21/2022
  Time: 01:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Change Password Successful</title>
</head>
<body>

<h1>Password changed</h1>
<form action="${pageContext.request.contextPath}/sign_in_controller" method="post">
    <input type="submit" value="Back to main page">
</form>

</body>
</html>
