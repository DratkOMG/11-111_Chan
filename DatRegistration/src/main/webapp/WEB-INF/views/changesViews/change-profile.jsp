<%@ page import="ru.itis.datregistration.models.User" %>
<%@ page import="ru.itis.datregistration.services.impl.UserServiceImpl" %>
<%@ page import="ru.itis.datregistration.repositories.impl.UsersRepositoryImpl" %><%--
  Created by IntelliJ IDEA.
  User: DratkOMG
  Date: 10/23/2022
  Time: 15:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Change profile</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style.css">
</head>
<body>
<%
    String email = (String) session.getAttribute("root_email");
    User user = new UsersRepositoryImpl().getProfileByEmail(email);
    String username = user.getUserName();
    long numberPhone = user.getPhoneNumber();
%>

<form action="${pageContext.request.contextPath}/change_username_controller" method="post">
    <h1>
        Change profile
    </h1>
    <p>Your username: <%=username%></p>
    <input type="text" name="user_name" placeholder="Username" value="${username}"> <br>
    <p class="ui-state-error-text">${messError1} </p>
    <p class="ui-state-active-text">${messSuccess1}</p>
    <input type="submit" value="Change">
</form>

<form action="${pageContext.request.contextPath}/change_number_phone_controller" method="post">
    <p>Your phone number: <%=numberPhone%></p>
    <input type="text" name="phone_number" placeholder="Phone number" value="${number_phone}"> <br>
    <p class="ui-state-error-text">${messError2} </p>
    <p class="ui-state-active-text">${messSuccess2}</p>
    <input type="submit" value="Change">
</form>

<form action="${pageContext.request.contextPath}/profile_controller" method="post">
    <input type="submit" value="Back to profile">
</form>

</body>
</html>
