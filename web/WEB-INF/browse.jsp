<%-- 
    Document   : browse
    Created on : Nov 22, 2018, 9:22:16 AM
    Author     : Administrator
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <link href="https://fonts.googleapis.com/css?family=Dancing+Script|Hind+Madurai|Montserrat" rel="stylesheet">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <title>JSP Page</title>
        <style>
            h1{
                text-align: center;
                font-family: 'Dancing Script', cursive;
                font-size: 55px;
            }
        </style>
    </head>
    <body>
        <div class="container">
        <h1>BuyStuff Product Browser</h1><br/>
        Welcome, ${sessionScope.user}
        <a class="btn btn-success btn-sm" role="button" href="LoginServices?logout=true">Logout</a><br/><br/>
        <a href="ProductServices?shoppingCart=true">Cart(${sessionScope.cartNum})</a>
        
        <h2>Browse Products</h2>
        <table class="table table-striped">
            <th>Product Description</th>
            <th>In Stock</th>
            <th>Unit Price</th>
            <th># to Order</th>
            <th>Add to Cart</th>
            
            
            <c:forEach var="product" items="${requestScope.productsTable}">
                <form action="ProductServices" method="POST">
                    <tr>
                        <td>
                            ${product.productDesc}
                            <input type="hidden" name='productDesc' value="${product.productDesc}">
                        </td>
                        <td>
                            <fmt:formatNumber type = "number" value = "${product.productUnitsInStock}" />
                        </td>
                        <td>
                            <fmt:setLocale value = "en_US"/>
                            <fmt:formatNumber value = "${product.productUnitPrice}" type = "currency"/>
                            <input type="hidden" name='productUnitPrice' value="${product.productUnitPrice}">
                        </td>
                    
                        <td><input type="text" name="quantity"></td>
                        <td><input class="btn btn-primary" type="submit" value="Add to Cart"></td>
                        <input type="hidden" name='productID' value="${product.productID}">
                    </tr>
                </form>
            </c:forEach>
        </table>
        </div>
    </body>
</html>
