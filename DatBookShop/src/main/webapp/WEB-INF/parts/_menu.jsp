<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: DratkOMG
  Date: 11/6/2022
  Time: 15:04
  To change this template use File | Settings | File Templates.
--%>
<style>
    .nav-link, .navbar-brand {
        color: white;
    }

    a:hover {
        color: black;
    }

    form {
        display: block;
        margin-block-end: 0em;
    }
</style>

<nav class="navbar navbar-expand-md navbar-blue text-light">
    <div class="container">
        <a class="navbar-brand" href="<c:url value="/home_controller"/>"> DatBookShop </a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault"
                aria-controls="navbarsExampleDefault" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse justify-content-end" id="navbarsExampleDefault">
            <ul class="navbar-nav m-auto">
                <c:choose>
                    <c:when test="${sessionScope.root_id==null}">
                        <li class="nav-item">
                            <a class="nav-link" href="<c:url value="/sign_in_controller"/>">Login</a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="nav-item">
                            <a class="nav-link" href="<c:url value="/sign_in_controller"/>">Logout</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="<c:url value="/profile_controller"/>">Profile</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="<c:url value="/purchase_history_controller"/>">Purchase History</a>
                        </li>
                        <c:if test="${sessionScope.seller == 1}">
                            <li class="nav-item">
                                <a class="nav-link" href="<c:url value="/manager_controller"/>">Manager Product</a>
                            </li>
                            <c:if test="${sessionScope.admin == 1}">
                                <li class="nav-item">
                                    <a class="nav-link" href="<c:url value="/admins_manager_controller"/>">Manage
                                        Employees</a>
                                </li>
                            </c:if>
                        </c:if>

                    </c:otherwise>
                </c:choose>
            </ul>

            <div class="form-inline my-2 my-lg-0">
                <div class="input-group input-group-sm">
                    <input oninput="liveSearch(this)" name="search" type="text" class="from-control" aria-label="Small"
                           aria-describedby="inputGroup-sizing-sm" placeholder="Search...">
                </div>
                <form action="<c:url value="/load_cart_controller"/>" method="post">
                    <button type="submit" class="btn btn-success btn-sm ml-3" onclick="">
                        <i class="fa fa-shopping-cart"></i> Cart
                        <span class="badge badge-light" id="count-product"> ${countProduct}</span>
                    </button>
                </form>
            </div>

        </div>
    </div>
</nav>

<section class="jumbotron text-center">
    <div class="container">
        <h1 class="jumbotron-heading"> Dat Book Shop </h1>
        <p class="lead text-muted mb-0"> Beautiful books at affordable prices and an unforgettable experience </p>
    </div>
</section>

