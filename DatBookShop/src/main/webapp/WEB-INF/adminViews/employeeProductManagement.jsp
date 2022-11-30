<%--
  Created by IntelliJ IDEA.
  User: DratkOMG
  Date: 11/13/2022
  Time: 18:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Employee product management</title>
    <jsp:include page="/WEB-INF/parts/_headerManager.jsp"></jsp:include>
</head>
<body>
<div class="container">
    <div class="table-wrapper">
        <div class="table-title">
            <div class="row">
                <div class="col-sm-6">
                    <h2><b>${sellers_name}</b> Products</h2>
                </div>
                <div class="col-sm-6">
                    <a href="<c:url value="/home_controller"/>" class="btn btn-danger"
                       data-toggle="modal">
                        <span>Dat Book Shop</span>
                    </a>
                    <a href="<c:url value="/admins_manager_controller"/>" class="btn btn-danger"
                       data-toggle="modal">
                        <span>Manage Employee</span>
                    </a>
                </div>
            </div>
        </div>
        <table class="table table-striped table-hover">
            <thead>
            <tr>
                <th>ID</th>
                <th>Title</th>
                <th>Image</th>
                <th>Price</th>
                <th>Author</th>
                <th>Description</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody id="content">
            <c:forEach items="${listBook}" var="book">
                <tr class="product">
                    <td>${book.id}</td>
                    <td>${book.title}</td>
                    <td>
                        <img src="${book.image}">
                    </td>
                    <td>${book.price} $</td>
                    <td>${book.author} </td>
                    <td>${book.description} </td>
                    <td>
                        <a href="<c:url value="/delete_book_controller?bid=${book.id}"/>" class="delete"
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
