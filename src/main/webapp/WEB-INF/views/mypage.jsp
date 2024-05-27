<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Artfinity</title>
  <link rel="stylesheet" href="css/mypage.css">
  <link rel="stylesheet" href="css/profile_drop.css">

  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>

<!-- 기본ui -->
<nav>
  <div class="logo"><a href="/">Art🖍finity</a></div>
  <div class="nav-items">
    <a href="/projects">프로젝트</a>
    <a href="/background_img">배경화면</a>
    <a href="/creators">크리에이터</a>
    <a href="/notice_list">외주 게시판</a>
  </div>
  <div class="nav-right">
    <div class="dropdown-btn">
      <a href="#" class="btn btn-secondary dropdown-toggle" role="button" data-bs-toggle="dropdown" aria-expanded="false">
        <img src="upload/${user.userImage}" alt="마이페이지">
      </a>
      <div class="dropdown-content">
        <a href="/mypage">마이페이지</a>
        <a href="/project_upload">업로드</a>
        <a href="/logout">로그아웃</a>
      </div>
    </div>
  </div>
</nav>


<!-- 유저상태바 -->
<div class="usercontainer">
  <div class="user">

    <div class="userimage">
      <img src="upload/${user.userImage}" alt="">
    </div>
    <div class="username">
      <div class="name">${user.userName}</div>
      <form action="/Changing" method="get">
        <button type="submit" value="a" id="Rename">기본정보 수정</button>
      </form>
    </div>
  </div>

  <div class="views">
    <div class="likeBox">
      <div class="likeCount">${userLike}</div>
      <div class="like">좋아요</div>
    </div>
    <div class="line"><hr></div>
    <div class="viewBox">
      <div class="viewCount">${userView}</div>
      <div class="view">조회수</div>
    </div>

  </div>
</div>

<!-- tap -->
<section class="usertap">
  <div class="menu_bar">
    <button type="button" id="default" class="tab" onclick="Menu('tab1', this)">업로드한 프로젝트</button>
    <button type="button" class="tab" onclick="Menu('tab2', this)">무드보드</button>
  </div>
</section>

<!-- 업로드프로젝트 -->
<section4>
  <div id="tab1" class="content" >
    <div class="grid-container">

      <!-- 그룹시작 -->
      <c:forEach items="${projectList}" var="project">
        <div class="content-group">

          <div class="content-nav" >
            <img src="images/icon2.png" alt="">
          </div>

          <div class="incontent">
            <a href="/project_detail/${project.projectId}">
              <img src="upload/${project.fileName}" alt="">
            </a>
          </div>

          <div id="menu1" class="menu1">
            <ul>
              <li><a href="#" onclick="addBoard(${project.projectId})">무드보드추가</a></li>
              <li><a href="#" onclick="deletecontent(${project.projectId})">삭제</a></li>
              <li><a href="#" onclick="editcontent(${project.projectId})">수정</a></li>
            </ul>
          </div>
        </div>
      </c:forEach>


      <!-- 추가 -->

    </div>
  </div>
</section4>

<!-- 무드보드 -->
<section>
  <div id="tab2" class="content boardcontent" style="display: none;" >
    <div class="grid-container2">

      <!-- 그룹시작 -->
      <c:forEach items="${galleryList}" var="gallery">
        <div class="gridgroup">
          <div class="boardgroup">

            <div  class="grid-nav">
              <img src="images/icon2.png" alt="">
            </div>

            <div class="mboardtitle" >
              <p>${gallery.galleryName}</p>
            </div>

            <div class="inboard">
              <a href="/mboard_detail/${gallery.galleryName}" methods="GET">
                <c:forEach var="imageFileName" items="${gallery.imageFiles}" varStatus="loop">
                  <c:if test="${loop.index < 4}">
                    <img src="upload/${imageFileName}" alt="">
                  </c:if>
                </c:forEach>


              </a>
            </div>

            <div id="menu" class="menu">
              <ul>
                <input type="hidden" value="${gallery.galleryName}" id="delboardName">
                <button type="button" id="board_deleteBtn">삭제</button>
              </ul>
            </div>

          </div>
        </div>
      </c:forEach>



    </div>
  </div>
</section>

<div class="modal">

  <div class="modal_popup">
    <div class="modaltitle">
      <p>무드보드 추가하기</p>
    </div>
    <form id="boardForm" enctype="multipart/form-data">
      <div class="boardlist">
        <div class="list">
          <!-- 무드보드리스트  -->
          <c:forEach items="${galleryTitleList}" var="galleryNameList">
            <input type="hidden" value="modalProjectId" name="ProjectId">
            <input type='checkbox' name='galleryName' id='addgallery' value="${galleryNameList.galleryName}"> ${galleryNameList.galleryName}<br>
          </c:forEach>
        </div>
      </div>
      <div class="btn_list">
        <button type="submit" class="save_btn">저장</button>
        <button class="close_btn">취소</button>
        <button  class="plusboard">+새무드보드</button>
      </div>
    </form>
  </div>
</div>


<div class="modal modalplus">
  <div class="modal_popup">
    <p>새무드보드 생성</p>
    <div class="modaltitle">
      <label for="galleryName">무드보드 제목</label><br>
      <input type="text" name="galleryName" id="galleryName">
    </div>

    <div class="btn_list">
      <button  class="save_btn boardsave">저장</button>
      <button class="addboardclose_btn">취소</button>
    </div>
  </div>
</div>
</body>
<script src="js/mypage.js"></script>
</html>