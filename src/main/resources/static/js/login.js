document.addEventListener("DOMContentLoaded", function() {
    const form = document.querySelector("form");
    const emailInput = form.querySelector("input[name='email']");
    const passwordInput = form.querySelector("input[name='password']");
    const emailError = document.getElementById("email-error");
    const passwordError = document.getElementById("password-error");

    form.addEventListener("submit", function(event) {
        let hasError = false;

        // Clear previous error messages
        emailError.textContent = "";
        passwordError.textContent = "";

        // Email validation
        const email = emailInput.value;
        const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        if (!emailPattern.test(email)) {
            emailError.textContent = "유효한 이메일을 입력하세요.";
            hasError = true;
        }

        // Password validation
        const password = passwordInput.value;
        if (password.length < 6) { // 최소 길이 조건
            passwordError.textContent = "비밀번호는 최소 6자 이상이어야 합니다.";
            hasError = true;
        }

        // If there's an error, prevent form submission
        if (hasError) {
            event.preventDefault();
        }
    });
});