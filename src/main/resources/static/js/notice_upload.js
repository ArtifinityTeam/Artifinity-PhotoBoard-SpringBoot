//formData
$(document).ready(function() {
    $("#saveButton").click(function(event) {
        event.preventDefault();

        var formData = new FormData($('#noticeForm')[0]);

        $.ajax({
            url: "/notice_upload",
            type: "POST",
            data: formData,
            processData: false,
            contentType: false,
            dataType: 'json', // JSON 형식
            success: function(response) {
                if (response.status === "success") {
                    console.log('응답 메시지:', response.message);
                    alert("공지사항이 성공적으로 업로드되었습니다.");
                    window.location.href = "/notice_list";
                } else {
                    console.log('응답 메시지:', response.message);
                    alert("공지사항 업로드에 실패했습니다. 다시 시도해주세요.");
                }
            }
        });
    });
});
