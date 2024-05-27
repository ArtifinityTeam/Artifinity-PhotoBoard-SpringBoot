//기본탭설정
window.onload = function() {
    var defaultTabButton = document.getElementById("default");
    Menu('tab1', defaultTabButton);
};
function Menu(tabId, button) {
    var i, changetab, tablinks;

    // 모든 탭 콘텐츠를 숨김
    changetab = document.getElementsByClassName("content");
    for (i = 0; i < changetab.length; i++) {
        changetab[i].style.display = "none";
    }

    // 모든 탭 버튼에서 활성화 클래스 제거
    tablinks = document.getElementsByClassName("tab");
    for (i = 0; i < tablinks.length; i++) {
        tablinks[i].classList.remove("active");
    }

    // 현재 클릭된 탭 콘텐츠를 표시하고 해당 탭 버튼에 활성화 클래스 추가
    document.getElementById(tabId).style.display = "block";
    button.classList.add("active");
}
