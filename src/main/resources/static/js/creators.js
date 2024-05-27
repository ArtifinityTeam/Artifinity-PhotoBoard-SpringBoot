

// 최신순 정렬 함수
function sortByDate() {
    data.sort((a, b) => new Date(b.creators) - new Date(a.creators));
    // 정렬된 데이터를 화면에 표시하는 함수 호출 (여기서는 생략)
}

// 인기순 정렬 함수
function sortByLikes() {
    data.sort((a, b) => b.likes - a.likes);
    // 정렬된 데이터를 화면에 표시하는 함수 호출 (여기서는 생략)
}

// 버튼 클릭 이벤트 핸들러
function sort(type, button) {
    // 모든 탭 버튼 비활성화
    let tabs = document.querySelectorAll('.tab');
    tabs.forEach(tab => tab.classList.remove('active'));
    
    // 클릭된 탭 버튼 활성화
    button.classList.add('active');

    // 정렬 실행
    if (type === 'tab1') {
        sortByDate(); // 최신순
    } else if (type === 'tab2') {
        sortByLikes(); // 인기순
    }
}
