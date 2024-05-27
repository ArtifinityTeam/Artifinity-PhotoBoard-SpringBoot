<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Artfinity</title>
    <link rel="stylesheet" href="css/Changing.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>

  <!-- 고정 Ui -->
  <nav>
      <div class="logo"><a href="/">Art🖍finity</a></div>
      <div class="nav-items">
          <a href="/projects">프로젝트</a>
          <a href="/background_img">배경화면</a>
          <a href="/creators">크리에이터</a>
          <a href="/notice_list">외주 게시판</a>
      </div>
      <div class="nav-right">
          <a href="#" class="btn btn-secondary dropdown-toggle" role="button" data-bs-toggle="dropdown" aria-expanded="false">
              <img src="upload/${user.userImage}" alt="마이페이지">
          </a>
      </div>
  </nav>

      <!-- 정보수정 -->
      <div class="header">
        <h2>회원정보 수정</h2>   
        <div class="line"></div>
      </div>
      <div class="changebody">

        <form id="userinfo" enctype="multipart/form-data">
          <%--사용자 이미지 정보--%>
          <div class="userimg">
            <input type="file" name="file" id="fileInput" style="display: none;" onchange="changeimg(event)" >
            <label for="fileInput">
              <img id="previewImg" src="upload/${user.userImage}" alt="">

              <div id="overlay" onclick="openFile()">변경하기</div>
            </label>
          </div>
          <%--사용자 정보--%>
          <div class="changeBox">
            <label for="username" id="userName">닉네임</label><br>
            <input type="text" name="userName" id="Name" placeholder="Name" value="${user.userName}">
            <br>
            <label for="Email" id="email">이메일</label><br>
            <input type="text" name="userEmail" id="userEmail" placeholder="Email" value="${user.userEmail}">
            <br>
            <label for="userphone">전화번호</label><br>
            <input type="text" name="userPhone" id="userphone" placeholder="Phone" value="${user.userPhone}">
            <br>
            <label for="userPs">비밀번호</label><br>
            <input type="password" name="userPassword" id="userps" placeholder="Password" value="${user.userPassword}">
            <br>
            <label for="userpscheck" id="ckpw">비밀번호 확인</label><br>
            <input type="password" name="checkPassword" id="userpscheck" placeholder="Password확인">
          </div>
          <button type="submit" id="btnchange" >정보변경</button>
        </form>

      </div>
      <div class="lastLine"></div>
<script src="js/Changing.js"></script>

</body>
</html>