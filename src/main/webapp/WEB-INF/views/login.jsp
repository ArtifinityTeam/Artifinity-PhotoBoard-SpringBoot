
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Artfinity_login</title>
    <link rel="stylesheet" href="css/login.css">
</head>
<body>
<nav>
    <div class="logo">Art🖍finity</div>
</nav>
<div class="content-box">
    <div class="title">로그인</div>
    <form action="/login" method="post">
        <input type="email" name="email" placeholder="EMAIL"><br>
        <span id="email-error" class="error-message"></span><br>
        <input type="password" name="password" placeholder="PASSWORD"><br>
        <span id="password-error" class="error-message"></span><br>
        <input type="submit" class="login-btn" value="로그인" >

    </form>
    <div class="finder">
        <a href="/email_finder">이메일 찾기</a>
        <span class="divider">|</span>
        <a href="/pw_finder">비밀번호 찾기</a>
        <span class="divider">|</span>
        <a href="/signup">회원가입</a>

    </div>
</div>
<script src="js/login.js"></script>
</body>
</html>
