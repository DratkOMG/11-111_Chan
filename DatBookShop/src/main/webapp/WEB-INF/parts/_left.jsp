<%--
  Created by IntelliJ IDEA.
  User: DratkOMG
  Date: 11/6/2022
  Time: 16:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="col-sm-3">
    <div class="card bg-light mb-3">
        <div class="card-header bg-primary text-white text-uppercase">
            <i class="fa fa-list"></i> Categories
        </div>
        <ul class="list-group category_block" id="list-category-1">
            <c:forEach items="${listCategories}" var="book" end="4">
                <li class="list-group-item text-white ${tag == book.categoriesId?"active":""}">
                    <a href="categories_controller?cid=${book.categoriesId}" class="btn btn-block">${book.categoriesName}</a>
                </li>
            </c:forEach>
        </ul>
        <ul class="list-group category_block" id="list-category-2" hidden="hidden">
            <c:forEach items="${listCategories}" var="book" begin="5">
                <li class="list-group-item text-white ${tag == book.categoriesId?"active":""}">
                    <a href="categories_controller?cid=${book.categoriesId}" class="btn btn-block">${book.categoriesName}</a>
                </li>
            </c:forEach>

        </ul>
        <button onclick="toggle(this)" class="btn btn-primary">Show list</button>
    </div>

    <div class="card bg-light mb-3">
        <div class="card-header bg-danger text-white text-uppercase" style="text-align: center">
            Hot product
        </div>
        <img class="img-fluid" src="${hotBook.image}" width="100%" />
        <div class="card-body">
            <h4 class="card-title show_txt">
                <a href="show_book_controller?bid=${hotBook.id}" title="View Product">${hotBook.title}</a>
            </h4>
            <p class="card-text show_txt">${hotBook.description}</p>
            <p class="bloc_left_price">${hotBook.price} $</p>
        </div>
    </div>

    <div class="card bg-light mb-3">
        <div class="card-header bg-success text-white text-uppercase" style="text-align: center">
            Last product
        </div>
        <img class="img-fluid" src="${lastBook.image}" width="100%" />
        <div class="card-body">
            <h4 class="card-title show_txt">
                <a href="show_book_controller?bid=${lastBook.id}" title="View Product">${lastBook.title}</a>
            </h4>
            <p class="card-text show_txt">${lastBook.description}</p>
            <p class="bloc_left_price">${lastBook.price} $</p>
        </div>
    </div>

</div>
