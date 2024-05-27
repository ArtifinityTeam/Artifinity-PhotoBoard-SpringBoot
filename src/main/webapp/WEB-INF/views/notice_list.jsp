<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Artfinity</title>
    <link rel="stylesheet" href="css/notice_list.css">
    <link rel="stylesheet" href="css/profile_drop.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
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

<div class="board-banner">
    <img src="images/notice.png"/>
    <img src="images/upload_notice.png" onclick="upload()"/>
</div>

<div class="board-list">
    <div class="list">
        <dl>
            <dt class="num">번호</dt>
            <dt class="subject">제목</dt>
            <dt class="name">작성자</dt>
            <dt class="created">작성일</dt>
        </dl>
    </div>
    <div class="title">
        <c:forEach var="notice" items="${notice}">

            <dl>
                <dt class="N_num">${notice.noticeId}</dt>
                <dt class="N_subject"><a href="/notice/${notice.noticeId}">${notice.Title}</a></dt>
                <dt class="N_name">${notice.userName}</dt>
                <dt class="N_created">${notice.created}</dt>
            </dl>
            <hr>
        </c:forEach>

        <%--        <dl>--%>
        <%--            <dt class="num">${post.num}</dt>--%>
        <%--            <dt class="subject"><a href="/post/${post.id}">${post.subject}</a></dt>--%>
        <%--            <dt class="name">${post.name}</dt>--%>
        <%--            <dt class="created">${post.created}</dt>--%>

        <%--        </dl>--%>
    </div>
    <%--    <div class="uploadNotice">--%>
    <%--        <a href="/notice_upload" class="upload">--%>
    <%--            <button type="button" id="uploadButton" class="upload-button">공고 올리기</button>--%>
    <%--        </a>--%>
    <%--    </div>--%>
</div>

<script src="${pageContext.request.contextPath}/js/notice_list.js"></script>
</body>
</html>
