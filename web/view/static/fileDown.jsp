<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"
        isThreadSafe="true" %>

<%@ include file="/view/template/taglib.jsp" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <%@ include file="/view/template/head-meta.jsp" %>
    <%@ include file="/view/template/head-link.jsp" %>
    <%@ include file="/view/template/head-script.jsp" %>

    <link rel="stylesheet" href="https://www.jeasyui.com/easyui/themes/default/easyui.css">
    <link rel="stylesheet" href="https://www.jeasyui.com/easyui/themes/icon.css">
    <script src="https://www.jeasyui.com/easyui/jquery.min.js"></script>
    <script src="https://www.jeasyui.com/easyui/jquery.easyui.min.js"></script>

    <script>
        // 페이지 로딩 시, datagrid를 생성하고 초기 데이터를 바인딩합니다.
        $(function () {
            $('#table-dg').datagrid({
                // Java 서버에서 파일 목록 json
                url: '/json/fileList.do',
                rownumbers: true,
                pagination: true, // 페이징 기능을 사용함
                pageSize: 1000, // 한 페이지에 보여줄 행 수
                pageList: [1000], // 페이지 당 행 수 선택 옵션
                columns: [[
                    {field: 'name', title: '파일명', width: "60%", align: 'left'},
                    {field: 'size', title: '파일크기', width: "10%", align: 'center'},
                    {
                        field: 'WEB ajax', title: 'WEB ajax', width: "10%", align: 'center',
                        formatter: function (value, row, index) {
                            return '<button class="btn-download" onclick="downFile(\'' + row.name + '\')">다운</button>';
                        }
                    },
                    {
                        field: 'WEB a링크', title: 'WEB a링크', width: "10%", align: 'center',
                        formatter: function (value, row, index) {
                            return '<a class="btn-download" download="' + row.name + '" href="/upload/' + encodeURIComponent(row.name) + '">다운</a>';
                        }
                    },
                    {
                        field: 'WAS base64', title: 'WAS base64', width: "10%", align: 'center',
                        formatter: function (value, row, index) {
                            return '<button class="btn-download" onclick="downBase64(\'' + row.name + '\')">다운</button>';
                        }
                    }
                ]]
            });

            // 페이지 변경 이벤트 핸들러 등록
            $('#table-dg').datagrid('getPager').pagination({
                onSelectPage: function (pageNumber, pageSize) {
                    currentPage = pageNumber;
                    bindDataGrid();
                }
            });

            $('#input-fileComp').on('click', function () {
                $.ajax({
                    url: '/json/fileComp.do',
                    type: 'POST',
                    dataType: 'json',
                    contentType: 'application/json',
                    data: {},
                    success: function (response) {
                        // 파일 다운로드
                        downFile(response.fileName);
                    },
                    error: function (xhr, status, error) {
                        console.error('압축 처리 중 오류가 발생했습니다.');
                        console.error(xhr, status, error);
                    }
                });
            });
        });

        // 파일을 다운로드하는 함수를 추가합니다.
        function downFile(fileName) {
            $.ajax({
                url: '/upload/' + encodeURIComponent(fileName), // 서버에서 파일 다운로드 링크를 제공하는 API 엔드포인트
                type: 'GET',
                xhrFields: {
                    responseType: 'blob' // 서버가 binary 데이터를 응답할 경우, responseType을 blob으로 설정하여 다운로드 가능한 형태로 변환
                },
                success: function (response) {
                    var link = document.createElement('a');
                    link.href = window.URL.createObjectURL(response);
                    link.download = fileName;
                    link.click();
                },
                error: function (xhr, status, error) {
                    // 에러 처리
                    console.error(error);
                }
            });
        }

        function downBase64(fileName) {
            $.ajax({
                url: '/json/fileDown.do',
                type: 'GET',
                contentType: 'application/json',
                data: {
                    "fileName": fileName
                },
                success: function (response) {
                    var base64Data = response.fileCont;
                    var downloadLink = document.createElement('a');
                    downloadLink.href = 'data:application/octet-stream;base64,' + base64Data;
                    downloadLink.download = response.fileName;
                    downloadLink.click();
                },
                error: function (xhr, status, error) {
                    // 에러 처리
                    console.error(error);
                }
            });
        }

    </script>
</head>

<body>

<!-- 상단 메뉴 -->
<%@ include file="/view/template/body-header.jsp" %>

<div class="container mt-4">
    <div class="row">
        <!-- 좌측 메뉴 -->
        <%@ include file="/view/template/body-sidebar.jsp" %>

        <%-- 우측 내용  --%>
        <div class="col-md-9">
            <div class="card">
                <div class="mb-3 text-center fw-bold">
                    <label class="form-label">파일 다운로드</label>
                </div>
                <input type="button" class="btn btn-primary" id="input-fileComp" value="전체 압축">
                <!-- DataGrid를 표시할 div 요소를 추가합니다. -->
                <table id="table-dg" style="width:100%;height:400px"></table>
            </div>
        </div>
    </div>
</div>

</body>

</html>