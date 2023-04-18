/**
 * 글 수정을 위한 유효성 검사
 * - [수정] 버튼을 클릭 이벤트 실행
 * - [취소] 버튼을 클릭 이벤트 발생
 * - 수정 폼의 입력값 유효성 검사
 */

var iStatus = true;

$(document).ready(function () {

    /*[수정] 버튼을 클릭하면 자동 실행*/
    $('#updateBtn').on('click', function () {

        if (isPass()) {  /*수정값을 모두 입력한 경우*/
            var updateResult = $('#updateForm').serialize()
            $.ajax({
                type: "POST"
                /*, url:'/board/controller.jsp?act=updateOk'*/
                , url: '/updateOk.do'
                /*, contentType: 'application/json; charset=utf-8'*/
                , dataType: "text"
                , cache: false
                , data: updateResult
                , success: onSuccess
                , error: onError
            });

            function onSuccess(result) {
                if (result.trim() >= 0) {
                    alert('수정 성공 : ' + result.trim());
                    alert('목록 페이지로 이동합니다.');
                    location.href = "/list.do";
                } else {
                    /*TODO : 에러코드에 따른 처리 (result.updateResult <= -1)*/
                    alert("에러코드 : " + result.updateResult);
                }
            }

            function onError(request, status, error) {
                console.warn('ajax 통신 실패');
                alert("code : " + request.status + "\n" + "message : " + request.responseText + "\n" + "error : " + error);
            }
        }
    });

    /*[취소] 버튼을 클릭하면 자동 실행*/
    $('#resetBtn').on('click', function () {
        alert('취소되었습니다.')
        $('form')[0].reset();
    });
});


/**
 * 수정 폼의 입력값 유무 확인
 */
function isPass() {  // function 키워드로 함수를 정의할 수 있음.

    if (!$.trim($("#btitle").val())) {
        alert("제목을 입력하세요!");
        $("#btitle").val("").focus();
        iStatus = false;
        return false;
    }

    if (!$.trim($("#bcont").val())) {
        alert("내용을 입력하세요!");
        $("#bcont").val("").focus();
        iStatus = false;
        return false;
    }
    return true;
}  // isPass()