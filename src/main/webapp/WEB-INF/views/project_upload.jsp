<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Artfinity</title>
    <link rel="stylesheet" href="css/sample.css">
    <link rel="stylesheet" href="css/project_upload.css">
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

<form id="projectForm"  enctype="multipart/form-data">
    <div class="title-tag">
        <div class="title-input">
            <h3>제목</h3>
            <input type="text" placeholder="제목" name="projectName">
        </div>
        <div class="category">
            <h3>카테고리</h3>
            <div class="radio-buttons">
                <input type="radio" id="illustration" name="tagName" value="일러스트" checked>
                <label for="illustration">일러스트</label>
                <input type="radio" id="photo" name="tagName" value="사진">
                <label for="photo">사진</label>
                <input type="radio" id="calligraphy" name="tagName" value="캘리그라피">
                <label for="calligraphy">캘리그라피</label>
                <input type="radio" id="design" name="tagName" value="디자인">
                <label for="design">디자인</label>
                <input type="radio" id="painting" name="tagName" value="회화">
                <label for="painting">회화</label>
                <input type="radio" id="architecture" name="tagName" value="건축">
                <label for="architecture">건축</label>
                <input type="radio" id="crafts" name="tagName" value="공예/조각">
                <label for="crafts">공예/조각</label>
                <input type="radio" id="interior" name="tagName" value="인테리어">
                <label for="interior">인테리어</label>
                <input type="radio" id="etc" name="tagName" value="기타">
                <label for="etc">기타</label>
            </div>
        </div>
    </div>

    <div class="toggle-slide">
        <h3>배경화면 공개 여부</h3>
        <label class="toggle">
            <input type="checkbox"  id="background" name="background" value="1">
            <span class="slide"></span>
        </label>
    </div>

    <div class="text-file">
        <img id="uploadImage" src="" alt="">
        <textarea id="textInput" name="content" placeholder="프로젝트 관련 내용 입력" style="display: none;"></textarea>
        <div class="upload">
            <button type="button" class="upload-text" id="addTextInput" onclick="openTextInput()"><img src="images/Text.png">텍스트 입력하기</button>
            <input type="file" id="fileInput" style="display: none;" onchange="handleFileSelect(event)">
            <button type="button" class="upload-img" onclick="openFile();"><img src="images/File.png">파일 가져오기</button>
        </div>
    </div>

    <div class="save-button">
        <a href="/" class="cancle">
            <button type="button" id="cancleButton" class="cancle">취소</button>
        </a>
        <button type="button" id="saveButton" class="save">저장</button>
    </div>

</form>

<script src="js/project_upload.js"></script>
</body>
</html>