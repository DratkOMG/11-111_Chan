<%--
  Created by IntelliJ IDEA.
  User: DratkOMG
  Date: 10/20/2022
  Time: 00:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Change Password</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style.css">

</head>
<body>
<%
    String test = (String) session.getAttribute("root_password");
%>

<form action="${pageContext.request.contextPath}/change_pass_controller" method="post">
    <h1>
        Change password
    </h1>

    <input type="text" name="old_password" placeholder="Old password" value="${old_password}"> <br>
    <input type="text" name="new_password" placeholder="New Password" value="${new_password}"> <br>
    <input type="text" name="confirm_new_password" placeholder="Confirm new Password" value="${confirm_new_password}">
    <br>
    <p class="ui-state-error-text">${mess}</p> <br>
    <input type="submit" value="Change">
</form>
<form action="${pageContext.request.contextPath}/profile_controller" method="post">
    <input type="submit" value="Back to profile">
</form>
</body>
</html>
