<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style>
    <%@include file='/WEB-INF/views/css/main.css' %>
</style>
<html>
<head>
    <title>Login to chat:</title>
</head>
<body>
<h2 class="error">${errormsg}</h2>
<form method="post" id="login" action="${pageContext.request.contextPath}/login"></form>
<div id="login-container">
    <h1 class="title">Enter username:</h1>
    <form id="loginForm" name="loginForm" method="POST">
        <input type="text" name="username"/>
        <button type="submit">Login</button>
    </form>
</div>
</body>
</html>
