<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Artfinity</title>
    <link rel="stylesheet" href="css/creater.css">
    <link rel="stylesheet" href="css/profile_drop.css">
</head>
<body>

<%-- UI --%>
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




<!-- 나열 -->
<section class="creatertap">
    <%--    <div class="menu_bar">--%>
    <%--        <button type="button" id="default" class="tab" onclick="sort('tab1', this)">최신순</button>--%>
    <%--        <button type="button" class="tab" onclick="sort('tab2', this)">인기순</button>--%>
    <%--    </div>--%>
</section>


<!-- 크리에이터  -->
<section>
    <div  class="content"  >
        <div class="grid-container">


            <!-- 그룹시작 -->

            <c:forEach items="${creatorList}" var="creator">
                <div class="gridgroup">
                    <div class="mboardtitle"  >
                        <p>${creator.userName}</p>
                    </div>
                    <div class="creatorimg">
                        <a href="/increator/${creator.userId}" methods="GET">
                            <img src="upload/${creator.userImage}" alt="">
                        </a>
                    </div>
                    <div id="inboard" class="increator" onclick="inboard()" >
                        <a href="/increator/${creator.userId}" methods="GET">
                            <c:forEach var="imageFileName" items="${creator.imageFiles}" varStatus="loop">
                                <c:if test="${loop.index < 4}">
                                    <img src="upload/${imageFileName}" alt="">
                                </c:if>
                            </c:forEach>

                        </a>
                    </div>
                    <!-- 좋아요 / 조회수 -->
                    <div class="detail">
                        <div class="detail_content">
                            <div class="icon-view">
                                <img src="icons/white_eye.png" alt="">
                                <span>${creator.view}</span>
                            </div>
                            <div class="icon-heart">
                                <img src="icons/white_heart.png" alt="">
                                <span>${creator.like}</span>
                            </div>
                        </div>
                    </div>

                </div>
            </c:forEach>

            <!-- 추가 -->

        </div>
    </div>
</section>
<script src="js/creators.js"></script>
</body>
</html>