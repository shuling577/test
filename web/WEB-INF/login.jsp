<%-- 
    Document   : login
    Created on : Nov 22, 2018, 9:21:26 AM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <link href="https://fonts.googleapis.com/css?family=Dancing+Script|Hind+Madurai" rel="stylesheet">
        <style>
            h1 {
                text-align: center;
                font-size: 55px;
                margin-top: 20px;
                font-family: 'Dancing Script', cursive;
            }
            form{
                margin-left: 480px;
                margin-top: 50px;
                font-size: 25px;
                font-family: 'Hind Madurai', sans-serif;
            }
            a{
                margin-left: 480px;
            }
            
            
        </style>
        <link rel="stylesheet" href="/login.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <title>WEB FINAL PROJECT</title>
    </head>
    <body>
        <h1>BuyStuff Login</h1>
        <form action="LoginServices" method="POST">
            Username:   <input type="text" name="username" value='${cookie.username.value}'><br/><br/>
            Password:    <input type="password" name="password"><br/><br/>
            <input id="login" class="btn btn-primary btn-lg" type="submit" value="Login"><br/><br/>
            <!--the expression in the next line means when the user login next time,the browser checks
                the cookies. if the cookies is not null, it will check the check box and show the saved
                information from the user-->
            <input type="checkbox" name="rememberMe" ${cookie.username.value != null? 'checked':''}>Remember Me<br/>
            <p style="color: crimson">${requestScope.message}</p><br/>
        </form>
        
        <a class="btn btn-success" role="button" href="RegisterServices">Register</a>
    </body>
</html>
