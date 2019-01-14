<%-- 
    Document   : admin
    Created on : Nov 22, 2018, 9:22:45 AM
    Author     : Administrator
--%>

<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <link href="https://fonts.googleapis.com/css?family=Dancing+Script|Hind+Madurai|Montserrat" rel="stylesheet">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <style>
            h1{
                text-align: center;
                font-family: 'Dancing Script', cursive;
                font-size: 55px;
            }
            .container{
                font-family: 'Hind Madurai', sans-serif;
                font-size: 15px;
            }
            #last{
                margin-bottom: 30px;
            }
        </style>
    </head>
    <body>
        <h1>BuyStuff Admin Page</h1>
        <div class="container">
        Welcome, ${sessionScope.user}
        <a class="btn btn-success btn-sm" role="button" href="LoginServices?logout=true">Logout</a><br/><br/>
        
        <h2>Users</h2>
        <table class="table table-striped">
            <th>Username</th>
            <th>Password</th>
            <th>User Type</th>
            <th>Delete</th>
            
            <c:forEach var="user" items="${requestScope.userList}">
                <form action="AdminServices" method="POST">
                    <tr>
                        <td>${user.username}</td>
                        <td><input class="btn btn-default" type="submit" name="action" value="Reset"></a></td>
                        <td><input class="btn btn-default" type="submit" name="action" value="${user.userType}"></td>
                        <td><input class="btn btn-default" type="submit" name="action" value="Delete"></td>
                        <input type="hidden" name='username' value="${user.username}">
                    </tr>
                </form>
            </c:forEach>
        </table>
        ${requestScope.message}
        
        <h3>Add User</h3>
        <form action="AdminServices" method="POST">
            <div class="form-group">
            Username:<input class="form-control" type="text" name="usernameA"><br/>
            Password:<input class="form-control" type="text" name="password1"><br/>
            Confirm password:<input class="form-control" type="text" name="password2"><br/>
            </div>
            <input class="btn btn-primary" type="submit" value="Add User">
        </form><br/><br/>
        
        <h2>Products</h2>
        <table class="table table-striped">
            <th>Description</th>
            <th>Unit Price</th>
            <th>In Stock</th>
            <th>Update</th>
            <th>Delete</th>
            
            <c:forEach var="product" items="${requestScope.productList}">
                <form action="AdminServices" method="POST">
                    <tr>
                        <td><input type="text" name="productDesc" value="${product.productDesc}"></td>
                        <td><input type="text" name="productUnitPrice1" value="<fmt:setLocale value = "en_US"/>
                            <fmt:formatNumber value = "${product.productUnitPrice}" type = "currency"/>"</td>
                        <td><input type="text" name="productUnitsInStock" value="<fmt:formatNumber type = "number" value = "${product.productUnitsInStock}" />"</td>
                        <td><input class="btn btn-default" type="submit" name="action1" value="Update"></td>
                        <td><input class="btn btn-default" type="submit" name="action1" value="Delete"></td>
                        <input type="hidden" name='productID' value="${product.productID}">
                        <input type="hidden" name='productUnitPrice' value="${product.productID}">
                    </tr>
                </form>
            </c:forEach>
        </table>
        ${requestScope.message1}
        
        <h3>Add Product</h3>
        <form action="AdminServices" method="POST">
            <div class="form-group">
            Description:<input class="form-control" type="text" name="description"><br/>
            Unit Price:<input class="form-control" type="text" name="unitPrice"><br/>
            Units in stock:<input class="form-control" type="text" name="unitsInStock"><br/>
            </div>
            <input id="last" class="btn btn-primary" type="submit" value="Add Product">
        </form>
        </div>
    </body>
</html>
