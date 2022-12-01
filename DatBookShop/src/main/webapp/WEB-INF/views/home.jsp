<%--
  Created by IntelliJ IDEA.
  User: DratkOMG
  Date: 11/5/2022
  Time: 17:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Home</title>
    <link rel="stylesheet" href="style.css">
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet"
          integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="script.js"></script>
    <style>
        img {
            height: 350px;
            border-radius: 0.8rem;
        }

        .product {
            margin-top: 1.5rem;
        }
        button#up, #btvn {
            margin-top: 1.5rem;
            float: right;
        }
    </style>
</head>
<body>

<jsp:include page="/WEB-INF/parts/_menu.jsp"></jsp:include>

<div class="container">
    <div class="row">
        <jsp:include page="/WEB-INF/parts/_left.jsp"></jsp:include>

        <div class="col-sm-9">
            <div id="content" class="row">
                <c:forEach items="${listBooks}" var="book">
                    <div class="product col-12 col-md-6 col-lg-4">
                        <div class="card">
                            <img class="" src="${book.image}" alt="Book image">
                            <div class="card-body">
                                <h4 class="card-title show_txt">
                                    <a href="show_book_controller?bid=${book.id}" title="${book.title}">${book.title}</a>
                                </h4>
                                <p class="card-text show_txt">${book.description}</p>
                                <div class="row">
                                    <div class="col">
                                        <a href="<c:url value="/add_cart_controller?bid=${book.id}"/>"
                                           class="btn btn-danger btn-block">${book.price} $</a>
                                    </div>
                                    <div class="col">
                                        <button onclick="addToCart(${book.id})" class="btn btn-success btn-block">Add to
                                            cart
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>

            <button onclick="loadMore()" class="btn btn-primary"  ${hidden} id="btvn">Load More</button>

            <button onclick="up()" class="btn btn-primary" hidden = "" id="up">Top</button>


        </div>

    </div>
</div>

<jsp:include page="/WEB-INF/parts/_footer.jsp"/>

</body>
</html>
