// 프로젝트별 메뉴 팝업설정
document.querySelectorAll(".content-nav").forEach(function(nav) {
    nav.addEventListener("click", function(event) {
        var contentGroup = event.target.closest(".content-group");
        var menu = contentGroup.querySelector(".menu1");

        if (menu.style.display === "block") {
            menu.style.display = "none";
        } else {
            // 모든 메뉴를 닫은 후 현재 그룹의 메뉴 열기
            document.querySelectorAll(".menu1").forEach(function(m) {
                m.style.display = "none";
            });
            menu.style.display = "block";
        }

        event.stopPropagation(); // 클릭 이벤트 전파 중지
    });
});

// 문서의 다른 곳을 클릭할 때 모든 메뉴를 닫기.
document.addEventListener("click", function() {
    document.querySelectorAll(".menu1").forEach(function(menu) {
        menu.style.display = "none";
    });
});



function deletecontent(projectId) {
    // 삭제 기능 구현
    $.ajax({
        type : 'POST',
        url : '/deleteProject',
        data : {"projectId" : projectId},
        success : function (response){
            alert("프로젝트가 삭제되었습니다.")
            window.location.reload();
        },
        error : function (xhr, status, error) {
            console.error(status, error);
            alert("삭제에 실패하였습니다.")
        }
    });
}





//DB에 갤러리 명 수정할 데이터 값
let boardTitle = document.getElementById("boardtitle").value;


//모달창 관련 JS
const modal = document.querySelector('.modal'); //모달창
const modalOpen = document.querySelector('.modal_btn'); // 열기
const modalClose = document.querySelector('.close_btn'); //닫기
const savebtn = document.querySelector('.save_btn'); //저장
const deletebtn = document.querySelector('.delete_btn'); //삭제

//열기 버튼을 눌렀을 때
modalOpen.addEventListener('click',function(){
    //'on' class 추가
    modal.classList.add('on');
    event.preventDefault();
});
//닫기 버튼을 눌렀을 때
modalClose.addEventListener('click',function(){
    //'on' class 제거
    modal.classList.remove('on');
    console.log("닫기")
});
//삭제 버튼 눌렀을 때
$('#delete_btn').click(function (event){
    event.preventDefault();

    modal.classList.remove('on');
    console.log("삭제");
    var originalName = document.getElementById("Originalname").value;
    console.log(originalName);
    $.ajax({
        type : 'POST',
        url : '/deleteGallery',
        data : {"GalleryName" : originalName},
        success: function (response) {
            console.log(response);
            alert("무드보드가 삭제되었습니다.");
            window.location.href = '/mypage';
        },
        error: function (xhr, status, error) {
            console.error(status, error);
            alert("무드보드 삭제 실패")
            window.location.reload();
        },
    })
})

//저장버튼 클릭시
$('#save_btn').click(function(event){
    event.preventDefault();

    modal.classList.remove('on');
    console.log("저장")
    //저장시 controller넘기는 코드 작성해야함


    var originalName = document.getElementById("Originalname").value;
    var newName = document.getElementById("boardtitle").value;
    console.log(newName);
    console.log(originalName);
    $.ajax({
        type : 'POST',
        url : '/GalleryRename',
        data : {"newName" : newName, "OriginName" : originalName },
        success: function (response) {
            console.log(response);
            alert("무드보드 수정에 성공하였습니다.")
            window.location.href = "/mypage";
        },
        error: function (xhr, status, error) {
            console.error(status, error);
            alert("무드보드 수정에 실패하였습니다.")
            // window.location.reload();
        },

    })

})