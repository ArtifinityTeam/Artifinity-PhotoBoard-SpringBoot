<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Artfinity</title>
    <link rel="stylesheet" href="css/Nmainpage.css">
</head>
<body>
<nav>
    <div class="logo">Art🖍finity</div>
    <div class="nav-items">
        <a href="/projects">프로젝트</a>
        <a href="/background_img">배경화면</a>
        <a href="/creators">크리에이터</a>
        <a href="/notice_list">외주 게시판</a>
    </div>
    <div class="nav-right">
        <div class="login">
            <a href="/login">로그인</a>
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
            <c:forEach var="project" items="${projects}">
                <a href="/project_detail/${project.projectId}">
                    <img src="upload/${project.fileName}" alt="${project.projectName}">
                </a>
            </c:forEach>
        </div>

    </div>
</section2>
</body>
</html>
