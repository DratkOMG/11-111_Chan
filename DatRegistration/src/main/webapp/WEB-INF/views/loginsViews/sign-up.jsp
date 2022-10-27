<%--
  Created by IntelliJ IDEA.
  User: DratkOMG
  Date: 10/19/2022
  Time: 00:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign Up</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style.css">

</head>
<body>
<h1>Sign Up</h1>

<form action="${pageContext.request.contextPath}/sign_up_controller" method="post">
    <input type="text" name="email" placeholder="Email" value="${email}"> <br>
    <input type="password" name="password" placeholder="Password" value="${password}"> <br>
    <input type="password" name="confirm_password" placeholder="Confirm password" value="${confirm_password}"> <br>
    <input type="text" name="year_of_birth" placeholder="Year of birth" value="${year_of_birth}"> <br>
    <p class="ui-state-error-text">${messError}</p>
    <p class="ui-state-active-text">${messSuccess}</p>
    <input type="submit" value="Sign Up">
    <a href="${pageContext.request.contextPath}/sign_in_controller">
        i have account
    </a>
</form>
</body>
</html>
