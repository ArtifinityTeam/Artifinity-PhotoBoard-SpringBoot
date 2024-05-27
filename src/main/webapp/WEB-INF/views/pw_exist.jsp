<%--
  Created by IntelliJ IDEA.
  User: TJ
  Date: 2024-04-25
  Time: 오후 5:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>email_finder</title>
    <link rel="stylesheet" href="css/pw_exist.css">
</head>
<body>
<nav>
    <div class="logo">Art🖍finity</div>
</nav>
<div class="content-box">
    <div class="title">회원님의 비밀번호를 확인해주세요</div>

    <p>비밀번호: ${password}</p> <!-- 비밀번호를 플래시 애트리뷰트로 표시 -->

    <div class="finder">
        <a href="/login">로그인 하기</a>
        <span class="divider">|</span>
        <a href="/pw_finder">비밀번호 다시 찾기</a>
    </div>
</div>
</body>
</html>
