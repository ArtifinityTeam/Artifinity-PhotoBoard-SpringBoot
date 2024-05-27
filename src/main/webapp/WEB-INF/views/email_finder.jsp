
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>email_finder</title>
    <link rel="stylesheet" href="css/email_finder.css">
</head>
<body>
<nav>
    <div class="logo">Art🖍finity</div>
</nav>
<div class="content-box">
    <div class="title">이메일 찾기</div>
    <form action="" method="post">
        <input type="text" name="userName" placeholder="NAME"><br>
        <input type="tel" name="userPhone" placeholder="PHONE ( ' - '를 제외하세요)"><br>
        <input type="submit" class="email_finder-btn" value="이메일 찾기" >

    </form>
    <div class="finder">
        <a href="/signup">회원가입</a>
        <a href="/login">로그인</a>

    </div>
</div>
</body>
</html>