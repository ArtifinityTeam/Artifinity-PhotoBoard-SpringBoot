
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>email_finder</title>
    <link rel="stylesheet" href="css/email_exist.css">
</head>
<body>
<nav>
    <div class="logo">Art🖍finity</div>
</nav>
<div class="content-box">
    <div class="title">회원님의 아이디를 확인해 주세요</div>

    <!-- Flash Attribute에서 'email' 값을 받아서 출력 -->
    <p>아이디(이메일): ${email}</p>

    <div class="finder">
        <a href="/login">로그인 하기</a>
        <span class="divider">|</span>
        <a href="/pw_finder">비밀번호 찾기</a>
    </div>

</div>
</body>
</html>
