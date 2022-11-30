<%--
  Created by IntelliJ IDEA.
  User: DratkOMG
  Date: 11/8/2022
  Time: 00:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Manager Product</title>
    <jsp:include page="/WEB-INF/parts/_headerManager.jsp"></jsp:include>
</head>
<body>
<div class="container">
    <div class="table-wrapper">

        <div class="table-title">
            <div class="row">
                <div class="col-sm-6">
                    <h2>Manage <b>Product</b></h2>
                </div>
                <div class="col-sm-6">
                    <c:if test="${sessionScope.admin == 1}">
                        <a href="#addNewCategoryBook" class="btn btn-success" data-toggle="modal">
                            <i class="material-icons">&#xE147;</i>
                            <span>Add New Category Book</span></a>
                    </c:if>

                    <a href="<c:url value="/add_book_controller"/>" class="btn btn-success" data-toggle="modal">
                        <i class="material-icons">&#xE147;</i>
                        <span>Add New Product</span></a>
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
                <th>Title</th>
                <th>Image</th>
                <th>Price</th>
                <th>Sold</th>
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
                    <td>${book.quantitySold}</td>
                    <td>${book.author} </td>
                    <td>${book.description} </td>
                    <td>
                        <a href="<c:url value="/load_book_controller?bid=${book.id}"/>" class="edit"
                           data-toggle="modal">
                            <i class="material-icons" data-toggle="tooltip" title="Edit">&#xE254;</i>
                        </a>
                        <a href="<c:url value="/delete_book_controller?bid=${book.id}"/>" class="delete"
                           data-toggle="modal">
                            <i class="material-icons" data-toggle="tooltip" title="Delete">&#xE872;</i>
                        </a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <button onclick="loadMoreManager()" class="btn btn-primary" id="btvn"> Load More</button>
        <button onclick="up()" class="btn btn-primary" hidden = "" id="up">Top</button>

    </div>
</div>

<div id="addNewCategoryBook" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <form action="<c:url value="/add_category_controller"/>" method="post">

                <div class="modal-header">
                    <h4 class="modal-title">Add Category Book</h4>
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                </div>

                <div class="modal-body">
                    <div class="form-group">
                        <label> Category Name</label>
                        <input name="category" type="text" class="form-control" required>
                    </div>
                </div>

                <div class="modal-footer">
                    <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
                    <input type="submit" class="btn btn-success" value="Add">
                </div>

            </form>
        </div>
    </div>
</div>
</body>
</html>
