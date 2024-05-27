<%--
  Created by IntelliJ IDEA.
  User: 82109
  Date: 2024-04-18
  Time: 오후 1:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Artfinity</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/project_detail.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/profile_drop.css">

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

<div class="title_label">
  <a href="/">< 뒤로가기</a>
  <div class="main-title">
    <p>${project.projectName}</p>
    <div class="tagName">${tagName}</div>
  </div>
</div>

<div class="project-section">

  <div class="contents-container">
    <img class="project-img" src="${pageContext.request.contextPath}/upload/${project.fileName}" alt="${project.projectName}">
    <div class="down"></div>
    <c:if test="${background == 1}">
      <button class="downButton">
        <a href="<c:url value='${pageContext.request.contextPath}/upload/${project.fileName}'/>" id="download" class="download" download="">이미지 다운로드</a>
      </button>
    </c:if>
    <p>${project.content}</p>
  </div>

  <div class="sticky-nav">
    <div class="sticky-nav-content">
      <a href="/increator/${project.userId}" class="sticky-nav-link-mypage">
        <img src="${pageContext.request.contextPath}/upload/${project.userImage}" alt="프로필"></a>
      <img class="sticky-nav-link heart" id="heartIcon" data-project-id="${project.projectId}" data-liked-by-user="${isLikedByUser}"
           src="${isLikedByUser ? '/icons/heart_filled_icon.png' : '/icons/heart_empty_icon.png'}"
           alt="좋아요" onclick="toggleHeart(${user.userId})" style="cursor: pointer;" />
      <a href="#comment-list-start"><img class="sticky-nav-link chat" src="${pageContext.request.contextPath}/icons/chat_icon.png" alt="댓글로 이동"></a>
    </div>
  </div>
</div>


<div class="detail">
  <div class="detail_content">
    <div class="icon-view">
      <img src="${pageContext.request.contextPath}/icons/view_icon.png" alt="">
      <span>${project.views}</span>
    </div>
    <div class="icon-heart">
      <img id="heartIcon_view" src="${isLikedByUser ? '/icons/heart_filled_icon.png' : '/icons/heart_empty_icon.png'}" alt="빈 좋아요">
      <span>${userLikeCount}</span>
    </div>
    <div class="icon-comment">
      <img src="${pageContext.request.contextPath}/icons/chat_icon.png" alt="">
      <span>${commentCount}</span>
    </div>
  </div>
</div>

<div class="chat">
  <div id="comment-list-start" class="target-anchor"></div>
  <hr>

  <c:forEach items="${comments}" var="comment">
    <div class="chatBox" data-comment-id="${comment.commentId}">
      <div class="userImg"><img src="${pageContext.request.contextPath}/upload/${comment.userImage}" alt="프로필"></div>

      <div class="userChat">
        <div class="userName">${comment.userName}</div>
        <div class="chatContent">${comment.content}</div>
      </div>

      <div class="chatSide">

          <%-- 현재 세션의 userId와 댓글의 userId 비교 --%>
        <c:if test="${comment.userId == sessionScope.userId}">
          <div class="chatEdit">
            <img src="${pageContext.request.contextPath}/icons/dot_icon.png" alt="">
            <div class="dropdown">
              <ul class="dropdown-menu">
                <li><a href="#edit" onclick="updateComment(${comment.commentId}); return false;">수정</a></li>
                <li>
                  <!-- 삭제를 위한 폼 -->
                  <form action="${pageContext.request.contextPath}/delete-comment" method="post" style="display:inline;">
                    <input type="hidden" name="commentId" value="${comment.commentId}" />
                    <input type="hidden" name="projectId" value="${project.projectId}" /> <!-- projectId도 전송해야 합니다 -->
                    <button type="submit" class="no-button-style" onclick="return confirm('댓글을 삭제하시겠습니까?');">삭제</button>
                  </form>
                </li>
              </ul>
            </div>
          </div>
        </c:if>

        <div class="chatDate">
          <fmt:formatDate value="${comment.updated_at}" pattern="yyyy-MM-dd" />
        </div>

      </div>

    </div>
  </c:forEach>

  <%--<div class="chatBox">
    <div class="userImg"><img src="${pageContext.request.contextPath}/static/icons/userImg_basic_w.png" alt=""></div>
    <div class="userChat">
      <div class="userName">UserName</div>
      <div class="chatContent">댓글이 들어가는 부분입니다</div>
    </div>
    <div class="chatSide">

      <div class="chatEdit">
        <img src="${pageContext.request.contextPath}/static/icons/dot_icon.png" alt="">

        <div class="dropdown">
          <ul class="dropdown-menu">
            <li><a href="#edit">수정</a></li>
            <li><a href="#delete">삭제</a></li>
          </ul>
        </div>
      </div>

      <div class="chatDate">2024.04.16</div>
    </div>

  </div>--%>

  <hr>

  <%--댓글 입력하는 부분--%>
  <c:url var="commentActionUrl" value="/${project.getProjectId()}/comment"/>
  <form action="${commentActionUrl}" method="post">
    <div class="chatBox">
      <div class="userImg"><img src="${pageContext.request.contextPath}/upload/${user.userImage}" alt="내 프로필"></div>
      <div class="userChat">
        <div class="userName">${user.userName}</div>
        <div class="chatContent"><input name="content" type="text" value="" placeholder="댓글을 입력해 주세요"></div>
      </div>
      <div class="chatBtn">
        <input type="submit" name="register" value="등록" id="">
      </div>
    </div>
  </form>

</div>

<div class="footer"></div>

<script src="${pageContext.request.contextPath}/js/comment_dropdown.js"></script>
<script src="${pageContext.request.contextPath}/js/comment_btn.js"></script>
<script src="${pageContext.request.contextPath}/js/like_switch.js"></script>


</body>
</html>
