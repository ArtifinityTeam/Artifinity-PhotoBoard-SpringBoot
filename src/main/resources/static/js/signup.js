document.addEventListener("DOMContentLoaded", function() {
    var form = document.getElementById('signup-form');
    var emailInput = document.getElementById('email');
    var passwordInput = document.getElementById('password');
    var passwordCheckInput = document.getElementById('password-check');
    var phoneInput = document.getElementById('phone');

    form.addEventListener('submit', function(event) {
        let isValid = true;


        // 이메일 유효성 검사
        var emailError = document.getElementById('email-error');
        var emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        if (!emailPattern.test(emailInput.value)) {
            emailError.textContent = '올바른 이메일 주소를 입력하세요.';
            isValid = false;
        } else {
            emailError.textContent = '';
        }

        // 비밀번호 일치 확인
        var passwordError = document.getElementById('password-error');
        if (passwordInput.value.length < 6) {
            passwordError.textContent = '비밀번호는 최소 6자 이상이어야 합니다.';
            isValid = false;
        } else {
            passwordError.textContent = '';
        }

        var passwordCheckError = document.getElementById('password-check-error');
        if (passwordInput.value !== passwordCheckInput.value) {
            passwordCheckError.textContent = '비밀번호가 일치하지 않습니다.';
            isValid = false;
        } else {
            passwordCheckError.textContent = '';
        }

        // 전화번호 유효성 검사
        var phoneError = document.getElementById('phone-error');
        var phonePattern = /^\d{3}\d{3,4}\d{4}$/;
        if (!phonePattern.test(phoneInput.value)) {
            phoneError.textContent = '올바른 전화번호를 입력하세요. (예: 01012345678)';
            isValid = false;
        } else {
            phoneError.textContent = '';
        }

        // 폼 제출 방지
        if (!isValid) {
            event.preventDefault();
        } else {
            // 로그인 페이지로 이동
            window.location.href = '/login';
        }
    });

});
