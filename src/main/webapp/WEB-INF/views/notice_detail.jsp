<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Artfinity</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/notice_detail.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/profile_drop.css">
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
        <a href="#"></a>
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

<div class="continer">
<!-- 정보수정 -->
    <div class="header">

        <h1> ${notice.noticeTitle}</h1>
        <p class="userName">작성자 : ${notice.userName}</p>
        <p class="date">작성일자 : ${notice.noticeCreation}</p>
<%--        <div class="line"></div>--%>
        <hr>
    </div>
    <div class="board_main">
        <p>
            ${notice.noticeContent}
        </p>
    </div>
    <hr>
<%--    <div class="lastLine"></div>--%>
</div>
<div class="btnlist">
    <c:if test="${user.userId == notice.userId}">
        <button type="button" id="openmodal">신청자</button>
    </c:if>
    <c:if test="${user.userId != notice.userId}">
        <form id="applicationForm" action="/application" method="post" style="display: inline-block; margin: 0;">
           <input type="hidden" name="noticeId" value="${notice.noticeId}" />
            <button type="submit" id="applybtn">제작신청</button>
        </form>
    </c:if>
    <a href="/notice_list" style="display: inline-block; margin: 0;">
        <button type="button" id="boardlist" >목록으로</button>
    </a>
</div>

<div class="modal">

    <div class="modal_popup">
        <div class="modaltitle">
            <p>제작 신청자 목록 </p>
        </div>

        <div class="boardlist">

            <div class="list">
                <!-- 신청자이름 리스트  -->
                <ul>
                    <c:forEach var="apply" items="${apply}">
                    <li>${apply.userName}</li>
                    </c:forEach>
                </ul>
            </div>

        </div>
        <div class="btn_list">
            <button class="choose_btn">선택</button>
            <button class="close_btn">취소</button>
        </div>
    </div>
</div>


<script src="${pageContext.request.contextPath}/js/notice_detail.js"></script>


</body>
</html>