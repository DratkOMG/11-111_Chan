<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html">
    <title>Sign In</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style.css">
</head>
<body>
<h1>Sign In</h1>
<form action="${pageContext.request.contextPath}/sign_in_controller" method="post">
    <input type="text" name="email" placeholder="Email" value="${email}"> <br>
    <input type="password" name="password" placeholder="Password"> <br>
    <p class="ui-state-error-text">${message}</p>
    <input type="submit" value="Sign In">
    <a href="${pageContext.request.contextPath}/sign_up_controller">
        i don't have account
    </a>
</form>
</body>
</html>