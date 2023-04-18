/**
 * 글 삭제를 위한 유효성 검사
 * - [삭제] 버튼을 클릭 이벤트 실행
 * - [취소] 버튼을 클릭 이벤트 발생
 */

$(document).ready(function () {

    /*[수정] 버튼을 클릭하면 자동 실행*/
    $(document).ready(function () {
        $('#deleteBtn').on('click', function () {
            var deleteResult = $('#contentForm').serialize()  /*입력된 모든 Element를 문자열의 데이터에 serialize 한다.*/

            debugger;

            $.ajax({
                type: "POST"
                , url: '/deleteOk.do'
                /*, contentType: 'application/json; charset=utf-8'*/
                , dataType: "json"
                , cache: false
                , data: deleteResult
                , success: onSuccess
                , error: onError
            });

            function onSuccess(result) {
                if (result && (result.deleteResult >= 0)) {
                    alert('삭제 성공 : ' + result.deleteResult);
                    alert('목록 페이지로 이동합니다.');
                    location.href = "/list.do";
                } else {
                    alert("에러 코드 : " + result.deleteResult);  // TODO : 에러코드에 따른 처리 (result.updateResult <= -1)
                }
            }

            function onError(request, status, error) {
                console.warn('ajax 통신 실패');
                alert("code : " + request.status + "\n" + "message : " + request.responseText + "\n" + "error : " + error);
            }
        });

        /*[취소] 버튼을 클릭하면 자동 실행*/
        $('#resetBtn').on('click', function () {
            alert('취소되었습니다.')
            $('form')[0].reset();
        });
    });
});