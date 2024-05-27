function openTextInput() {
    // form 요소 가져오기
    var projectForm = document.getElementById('projectForm');

    // 이미 textarea가 생성되어 있는지 확인
    var existingTextarea = projectForm.querySelector('textarea[name="content"]');
    if (!existingTextarea) { // textarea가 없으면 생성
        // textarea 생성
        var textarea = document.createElement('textarea');
        textarea.name = 'content';
        textarea.placeholder = '프로젝트 관련 내용 입력';
        textarea.style.display = 'block';

        // textarea를 form 내에 추가
        projectForm.appendChild(textarea);

        // 포커스 설정
        textarea.focus();

        // 입력란의 초기 높이 설정
        var initialHeight = textarea.scrollHeight + 'px';
        textarea.style.height = initialHeight;

        // 입력란의 높이가 텍스트의 실제 높이보다 작을 때만 높이를 자동으로 조절
        textarea.addEventListener('input', function() {
            var computedStyle = window.getComputedStyle(textarea);
            var heightOffset = parseInt(computedStyle.paddingTop) + parseInt(computedStyle.paddingBottom);

            // 입력된 텍스트의 실제 높이 계산
            textarea.style.height = initialHeight; // 초기 높이로 재설정
            var actualHeight = textarea.scrollHeight - heightOffset;

            // 입력된 텍스트의 실제 높이가 초기 높이보다 크면 높이를 조절
            if (actualHeight > parseInt(initialHeight)) {
                textarea.style.height = actualHeight + 'px';
            }
        });
    }
    // textarea를 보이도록 설정
    document.getElementById('textInput').style.display = 'block';
    // textarea에 포커스를 설정
    document.getElementById('textInput').focus();
}

// 파일 정보를 저장하는 변수
var selectedFile = null;

document.getElementById('fileInput').addEventListener('change', function(event) {
    selectedFile = event.target.files[0];
    console.log('선택한 파일:', selectedFile);
    // 선택한 이미지 파일을 미리보기에 표시
    var previewImg = document.getElementById('uploadImage');
    previewImg.src = URL.createObjectURL(selectedFile);
    previewImg.style.display = 'block';
});

function openFile() {
    document.getElementById('fileInput').click();
}

//formDATA 전송
$(document).ready(function (){

    //formDATA 전송
    $('#saveButton').click(function (event) {
        event.preventDefault();
        if (selectedFile === null) {
            alert('파일을 선택해주세요.');
            return;
        }

        var formData = new FormData($('#projectForm')[0]);
        formData.append('file', selectedFile); // 파일을 'file'이라는 이름으로 FormData에 추가

        $.ajax({
            type: 'POST',
            url: '/project_upload', // 이미지를 업로드하는 서블릿 주소
            data: formData,
            contentType: false,
            processData: false,
            success: function(response) {
                console.log('이미지 업로드 성공:', response);
                // 이미지 업로드 성공 후, 프로젝트 정보를 저장
                // saveProject(response); // 이미지 파일명을 인자로 전달
                window.location.href = "/";

            },
            error: function(xhr, status, error) {
                console.error('업로드 실패:', error);
                alert('이미지 업로드에 실패했습니다. 다시 시도해주세요.');
            }
        });
    });

})