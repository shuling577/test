<%-- 
    Document   : invoice
    Created on : Nov 22, 2018, 9:22:35 AM
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
            
        </style>
    </head>
    <body>
        <h1>Invoice</h1>
        <div class="container">
        
        Customer: ${sessionScope.user}<br/>
        Date: ${requestScope.date}<br/>
        
        <h2>Order Items</h2>
        <table class="table table-striped">
            <th>Product Description</th>
            <th>Unit Price</th>
            <th>Number Ordered</th>
            <th>Subtotal</th>
            
            <c:set var="total" value="0" scope="page"/>
            <c:forEach var="detail" items="${sessionScope.shoppingCart1}">
                    <tr>
                        <td>${detail.productDesc}</td>
                        <td><fmt:setLocale value = "en_US"/>
                            <fmt:formatNumber value = "${detail.productUnitPrice}" type = "currency"/>
                        </td>
                        <td>${detail.numberUnitsOrdered}</td>
                    
                        <td><fmt:setLocale value = "en_US"/>
                            <fmt:formatNumber value = "${detail.productUnitPrice*detail.numberUnitsOrdered}" type = "currency"/>
                        </td>
                    </tr>
                <c:set var="total" value="${total+detail.productUnitPrice*detail.numberUnitsOrdered}"/>
            </c:forEach>
            <tr>
                <td>
                    TOTAL:<fmt:setLocale value = "en_US"/>
                            <fmt:formatNumber value = "${total}" type = "currency"/>
                </td>        
            </tr>
        </table>
        <p style="color: crimson">${requestScope.message}</p><br/>
        <a class="btn btn-default btn-sm" role="button" href="ProductServices?back=true">Back to Browse Page</a><br/>
        </div>
    </body>
</html>
