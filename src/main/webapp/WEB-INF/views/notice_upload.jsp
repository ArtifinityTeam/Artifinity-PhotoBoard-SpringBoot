<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Artfinity</title>
    <link rel="stylesheet" href="css/sample.css">
    <link rel="stylesheet" href="css/notice_upload.css">
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

<form id="noticeForm"  enctype="multipart/form-data">
    <div class="title">
        <div class="title-input">
            <h3>제목</h3>
            <input type="text" placeholder="제목" name="noticeTitle">
        </div>
    </div>

    <div class="notice-text">
        <div class="content">
            <h3>내용</h3>
            <textarea id="noticeInput" name="noticeContent" placeholder="아래의 양식을 맞춰 작성해주세요. &#13;&#10;&#13;&#10;&#13;&#10;1. 주제 : &#13;&#10;&#13;&#10; 2. 대상 : &#13;&#10;&#13;&#10; 3. 기간 : &#13;&#10;&#13;&#10; 4. 금액 : &#13;&#10;&#13;&#10; 5. 조건 : &#13;&#10;&#13;&#10; 6. 외주 설명"></textarea>
        </div>
    </div>

    <div class="save-button">
        <a href="/" class="cancel">
            <button type="button" id="cancelButton" class="cancel">취소</button>
        </a>
        <button type="button" id="saveButton" class="save">저장</button>
    </div>
</form>
<script src="js/notice_upload.js"></script>
</body>
</html>