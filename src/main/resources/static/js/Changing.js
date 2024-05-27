$(document).ready(function (){

    $('#btnchange').click(function (event){
        //이벤트 중복제어
        event.preventDefault();

        //값이 입력되었는지 확인
        if ($('#Name').val() == "") {
            alert('이름을 입력하시오.');
            return;
        }
        if ($('#userEmail').val() == "") {
            alert('이메일을 입력하시오.');
            return;
        }
        if ($('#userphone').val() == "") {
            alert('전화번호를 입력하시오.');
            return;
        }
        if ($('#userps').val() == "") {
            alert('비밀번호를 입력하시오.');
            return;
        }
        if ($('#userpscheck').val() == "") {
            alert('비밀번호확인을 입력하시오.');
            return;
        }
        if ($('#userps').val() == $('#userpscheck')) {
            alert('비밀번호가 일치하지 않습니다. 다시 입력해주세요.')
            return;
        }

    //AJAX형태로 데이터 전송
        var formData = new FormData($('#userinfo')[0]);

        $.ajax({
            type: 'POST',  //전송방식
            url: '/Changing', // 전송 주소
            data: formData, // 데이터 형태
            contentType: false,
            processData: false,
            success: function(response) {
                console.log(response);
                //성공시 페이지 이동
                // 이미지 경로 전달.
                // $('#previewImg').attr('src');
                window.location.href = '/mypage';
            },
            error: function(xhr, status, error) {
                // 오류 발생 시 수행할 동작
                console.error(xhr.responseText);
                alert('업데이트에 실패 했습니다. 다시 시도해주세요.')
            }
        });
    });


})


// 파일 열기 함수
function openFile() {
    // fileInput 요소를 클릭하여 파일을 엽니다.
    //document.getElementById("fileInput").click();
}

// 이미지 변경 함수
function changeimg(event) {
    // 이벤트의 대상을 가져옵니다.
    var input = event.target;
    // FileReader 객체 생성
    var reader = new FileReader();
    // 파일을 읽었을 때 실행할 함수 설정
    reader.onload = function () {
        // 파일을 읽은 결과를 가져와서 데이터 URL로 저장합니다.
        var dataURL = reader.result;
        // 미리보기 이미지 요소를 가져옵니다.
        var output = document.getElementById('previewImg');
        // 미리보기 이미지의 소스를 데이터 URL로 설정합니다.
        output.src = dataURL;
    };
    // 입력된 파일을 데이터 URL로 읽습니다.
    reader.readAsDataURL(input.files[0]);
}




