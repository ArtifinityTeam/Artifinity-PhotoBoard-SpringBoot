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


//무드보드 메뉴바 팝업


document.querySelectorAll(".grid-nav").forEach(function(nav) {
    nav.addEventListener("click", function(event) {
        var contentGroup = event.target.closest(".gridgroup");
        var menu = contentGroup.querySelector(".menu");

        if (menu.style.display === "block") {
            menu.style.display = "none";
        } else {
            // 모든 메뉴를 닫은 후 현재 그룹의 메뉴를 엽니다.
            document.querySelectorAll(".menu").forEach(function(m) {
                m.style.display = "none";
            });
            menu.style.display = "block";
        }

        event.stopPropagation(); // 클릭 이벤트 전파 중지
    });
});

// 문서의 다른 곳을 클릭할 때 모든 메뉴를 닫습니다.
document.addEventListener("click", function() {
    document.querySelectorAll(".menu1").forEach(function(menu) {
        menu.style.display = "none";
    });
});




$('#board_deleteBtn').click(function (event){
    event.preventDefault();

    var gallery_Del_name = document.getElementById("delboardName").value;
    $.ajax({
        type : 'POST',
        url : '/deleteGallery',
        data : {"GalleryName" : gallery_Del_name},
        success : function (response){
            alert("무드보드가 삭제되었습니다.")
            window.location.reload();
        },
        error : function (xhr, status, error) {
            console.error(status, error);
            alert("삭제에 실패하였습니다.")
        }
    })
})




// 업로드프로젝트
document.querySelectorAll(".content-nav").forEach(function(nav) {
    nav.addEventListener("click", function(event) {
        var contentGroup = event.target.closest(".content-group");
        var menu = contentGroup.querySelector(".menu1");

        if (menu.style.display === "block") {
            menu.style.display = "none";
        } else {
            // 모든 메뉴를 닫은 후 현재 그룹의 메뉴를 엽니다.
            document.querySelectorAll(".menu1").forEach(function(m) {
                m.style.display = "none";
            });
            menu.style.display = "block";
        }

        event.stopPropagation(); // 클릭 이벤트 전파 중지
    });
});

// 문서의 다른 곳을 클릭할 때 모든 메뉴를 닫습니다.
document.addEventListener("click", function() {
    document.querySelectorAll(".menu1").forEach(function(menu) {
        menu.style.display = "none";
    });
});



function deletecontent(projectId) {
    // 삭제 기능 구현
    let projectid = projectId;
    // Ajax 요청을 보내는 부분
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

function editcontent() {
    // 수정 기능 구현
    alert("수정할 수 있습니다.");
}

// 따로 수정 예정.
// function inboard(Name) {
//
//     window.location.href = "/mboard_detail"; // 다른 HTML 주소로 이동
// }
var ProjectId;
function addBoard(projectId){
    modal.classList.add('on');
    // document.getElementById("modalProjectId").value = projectId;
    ProjectId = projectId;
}

const modal = document.querySelector('.modal'); //모달창
// const modalOpen = document.querySelector('.modal_btn'); //1번 모달창
const modalClose = document.querySelector('.close_btn'); //1번 모달창 닫기
const savebtn = document.querySelector('.save_btn'); //1번 모달창 저장
const addboard = document.querySelector('.plusboard'); //2번 모달창켜기
const modal_plus = document.querySelector('.modalplus') //2번 모달창
const modalplusclose = document.querySelector('.addboardclose_btn'); //2번 모달창 닫기
const newboardSave = document.querySelector('.boardsave');// 2번 모달창 저장
var projectId
//열기 버튼을 눌렀을 때 1번모달팝업이 열림


//닫기 버튼을 눌렀을 때 1번모달팝업이 닫힘
modalClose.addEventListener('click',function(){
    //'on' class 제거
    modal.classList.remove('on');
});

//새무드보드 생성 클릭시 기존 모달창 끄고, 새로운 모달창 생성.
//이후 저장 버튼 누르면 기존 모달창 켜고, 해당 모달창 끄기,

//2번 모달창 으로 변경
addboard.addEventListener('click',function(){
    event.preventDefault();
    //무드보드추가하는 모달창변경
    modal_plus.classList.add('on');
    modal.classList.remove('on');
})

//2번 모달창에서 취소 클릭시 모달창 종료
modalplusclose.addEventListener('click',function(){
    modal_plus.classList.remove('on');
})




// 저장 버튼 클릭 시 실행되는 코드
savebtn.addEventListener('click', function() {
    // 모달 창을 닫습니다.
    modal.classList.remove('on');

    // FormData 객체생성
    // var formData = new FormData();
    var formData = new FormData($('boardForm')[0]);
    // 선택된 각 체크박스의 값을 FormData 객체에 추가
    var galleryCheckboxes = document.querySelectorAll('.list input[name="galleryName"]:checked');
    galleryCheckboxes.forEach(function(checkbox) {
        formData.append("galleryName", checkbox.value);
    });

    // projectId를 FormData 객체에 추가.
    formData.append("ProjectId", ProjectId);
    formData.append("GalleryName", galleryName);

    $.ajax({
        type: 'POST',
        url: '/Addboard',
        data: formData,
        contentType: false,
        processData: false,
        success: function(response) {
            console.log(response);
            window.location.reload();
        },
        error: function(xhr, status, error) {
            console.error(status, error);
            alert("무드보드 추가에 실패했습니다.");
        }
    });
});

var galleryName;

//2번 모달창에서 저장 클릭시 1번 모달창으로 이동
newboardSave.addEventListener('click', function() {
    modal_plus.classList.remove('on');
    modal.classList.add('on');

    // 새로운 보드의 이름 가져오기
    galleryName = document.getElementById("galleryName").value;

    // 폼에 새로운 입력 필드 추가
    var input = document.createElement("input");
    input.type = "checkbox";
    input.name = "galleryNameList";
    input.value = galleryName;
    document.querySelector('.list').appendChild(input);

    // 입력 필드 옆에 보드 이름 추가
    var label = document.createElement("label");
    label.textContent = galleryName;
    document.querySelector('.list').appendChild(label);


});