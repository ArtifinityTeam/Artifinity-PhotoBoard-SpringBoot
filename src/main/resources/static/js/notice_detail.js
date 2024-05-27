
const modal = document.querySelector('.modal'); //모달창
const modalOpen = document.querySelector('#openmodal'); //열기
const modalClose = document.querySelector('.close_btn'); //닫기
const choose = document.querySelector('.choose_btn'); //저장


modalOpen.addEventListener('click',function(){
    modal.classList.add('on')
})
//닫기 버튼을 눌렀을 때 1번모달팝업이 닫힘
modalClose.addEventListener('click',function(){
    //'on' class 제거
    modal.classList.remove('on');
});

choose.addEventListener('click',function(){
    modal.classList.remove('on');
})

$(document).ready(function() {
    $('#applicationForm').submit(function(event) {
        event.preventDefault();

        var form = $(this);
        var formData = form.serialize(); // 폼 데이터를 직렬
        var noticeId = $('input[name="noticeId"]').val();

        $.ajax({
            type: 'POST',
            url: '/application',
            data: formData,
            success: function(response) {
                console.log(response);
                alert("신청성공하였습니다.")

            },
            error: function(xhr, status, error) {
                console.error(xhr, status);
                alert("신청에 실패하였습니다.");
            }
        });
    });
});







