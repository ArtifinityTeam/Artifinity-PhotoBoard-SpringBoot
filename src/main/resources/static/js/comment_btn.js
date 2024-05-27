document.addEventListener('DOMContentLoaded', function () {
    function bindEditButtons() {
        const editButtons = document.querySelectorAll('.dropdown-menu li a[href="#edit"]');

        editButtons.forEach(function (btn) {
            btn.removeEventListener('click', handleEditClick); // 이전 이벤트 리스너 제거
            btn.addEventListener('click', handleEditClick); // 새 이벤트 리스너 추가
        });
    }

    function handleEditClick(e) {
        e.preventDefault();

        const chatBox = this.closest('.chatBox');
        const chatContent = chatBox.querySelector('.chatContent');
        const chatSide = chatBox.querySelector('.chatSide');
        const currentText = chatContent.textContent.trim();
        const originalChatSideContent = chatSide.innerHTML; // 원래 chatSide 내용 저장

        // 기존 내용을 input 필드로 변경합니다.
        chatContent.innerHTML = `<input type="text" class="edit-input" value="${currentText}">`;
        chatSide.classList.add('editing');

        // '저장'과 '취소' 버튼을 생성하고 추가합니다.
        chatSide.innerHTML = `
      <button class="save-edit">저장</button>
      <button class="cancel-edit">취소</button>`;

        const saveButton = chatSide.querySelector('.save-edit');
        const cancelButton = chatSide.querySelector('.cancel-edit');

        // 저장 버튼 이벤트 리스너
        saveButton.addEventListener('click', function () {
            const editedText = chatBox.querySelector('.edit-input').value;
            const commentId = chatBox.dataset.commentId; // HTML에서 data-comment-id 속성을 읽어옵니다.

            // AJAX 요청을 사용하여 서버에 댓글 내용을 업데이트합니다.
            fetch('/update-comment', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded',
                    // 추가적으로 필요한 CSRF 토큰 헤더가 있을 수 있습니다.
                },
                body: `commentId=${encodeURIComponent(commentId)}&content=${encodeURIComponent(editedText)}`,
            })
                .then(response => {
                    if (response.ok) {
                        chatContent.textContent = editedText;
                        chatSide.innerHTML = originalChatSideContent; // 원래 내용으로 복구
                        chatSide.classList.remove('editing');
                        bindEditButtons(); // 이벤트 리스너를 다시 바인딩
                    } else {
                        throw new Error('Something went wrong');
                    }
                })
                .catch(error => {
                    console.error('Error updating the comment:', error);
                    alert('댓글을 업데이트하지 못했습니다.');
                });
        });


        // 취소 버튼 이벤트 리스너
        cancelButton.addEventListener('click', function () {
            chatContent.textContent = currentText;
            chatSide.innerHTML = originalChatSideContent; // 원래 chatSide 내용으로 복구
            chatSide.classList.remove('editing');
            bindEditButtons(); // 이벤트 리스너를 다시 바인딩
        });

        // 인풋 필드에 포커스를 맞춥니다.
        chatContent.querySelector('.edit-input').focus();
        chatContent.querySelector('.edit-input').select();
    }

    // 페이지 로드 시 이벤트 리스너를 처음 바인딩합니다.
    bindEditButtons();
});
