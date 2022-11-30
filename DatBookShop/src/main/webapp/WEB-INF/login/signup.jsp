<%--
  Created by IntelliJ IDEA.
  User: DratkOMG
  Date: 11/7/2022
  Time: 11:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Sign Up</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css"
          integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">
    <link rel="stylesheet" href="<c:url value="/login-style.css"/>">
    <script src="script.js"></script>
</head>

<body>
<div id="logreg-forms">
    <form action="${pageContext.request.contextPath}/sign_up_controller" method="post" class="form-signup">
        <h1 class="h3 mb-3 font-weight-normal" style="text-align: center"> Sign Up</h1>

        <input type="text" name="username" id="user-name" class="form-control" placeholder="Full name" required=""
               autofocus="" value="${username}">
        <input type="email" name="email" id="user-email" class="form-control" placeholder="Email address" required
               autofocus="" value="${email}">
        <input type="password" name="password" id="user-pass" class="form-control" placeholder="Password" required
               autofocus="">
        <input type="password" name="confirm_password" id="user-repeatpass" class="form-control"
               placeholder="Repeat Password" required
               autofocus="">

        <hr>

        <p class="ui-state-error-text">${messError}</p>
        <p class="ui-state-active-text">${messSuccess}</p>

        <button class="btn btn-primary btn-block" type="submit">
            <i class="fas fa-user-plus"></i> Sign Up
        </button>

        <a href="${pageContext.request.contextPath}/sign_in_controller" id="cancel_signup">
            <i class="fas fa-angle-left"></i> Back
        </a>
    </form>

</div>
</body>
</html>
