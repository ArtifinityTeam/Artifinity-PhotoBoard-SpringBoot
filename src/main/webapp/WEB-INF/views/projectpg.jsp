<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Artfinity</title>
    <link rel="stylesheet" href="css/profile_drop.css">
    <link rel="stylesheet" href="css/projectpg.css">
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

<section1>
    <div class="sorting-buttons">
        <button id="latest-button" >최신순</button>
        <button id="popular-button" >인기순</button>

    </div>
</section1>

<section2>
    <div id="tab1" class="content" >
        <div id="grid-container">
            <!-- 프로젝트 목록은 여기에 동적으로 추가될 거에요 -->
        </div>
    </div>
</section2>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
    $(document).ready(function() {
        // 페이지가 로드될 때 최신순으로 프로젝트를 불러오도록 설정
        loadLatestProjects();

        // 최신순 버튼 클릭 시
        $("#latest-button").click(function() {
            loadLatestProjects();
            // 활성화된 버튼에 active 클래스 추가
            $(this).addClass('active');
            $("#popular-button").removeClass('active');
        });

        // 인기순 버튼 클릭 시
        $("#popular-button").click(function() {
            loadPopularProjects();
            // 활성화된 버튼에 active 클래스 추가
            $(this).addClass('active');
            $("#latest-button").removeClass('active');
        });

        // 최신순으로 프로젝트를 불러오는 함수
        function loadLatestProjects() {
            $.ajax({
                url: "/projects/latest-json",
                type: "GET",
                success: function(data) {
                    renderProjects(data); // 받은 데이터로 프로젝트 렌더링
                }
            });
        }

        // 인기순으로 프로젝트를 불러오는 함수
        function loadPopularProjects() {
            $.ajax({
                url: "/projects/popular-json",
                type: "GET",
                success: function(data) {
                    renderProjects(data); // 받은 데이터로 프로젝트 렌더링
                }
            });
        }

        // 받은 데이터로 프로젝트를 렌더링하는 함수
        function renderProjects(projects) {
            var gridContainer = $("#grid-container");
            gridContainer.empty(); // 기존의 프로젝트 목록을 비우기
            $.each(projects, function(index, project) {
                var projectHtml = '<a href="/project_detail/' + project.projectId + '">' +
                    '<img src="upload/' + project.fileName + '" alt="' + project.projectName + '">' +
                    '</a>';
                gridContainer.append(projectHtml); // 새로운 프로젝트 목록을 화면에 추가
            });
        }
    });
</script>
</body>
</html>