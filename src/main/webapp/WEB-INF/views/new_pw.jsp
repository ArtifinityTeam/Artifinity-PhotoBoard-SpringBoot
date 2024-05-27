<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>new_pw</title>
    <link rel="stylesheet" href="css/new_pw.css">
</head>
<body>
    <nav>
        <div class="logo">Art🖍finity</div>
    </nav>
    <div class="content-box">
        <div class="title">새 비밀번호 설정</div>


        <form action="/new_pw" method="post" onsubmit="return validateForm()">
            <input type="password" name="new_password" placeholder="새 비밀번호" required><br>
            <input type="password" name="confirm_password" placeholder="비밀번호 확인" required><br>
            <input type="hidden" name="email" value="${email}"> <!-- 이메일 정보를 전달 -->
            <input type="submit" class="new_pw-btn" value="비밀번호 변경하기">
        </form>
    </div>

    <script>
        function validateForm() {
            var newPassword = document.querySelector('input[name="new_password"]').value;
            var confirmPassword = document.querySelector('input[name="confirm_password"]').value;

            if (newPassword !== confirmPassword) {
                alert("비밀번호가 일치하지 않습니다. 다시 확인하세요.");
                return false; // 폼 제출을 방지
            }

            return true; // 폼 제출 허용
        }
    </script>
</body>
</html>