<%--
  Created by IntelliJ IDEA.
  User: DratkOMG
  Date: 11/20/2022
  Time: 23:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Order Information</title>
    <jsp:include page="/WEB-INF/parts/_headerManager.jsp"></jsp:include>
    <style>
        .table {
            text-align: center;
        }
        th {
            text-align: center;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="table-wrapper">

        <div class="table-title">
            <div class="row">
                <div class="col-sm-6">
                    <h2>Order Information</h2>
                </div>
                <div class="col-sm-6">
                    <a href="<c:url value="/home_controller"/>" class="btn btn-danger"
                       data-toggle="modal">
                        <span>Dat Book Shop</span></a>
                    <a href="<c:url value="/purchase_history_controller"/>" class="btn btn-danger"
                       data-toggle="modal">
                        <span>Purchase History</span></a>
                </div>
            </div>
        </div>
        <table class="table table-striped table-hover">
            <thead>
            <tr>
                <th>Image</th>
                <th>Title</th>
                <th>Price</th>
                <th>Quantity</th>
            </tr>
            </thead>
            <tbody id="content">
            <c:forEach items="${informations}" var="info">
                <tr class="product">
                    <td>
                        <img src="${info.image}">
                    </td>
                    <td>${info.title}</td>
                    <td>${info.price} $</td>
                    <td>${info.quantitySold} </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

    </div>
</div>

</body>
</html>
