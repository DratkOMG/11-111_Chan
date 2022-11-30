<%--
  Created by IntelliJ IDEA.
  User: DratkOMG
  Date: 11/19/2022
  Time: 10:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>History</title>
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
                    <h2>Purchase <b>History</b></h2>
                </div>
                <div class="col-sm-6">
                    <a href="<c:url value="/home_controller"/>" class="btn btn-danger"
                       data-toggle="modal">
                        <span>Dat Book Shop</span></a>

                    <c:choose>
                        <c:when test="${global != null}">
                            <a href="<c:url value="/purchase_history_controller"/>" class="btn btn-danger"
                               data-toggle="modal">
                                <span>Purchase History</span></a>
                        </c:when>
                        <c:when test="${sessionScope.admin == 1}">
                            <a href="<c:url value="/global_history_controller"/>" class="btn btn-danger"
                               data-toggle="modal">
                                <span>Global Purchase History</span></a>
                        </c:when>
                    </c:choose>

                </div>
            </div>
        </div>
        <table class="table table-striped table-hover">
            <thead>
            <tr>
                <th>Id Order</th>
                <th>Time Order</th>
                <c:if test="${global != null}">
                    <th>Buyer</th>
                </c:if>
                <th>Price</th>
                <th>View</th>
            </tr>
            </thead>
            <tbody id="content">
            <c:forEach items="${listOrders}" var="order">
                <tr class="product">
                    <td>${order.orderId}</td>
                    <td>${order.date}</td>
                    <c:if test="${global != null}">
                        <td>${order.userName}</td>
                    </c:if>
                    <td>${order.price} $</td>
                    <td>
                        <a href="<c:url value="purchase_history_controller?oid=${order.orderId}"/>" class="edit"
                           data-toggle="modal">
                            <i class="material-icons" data-toggle="tooltip" title="Edit">&#xE254;</i>
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
