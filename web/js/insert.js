/**
 * 글 등록을 위한 유효성 검사
 * - [등록] 버튼을 클릭 이벤트 실행
 * - [취소] 버튼을 클릭 이벤트 발생
 * - 등록 폼의 입력값 유효성 검사
 */

$(document).ready(function () {

    /*[등록] 버튼을 클릭하면 자동 실행*/
    $('#insertBtn').on('click', function () {
        //insertCheck();  /*글쓰기의 입력 여부 체크*/

        if (isPass()) {  /*입력란에 값을 모두 입력한 경우*/
            var insertResult = $('#insertForm').serialize()
            $.ajax({
                type: "POST"
                //, url:'/board/controller.jsp?act=insertOk'
                , url: '/insertOk.do'
                /*, contentType: 'application/json; charset=utf-8'*/
                // , dataType: "xml"
                , dataType: "json"
                , cache: false
                , data: insertResult
                , success: onSuccess
                , error: onError
            });

            function onSuccess(result) {

                if (result && (result.insertResult >= 0)) {
                    // alert('등록 성공 : ' + result.insertResult);
                    alert('목록 페이지로 이동합니다.');
                    location.href = "/list.do";
                } else {
                    /*TODO : 에러코드에 따른 처리 (result.insertResult <= -1)*/
                    alert("에러코드 : " + result.insertResult);
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
 * 등록 폼의 입력값 유무 확인
 */
function isPass() {  // function 키워드로 함수를 정의할 수 있음.

    if (!$.trim($("#btitle").val())) {
        alert("제목을 입력하세요!");
        $("#btitle").val("").focus();
        //iStatus = false;
        return false;
    }

    if (!$.trim($("#bcont").val())) {
        alert("내용을 입력하세요!");
        $("#bcont").val("").focus();
        //iStatus = false;
        return false;
    }

    return true;

}  // isPass()