
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Artfinity_signup</title>
    <link rel="stylesheet" href="css/signup.css">

</head>
<body>
<nav>
    <div class="logo">Art🖍finity</div>
</nav>
<div class="content-box">
    <div class="title">회원가입</div>
    <form id="signup-form" action="/signup" method="post">
        <input type="text" id="name" name="userName" placeholder="NICKNAME">
        <span id="name-error" class="error-message"><c:if test="${not empty nameError}"><div class="error">${nameError}</div></c:if></span><br>
        <input type="email" id="email" name="userEmail" placeholder="EMAIL">
        <span id="email-error" class="error-message"><c:if test="${not empty emailError}"><div class="error">${emailError}</div></c:if></span><br>
        <input type="password" id="password" name="userPassword" placeholder="PASSWORD">
        <span id="password-error" class="error-message"></span><br>
        <input type="password" id="password-check" name="password-check" placeholder="PASSWORD 확인">
        <span id="password-check-error" class="error-message"></span><br>
        <input type="tel" id="phone" name="userPhone" placeholder="PHONE ( ' - '를 제외하십시오)">
        <span id="phone-error" class="error-message"></span><br>
        <a href="/signup"><input type="submit" class="signup-btn" value="회원가입"></a>
    </form>
</div>
<script src="js/signup.js"></script>
</body>

</html>
