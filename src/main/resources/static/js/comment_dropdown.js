document.addEventListener('DOMContentLoaded', function() {
    var editButtons = document.querySelectorAll('.chatEdit');

    // 모든 드롭다운 메뉴를 숨기는 함수
    function closeAllDropdowns() {
        document.querySelectorAll('.dropdown-menu').forEach(function(menu) {
            menu.classList.remove('show');
        });
    }

    editButtons.forEach(function(btn) {
        btn.addEventListener('click', function() {
            var dropdownMenu = this.parentNode.querySelector('.dropdown-menu');
            // 먼저 모든 드롭다운 메뉴를 닫습니다.
            closeAllDropdowns();
            // 클릭한 요소의 드롭다운 메뉴만 토글합니다.
            if (dropdownMenu) {
                dropdownMenu.classList.toggle('show');
            }
        });
    });

    // 바디 클릭시 모든 드롭다운 메뉴 닫기
    document.body.addEventListener('click', function(e) {
        // .chatEdit가 클릭되지 않았을 때만 드롭다운을 닫습니다.
        if (!e.target.matches('.chatEdit')) {
            closeAllDropdowns();
        }
    }, true); // 캡처 단계에서 이벤트 처리
});
