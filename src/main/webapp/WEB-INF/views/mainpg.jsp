<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Artfinity</title>
        <link rel="stylesheet" href="css/mainpage.css">
        <link rel="stylesheet" href="css/profile_drop.css">
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
        <%--<input type="search" placeholder="검색">--%>
        <form action="/search" method="GET">
            <div class="search">
                <input name="keyword" type="text" placeholder="검색">
            </div>
        </form>

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

<section>
    <div class="event_title">
        💌 !당신을 위한 특별한 이벤트! 💌
    </div>
    <div class="event">
        <a href="/event_fullpage"><img src="images/banner.png"></a>
    </div>
</section>

<section2>
    <div class="event_title">
        📄 프로젝트 모아보기
    </div>
    <div id="tab1" class="content" >

        <div id="grid-container">
            <c:if test="${not empty projects}">
                <c:forEach var="project" items="${projects}">
                    <a href="/project_detail/${project.projectId}">
                        <img src="upload/${project.fileName}" alt="${project.projectName}">
                    </a>
                </c:forEach>
            </c:if>
        </div>
        <c:if test="${empty projects}">
            <div class="notice">
                <p>검색 결과가 없습니다.</p>
            </div>
        </c:if>
    </div>
</section2>

</body>
</html>