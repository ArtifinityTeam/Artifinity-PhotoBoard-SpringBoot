<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Artfinity</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/Gallery_Detail.css">
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
                <img src="${pageContext.request.contextPath}/upload/${user.userImage}" alt="마이페이지">
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
        <div class="username">
            <c:if test="${not empty DetailList}">
                <div class="name">${DetailList[0].galleryName}</div> <!-- 무드보드 제목 -->
            </c:if>
            <form action="#" method="get">
                <button type="submit" value="a" id="Rename" class="modal_btn">수정</button>
            </form>
        </div>
    </div>



</div>

<!-- tap -->
<section class="creatertap">
    <div class="menu_bar">
        <button type="button" id="default" class="tab" onclick="sort('tab1', this)">최신순</button>
        <button type="button" class="tab" onclick="sort('tab2', this)">인기순</button>
    </div>
</section>

<!-- 무드보드 내부 프로젝트  -->
<section4>
    <div class="content" >
        <div class="grid-container">

            <!-- 그룹시작 -->
            <c:forEach items="${DetailList}" var="detail">
                <div id="tap1" class="content-group">
                    <div class="content-nav" >
                        <img src="${pageContext.request.contextPath}/images/icon2.png" alt="">
                    </div>

                    <div class="incontent">
                        <a href="/project_detail/${detail.projectId}">
                            <img src="${pageContext.request.contextPath}/upload/${detail.fileName}" alt="">
                        </a>
                    </div>

                    <div id="menu1" class="menu1">
                        <ul>
                            <li><a href="#" onclick="deletecontent(${detail.projectId})">삭제</a></li>
                        </ul>
                    </div>
                </div>
            </c:forEach>
            <!-- 추가 -->

        </div>
    </div>
</section4>

<!-- 모달창 -->

<div class="modal">

    <div class="modal_popup">
        <div class="modaltitle">
            <p>무드보드 수정</p>
        </div>
        <form id="RenameBoard" enctype="multipart/form-data">
            <div class="board">
                <label for="boardtitle">무드보드 제목</label>
                <c:if test="${not empty DetailList}">
                    <input type="hidden" id="Originalname" name="Originalname" value="${DetailList[0].galleryName}">
                    <input type="text" name="galleryName" id="boardtitle" value="${DetailList[0].galleryName}">
                </c:if>
            </div>

            <div class="btn_list">
                <button class="save_btn" id="save_btn">저장</button>
                <button class="delete_btn" id="delete_btn">삭제</button>
                <button class="close_btn">취소</button>
            </div>
        </form>
    </div>

</div>

</body>
<script src="${pageContext.request.contextPath}/js/mboard_detail.js"></script>
</html>
