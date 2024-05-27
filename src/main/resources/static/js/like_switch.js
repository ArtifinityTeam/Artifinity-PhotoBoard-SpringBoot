function toggleHeart(userId) {
    var image = document.getElementById('heartIcon');
    var image2 = document.getElementById('heartIcon_view');
    var isLikedByUser = image.dataset.likedByUser === 'true'; // 현재 상태를 가져옵니다.
    var newIsLikedByUser = !isLikedByUser; // 상태 뒤집기

    // 이미지 상태를 업데이트합니다.
    /////경로 수정//////////////////////////////////////////////////////////////////////////////////////////////////
    var newSrc = newIsLikedByUser ? '${pageContext.request.contextPath}/icons/heart_filled_icon.png' : '${pageContext.request.contextPath}/icons/heart_empty_icon.png';
    image.src = newSrc;
    image2.src = newSrc;

    image.dataset.likedByUser = newIsLikedByUser; // 데이터 속성 업데이트

    // AJAX 요청으로 서버에 좋아요 상태 변경을 전송합니다.
    var action = newIsLikedByUser ? 'userlike_add' : 'userlike_remove'; // 좋아요 상태에 따라 액션 결정
    var projectId = image.dataset.projectId; // 프로젝트 ID를 가져옵니다.
    fetch('/' + projectId + '/' + action, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
            userId: userId
        })
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.text();
        })
        .then(data => {
            console.log('Like ' + action + 'ed: ', data);
            // 여기서 추가적인 UI 업데이트나 로직을 구현
            // 페이지를 새로고침하여 모든 데이터를 최신 상태로 업데이트
            window.location.reload(); // 페이지 새로고침 코드 추가
        })
        .catch(error => {
            console.error('Failed to ' + action + ' like:', error);
        });
}
