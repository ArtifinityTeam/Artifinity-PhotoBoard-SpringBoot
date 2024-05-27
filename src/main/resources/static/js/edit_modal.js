// 편집 버튼 클릭 이벤트 핸들러
function editBoard() {
    // 모달 창을 찾아 표시
    var modal = document.getElementById("editModal");
    modal.style.display = "block";

    // 모달 내의 폼에 이벤트 리스너 추가
    var form = document.getElementById("editForm");
    form.onsubmit = function() {
        // 폼 제출 로직을 작성
        // 예: AJAX를 이용하여 서버에 저장
        // ...

        // 모달 창을 숨김
        modal.style.display = "none";
        return false; // 페이지 리프레시 방지
    };

    // 닫기 버튼 클릭 이벤트 핸들러
    var closeBtn = document.getElementsByClassName("close")[0];
    closeBtn.onclick = function() {
        modal.style.display = "none";
    };
}

// 윈도우 클릭시 모달 바깥 클릭시 닫기
window.onclick = function(event) {
    var modal = document.getElementById("editModal");
    if (event.target == modal) {
        modal.style.display = "none";
    }
};
