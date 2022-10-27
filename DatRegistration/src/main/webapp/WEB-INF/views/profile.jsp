<%@ page import="ru.itis.datregistration.models.Account" %>
<%@ page import="ru.itis.datregistration.models.User" %><%--
  Created by IntelliJ IDEA.
  User: DratkOMG
  Date: 10/18/2022
  Time: 00:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Welcome</title>
</head>
<body>
<h1> Profile </h1>
<%@page import="ru.itis.datregistration.models.User" %>
<%@page import="ru.itis.datregistration.repositories.impl.UsersRepositoryImpl" %>
<%@ page import="ru.itis.datregistration.repositories.UsersRepository" %>
<%

    UsersRepository usersRepository = new UsersRepositoryImpl();
    String email = (String) request.getSession().getAttribute("root_email");
    String password = (String) request.getSession().getAttribute("root_password");
    User user = usersRepository.getProfileByEmail(email);
    String username = user.getUserName();
    Integer age = user.getAge();
    Long phoneNumber = user.getPhoneNumber();
%>
<form action="${pageContext.request.contextPath}/sign_in_controller" method="post">
    <p>Your email is:
        <%=email%>
    </p>
    <p>Your password is:
        <%=password%>
    </p>
    <p>Your username is:
        <%=username%>
    </p>
    <p>Your age:
        <%=age%>
    </p>
    <p>Your phone number:
        <%=phoneNumber%>
    </p>
    <a href="${pageContext.request.contextPath}/change_profile_controller">
        Change profile
    </a> <br>

    <a href="${pageContext.request.contextPath}/change_pass_controller">
        I want to change my password
    </a> <br>

    <input type="submit" value="Back to main page">
</form>
</body>
</html>
