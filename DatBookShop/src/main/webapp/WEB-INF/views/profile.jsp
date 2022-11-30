<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: DratkOMG
  Date: 11/7/2022
  Time: 14:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Profile</title>
    <link rel="stylesheet" href="style.css">
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet"
          integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
    <script src="script.js"></script>

</head>
<body>
<jsp:include page="/WEB-INF/parts/_menu.jsp"></jsp:include>

<div class="container rounded bg-white mt-5 mb-5">
    <div class="row">
        <div class="col-md-3 border-right">
            <div class="d-flex flex-column align-items-center text-center p-3 py-5">
                <img class="rounded-circle mt-5" width="150px"
                     src="https://st3.depositphotos.com/15648834/17930/v/600/depositphotos_179308454-stock-illustration-unknown-person-silhouette-glasses-profile.jpg">
                <span class="font-weight-bold">${userProfile.userName}</span>
                <span class="text-black-50">${userProfile.email}</span>
                <br>
                <form action="<c:url value="/change_profile_controller"/>" method="get">
                    <span class="font-weight-bold">Balance: ${userProfile.balance} $</span>
                    <br>

                    <input type="text" name="donate" class="form-control" placeholder="Donate">
                    <br>

                    <p class="ui-state-error-text">${errorDonate}</p>
                    <p class="ui-state-active-text">${niceDonate}</p>
                    <button type="submit" class="btn btn-success">
                        <i class="material-icons">&#xE147;</i>
                        <span>Donate</span></button>
                </form>


            </div>
        </div>

        <form action="<c:url value="/change_profile_controller"/>" method="post" class="col-md-5 border-right">
            <div class="p-3 py-5">
                <div class="d-flex justify-content-between align-items-center mb-3">
                    <h4 class="text-right">Profile Settings</h4>
                </div>
                <div class="row mt-3">
                    <div class="col-md-12">
                        <label class="labels">Username</label>
                        <input type="text" name="username" class="form-control" value="${userProfile.userName}"
                               placeholder="Username">

                        <p class="ui-state-error-text">${errorUsername}</p>
                        <p class="ui-state-active-text">${niceUsername}</p>
                    </div>
                    <div class="col-md-12">
                        <label class="labels">Mobile Number</label>
                        <input type="text" name="phone_number" class="form-control"
                               placeholder="Phone number" value="${userProfile.phoneNumber}">

                        <p class="ui-state-error-text">${errorPhoneNumber}</p>
                        <p class="ui-state-active-text">${nicePhoneNumber}</p>
                    </div>
                    <div class="col-md-12">
                        <label class="labels">City</label>

                        <select name="city" class="form-control">
                            <option></option>
                            <option ${userProfile.city.equals("Moscow")?"selected":""}> Moscow</option>
                            <option ${userProfile.city.equals("Kazan")?"selected":""}> Kazan</option>
                            <option ${userProfile.city.equals("Saint Petersburg")?"selected":""}> Saint Petersburg</option>
                            <option ${userProfile.city.equals("Novosibirsk")?"selected":""}> Novosibirsk</option>
                            <option ${userProfile.city.equals("Yekaterinburg")?"selected":""}> Yekaterinburg</option>
                            <option ${userProfile.city.equals("Nizhny Novgorod")?"selected":""}> Nizhny Novgorod</option>
                            <option ${userProfile.city.equals("Chelyabinsk")?"selected":""}> Chelyabinsk</option>
                            <option ${userProfile.city.equals("Krasnoyarsk")?"selected":""}> Krasnoyarsk</option>
                            <option ${userProfile.city.equals("Samara")?"selected":""}> Samara</option>
                            <option ${userProfile.city.equals("Ufa")?"selected":""}> Ufa</option>
                        </select>

                        <p class="ui-state-active-text">${niceCity}</p>
                    </div>
                    <div class="col-md-12">
                        <label class="labels">Sex </label> <br>

                        <select name="sex" class="form-control">
                            <option></option>
                            <option ${userProfile.sex.equals("Male")?"selected":""} > Male</option>
                            <option ${userProfile.sex.equals("Female")?"selected":""} > Female</option>
                        </select>

                        <p class="ui-state-active-text">${niceSex}</p>
                    </div>
                    <div class="col-md-12">
                        <label class="labels">Age</label>
                        <input type="text" name="age" class="form-control"
                               placeholder="Age" value="${userProfile.age}">

                        <p class="ui-state-error-text">${errorAge}</p>
                        <p class="ui-state-active-text">${niceAge}</p>
                    </div>
                </div>

                <div class="mt-5 text-center">
                    <button class="btn btn-primary profile-button" type="submit">Save Profile
                    </button>
                </div>
            </div>
        </form>

        <form action="<c:url value="/change_pass_controller"/>" method="post" class="col-md-4">
            <div class="p-3 py-5">
                <div class="d-flex justify-content-between align-items-center mb-3">
                    <h4 class="text-right">Change password</h4>
                </div>
                <br>
                <div class="col-md-12">
                    <label class="labels">Password</label>
                    <input type="password" name="old_password" class="form-control"
                           placeholder="Old password" value="${old_password}">
                </div>
                <div class="col-md-12">
                    <label class="labels">New password</label>
                    <input type="password" name="new_password" class="form-control"
                           placeholder="New password" value="${new_password}">
                </div>
                <div class="col-md-12">
                    <label class="labels">Repeat new password</label>
                    <input type="password" name="confirm_new_password" class="form-control"
                           placeholder="Repeat new password" value="${confirm_new_password}">
                </div>

                <p class="ui-state-error-text">${errorChange}</p>
                <p class="ui-state-active-text">${niceChange}</p>

                <div class="mt-5 text-center">
                    <button class="btn btn-primary profile-button" type="submit">Change</button>
                </div>
            </div>

        </form>
    </div>
</div>
<div id="donate" class="modal fade">
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
<jsp:include page="/WEB-INF/parts/_footer.jsp"></jsp:include>
</body>
</html>
