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
            $('#dg').datagrid({
                // Java 서버에서 파일 목록 json
                url: '/json/fileList.do',
                pagination: true, // 페이징 기능을 사용함
                pageSize: 20, // 한 페이지에 보여줄 행 수
                pageList: [20, 50, 100], // 페이지 당 행 수 선택 옵션
                columns: [[
                    {field: 'name', title: '파일명', width: "60%", align: 'left'},
                    {field: 'size', title: '파일크기', width: "10%", align: 'center'},
                    {
                        field: 'action', title: '비동기 다운로드', width: "15%", align: 'center',
                        formatter: function (value, row, index) {
                            return '<button class="btn-download" onclick="downFile(\'' + encodeURIComponent(row.name) + '\')">다운로드</button>';
                        }
                    },
                    {
                        field: 'action', title: '동기 다운로드', width: "15%", align: 'center',
                        formatter: function (value, row, index) {
                            return '<a class="btn-download" href="/upload/' + encodeURIComponent(row.name) + '">다운로드</a>';
                        }
                    }
                ]]
            });

            // 페이지 변경 이벤트 핸들러 등록
            $('#dg').datagrid('getPager').pagination({
                onSelectPage: function (pageNumber, pageSize) {
                    currentPage = pageNumber;
                    bindDataGrid();
                }
            });

            // 압축 다운로드 버튼에 대한 이벤트 핸들러를 추가합니다.
            $("#btn-compressed-download").click(function () {
                var selectedRows = $("#dg").datagrid("getSelections");
                var fileNames = selectedRows.map(function (row) {
                    return row.name;
                });

                // 파일 이름 배열을 서버로 전송하고 압축된 파일을 다운로드합니다.
                downloadCompressedFiles(fileNames);
            });
        });

        // 데이터를 가져와서 datagrid에 바인딩하는 함수

        // function urlToPromise(url) {
        //     return new Promise(function (resolve, reject) {
        //         new JSZip().getBinaryContent(url, function (err, data) {
        //             if (err) {
        //                 reject(err);
        //             } else {
        //                 resolve(data);
        //             }
        //         });
        //     });
        // }

        function downloadSelectedFiles() {
            var selectedRows = $('#dg').datagrid('getSelections'); // 선택된 행 목록
            if (selectedRows.length === 0) {
                alert('다운로드할 파일을 선택하세요.');
                return;
            }

            // var fileNames = selectedRows.map(function (row) {
            //     return encodeURIComponent(row.name);
            // }).join(',');

            // const files = ['https://example.com/file1.txt', 'https://example.com/file2.txt'];
            // for (const fileUrl of files) {
            //     const file = await fetchFile(fileUrl);
            //     zip.file(file.name, file.blob);
            // }

            var zip = new JSZip();


// 파일을 가져와서 압축 파일에 추가합니다.
//             fetch('http://localhost:9000/upload/20230424142314_20230424_090630.png')
//                 .then(response => response.blob())
//                 .then(data => zip.file('http://localhost:9000/upload/20230424142314_20230424_090630.png', data));
// //             fetch('http://localhost:9000/upload/20230424142314_20230424_095022.png')
// //                 .then(response => response.blob())
// //                 .then(data => zip.file('http://localhost:9000/20230424142314_20230424_095022.png', data));

            // zip.file('http://localhost:9000/upload/20230424142314_20230424_090630.png');

             // zip.file("20230424142314_20230424_090630.png", urlToPromise('http://localhost:9000/upload/20230424142314_20230424_090630.png'), { binary: true });
             // zip.file("20230424142314_20230424_090630.png", urlToPromise('http://localhost:9000/upload/20230424142314_20230424_090630.png'), { binary: true });
             // zip.file("20230424142314_20230424_090630.png", urlToPromise('http://localhost:9000/upload/20230424142314_20230424_090630.png'), { binary: true });

            // debugger;

            // zip.file('hello.txt', 'Hello World!');
            // zip.file('hello.txt', "http://localhost:9000/upload/20230424142314_20230424_090630.png", { binary: true });

            fetch("http://localhost:9000/upload/20230424142314_20230424_090630.png")
                .then(response => response.blob()) // fetch로 받은 response를 blob 형태로 변환합니다.
                .then(blobData => {
                    zip.file('image.jpg', blobData); // JSZip 객체에 blob 형태로 변환된 이미지 파일을 추가합니다.
                });


            zip.generateAsync({type: 'blob'}).then((blob) => {
                saveAs(blob, 'files.zip');
            });

            // debugger;
            //
            // $.ajax({
            //     url: '/compressedDownload/' + fileNames, // 서버에서 압축된 파일을 제공하는 API 엔드포인트
            //     type: 'GET',
            //     xhrFields: {
            //         responseType: 'blob'
            //     },
            //     success: function (blob) {
            //         var link = document.createElement('a');
            //         link.href = window.URL.createObjectURL(blob);
            //         link.download = 'compressed_files.zip'; // 압축 파일명
            //         link.click();
            //     },
            //     error: function (xhr, status, error) {
            //         console.error(error);
            //     }
            // });
        }

        // 파일을 다운로드하는 함수를 추가합니다.
        function downFile(fileName) {
            debugger;

            $.ajax({
                url: '/upload/' + fileName, // 서버에서 파일 다운로드 링크를 제공하는 API 엔드포인트
                type: 'GET',
                xhrFields: {
                    responseType: 'blob' // 서버가 binary 데이터를 응답할 경우, responseType을 blob으로 설정하여 다운로드 가능한 형태로 변환
                },
                success: function (blob) {
                    var link = document.createElement('a');
                    link.href = window.URL.createObjectURL(blob);
                    link.download = fileName;
                    link.click();
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
                <!-- DataGrid를 표시할 div 요소를 추가합니다. -->
                <table id="dg" style="width:100%;height:400px"></table>
            </div>
        </div>
    </div>
</div>

</body>

</html>