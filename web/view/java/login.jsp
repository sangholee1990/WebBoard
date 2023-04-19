<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>로그인</title>
    <script src="/js/jquery.js"></script>
</head>
<body>
<h1>관리자 로그인</h1>
<form id="loginForm">

    <table>
        <tr>
            <td>아이디: <input type="text" name="id" id="id"></td>
        </tr>
        <tr>
            <td>비밀번호: <input type="password" name="pw" id="pw"></td>
        </tr>
        <tr>
            <td>
                <input type="button" id=loginBtn value="로그인">
                <input type="button" id="resetBtn" value="취소">
            </td>
        </tr>
    </table>

    <script>
        $(document).ready(function () {
            $('#loginBtn').on('click', function () {
                var loginResult = $('#loginForm').serialize()  // 입력된 모든 Element를 문자열의 데이터에 serialize 한다.
                //var query = {id : $("#id").val(), pw : $("#pw").val()}
                $.ajax({
                    type: "POST"
                    , url: '/view/loginOk.jsp'
                    // , contentType: 'application/json; charset=utf-8'
                    , dataType: "json"
                    , cache: false
                    , data: loginResult
                    , success: onSuccess
                    , error: onError
                });

                function onSuccess(result) {
                    if (result && (result.loginResult >= 0)) {
                        alert('로그인 성공 : ' + result.loginResult);
                        // alert('목록 페이지로 이동합니다.');
                        // location.href="/controllerBoard/controller.jsp?act=list";
                    } else {
                        alert("에러 코드 : " + result.loginResult);  // TODO : 에러코드에 따른 처리 (result.updateResult <= -1)
                    }
                }

                function onError(request, status, error) {
                    console.warn('ajax 통신 실패');
                    alert("code : " + request.status + "\n" + "message : " + request.responseText + "\n" + "error : " + error);
                }
            });

            $('#resetBtn').on('click', function () {
                $('form')[0].reset();
            });
        });
    </script>
</form>
</body>
</html>