<%-- 
    Document   : register
    Created on : Nov 22, 2018, 9:21:41 AM
    Author     : Administrator
--%>

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
        <h1>BuyStuff User Registeration</h1>
        <div class="container">
        <form action="RegisterServices" method="POST">
            <div class="form-group">
            Enter username:<input class="form-control" type="text" name="username"><br/>
            Enter password:<input class="form-control" type="text" name="password1"><br/>
            Confirm password:<input class="form-control" type="text" name="password2"><br/>
            </div>
            <input class="btn btn-primary" type="submit" value="Register">
        </form>
        ${requestScope.message}<br/>
        <a class="btn btn-success btn-sm" href="LoginServices">Login</a>
        </div>
    </body>
</html>
