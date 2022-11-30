<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: DratkOMG
  Date: 11/11/2022
  Time: 13:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Manager Employees</title>
    <jsp:include page="/WEB-INF/parts/_headerManager.jsp"></jsp:include>
</head>
<body>

<div class="container">
    <div class="table-wrapper">
        <div class="table-title">
            <div class="row">
                <div class="col-sm-6">
                    <h2>Manage <b>Employees</b></h2>
                </div>
                <div class="col-sm-6">
                    <a href="<c:url value="/home_controller"/>" class="btn btn-danger"
                       data-toggle="modal">
                        <span>Dat Book Shop</span></a>
                </div>
            </div>
        </div>
        <table class="table table-striped table-hover">
            <thead>
            <tr>
                <th>ID</th>
                <th>Email</th>
                <th>Username</th>
                <th>Age</th>
                <th>Sex</th>
                <th>City</th>
                <th>Phone Number</th>
                <th>Action</th>
            </tr>
            </thead>
            <tbody id="content">

            <c:forEach items="${listEmployees}"  var="employee" >
                <tr class="product">
                    <td>${employee.userId}</td>
                    <td>${employee.email}</td>
                    <td>${employee.userName}</td>
                    <td>${employee.age}</td>
                    <td>${employee.sex} </td>
                    <td>${employee.city}</td>
                    <td>${employee.phoneNumber}</td>
                    <td>
                        <a href="<c:url value="/load_employee_controller?eid=${employee.userId}"/>" class="edit"
                           data-toggle="modal">
                            <i class="material-icons" data-toggle="tooltip" title="Edit">&#xE254;</i>
                        </a>
                        <a href="<c:url value="/delete_employee_controller?eid=${employee.userId}"/>" class="delete"
                           data-toggle="modal">
                            <i class="material-icons" data-toggle="tooltip" title="Delete">&#xE872;</i>
                        </a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>


</body>
</html>
