<%-- 
    Document   : cart
    Created on : Nov 22, 2018, 9:22:25 AM
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
        </style>
    </head>
    <body>
        <div class="container">
        <h1>BuyStuff Shopping Cart</h1><br/>
        Welcome, ${sessionScope.user}
        <a class="btn btn-success btn-sm" role="button" href="LoginServices?logout=true">Logout</a><br/><br/>
        <a class="btn btn-default btn-sm" role="button" href="ProductServices?back=true">Back to Browse page</a><br/>
        
        <h2>Shopping Cart</h2>
        
        <table class="table table-striped">
            <th>Product Description</th>
            <th>Unit Price</th>
            <th>Number Ordered</th>
            <th>Subtotal</th>
            <th>Delete</th>
            
            <c:set var="total" value="0" scope="page"/>
            <c:set var="count" value="0" scope="page" />
            <c:forEach var="cart" items="${sessionScope.shoppingCart1}">
                <form action="ShoppingCartServices" method="POST">
                    
                    <tr>
                        <td>${cart.productDesc}</td>
                        <td>
                            <fmt:setLocale value = "en_US"/>
                            <fmt:formatNumber value = "${cart.productUnitPrice}" type = "currency"/>
                        </td>
                        <td>
                            <fmt:formatNumber type = "number" value = "${cart.numberUnitsOrdered}" />
                        </td>
                    
                        <td>
                            <fmt:setLocale value = "en_US"/>
                            <fmt:formatNumber value = "${cart.productUnitPrice*cart.numberUnitsOrdered}" type = "currency"/>
                        </td>
                        <td><input class="btn btn-primary" type="submit" value="Delete"></td>
                        <input type="hidden" name='count' value="${count}">
                    </tr>
                </form>
                <c:set var="total" value="${total+cart.productUnitPrice*cart.numberUnitsOrdered}"/>
                <c:set var="count" value="${count+1}" scope="page" />
            </c:forEach>
            <tr>
                <td>
                    TOTAL:  <fmt:setLocale value = "en_US"/>
                            <fmt:formatNumber value = "${total}" type = "currency"/> 
                    <a class="btn btn-danger" role="button" href="InvoiceServices">PAY</a>
                </td>        
            </tr>
        </table>
        ${requestScope.message}
        </div>
    </body>
</html>
