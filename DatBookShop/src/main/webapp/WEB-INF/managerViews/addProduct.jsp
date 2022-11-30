<%--
  Created by IntelliJ IDEA.
  User: DratkOMG
  Date: 11/12/2022
  Time: 20:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Add Book</title>
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link href="manager.css" rel="stylesheet" type="text/css"/>
    <style>
        textarea.form-control {
            resize: vertical;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="table-wrapper">
        <div class="table-title">
            <div class="row">
                <div class="col-sm-6">
                    <h2><b>Add Book</b></h2>
                </div>
            </div>
        </div>

        <div class="modal-dialog">
            <div class="modal-content">
                <form action="<c:url value="/add_book_controller"/>" method="post">
                    <div class="modal-body">
                        <div class="form-group">
                            <label>Title </label>
                            <input name="title" value="${title}" type="text" class="form-control" required>
                            <p class="ui-state-error-text">${errorTitle}</p>
                        </div>
                        <div class="form-group">
                            <label>Image </label>
                            <input name="image" value="${image}" type="text" class="form-control" required>
                            <p class="ui-state-error-text">${errorImage}</p>
                        </div>
                        <div class="form-group">
                            <label>Price </label>
                            <input name="price" value="${price}" type="text" class="form-control" required>
                            <p class="ui-state-error-text">${errorPrice}</p>
                        </div>
                        <div class="form-group">
                            <label>Author </label>
                            <input name="author" value="${author}" class="form-control" required>
                            <p class="ui-state-error-text">${errorAuthor}</p>
                        </div>
                        <div class="form-group">
                            <label>Description </label>
                            <textarea name="description" class="form-control" required>${description}</textarea>
                            <p class="ui-state-error-text">${errorDescription}</p>
                        </div>
                        <div class="form-group">
                            <label>Category</label>
                            <select name="category" class="form-select" aria-label="Default select example">
                                <c:forEach items="${listCategory}" var="category">
                                    <option value="${category.categoriesId}">${category.categoriesName}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <p class="ui-state-active-text">${Done}</p>

                    </div>
                    <div class="modal-footer">
                        <a href="<c:url value="/manager_controller"/>">
                            <input type="button" class="btn btn-default" value="Cancel">
                        </a>
                        <input type="submit" class="btn btn-info" value="Save">
                    </div>
                </form>
            </div>
        </div>


    </div>
</div>

</body>
</html>
