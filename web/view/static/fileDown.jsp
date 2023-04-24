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
        $(function () {
            $('#dg').datagrid({
                url: '/json/fileList.do', // Java 서버의 엔드포인트로 변경하세요.
                columns: [[
                    {field: 'name', title: 'File Name', width: 200},
                    {field: 'size', title: 'File Size', width: 100},
                    {
                        field: 'action', title: 'Action', width: 100, align: 'center',
                        formatter: function (value, row, index) {
                            return '<a href="javascript:void(0)" onclick="downloadFile(\'' + row.name + '\')">Download</a>';
                        }
                    }
                ]]
            });

            // 파일을 다운로드하는 함수를 추가합니다.
            function downloadFile(fileName) {
                window.location.href = 'your_download_endpoint?name=' + encodeURIComponent(fileName); // 다운로드 엔드포인트를 적절히 변경하세요.
            }

            // var progressBar = $("#progress-bar");
            // var progressLabel = $(".progress-label");
            //
            // progressBar.progressbar({
            //     value: true,
            //     change: function () {
            //         progressLabel.text("진행률 : " + progressBar.progressbar("value") + "%");
            //     },
            //     complete: function () {
            //         progressLabel.text("완료");
            //         $(".ui-dialog button").last().trigger("focus");
            //     }
            // });
            //
            // $('form').ajaxForm({
            //     url: "/json/fileUpload.do",
            //     type: "POST",
            //     beforeSubmit: function (formData, jqForm, options) {
            //
            //         var files = $('#upload-file')[0].files;
            //         if (files.length < 1) return false;
            //
            //         var formData = new FormData();
            //
            //         Array.from(files).forEach(function (file) {
            //             formData.append('files[]', file);
            //         });
            //
            //         options.data = formData;
            //
            //         return true;
            //     },
            //     uploadProgress: function (event, position, total, percentComplete) {
            //         progressBar.progressbar("value", percentComplete);
            //     },
            //     success: function (text, status, xhr, element) {
            //         progressBar.progressbar("value", 100);
            //     }
            // });
        });
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

                <label for="upload-file" class="form-label">파일 목록</label>
                <!-- DataGrid를 표시할 div 요소를 추가합니다. -->
                <table id="dg" style="width:100%;height:400px"></table>

                <%--                --%>
                <%--                <form id="upload-form" enctype="multipart/form-data">--%>
                <%--                    <div class="mb-3 text-center fw-bold">--%>
                <%--                        <label for="upload-file" class="form-label">파일 목록</label>--%>
                <%--                        <input type="file" class="form-control" id="upload-file" name="data[]" multiple>--%>
                <%--                    </div>--%>
                <%--                    <div class="progress-label"></div>--%>
                <%--                    <div id="progress-bar"></div>--%>

                <%--                    <br>--%>
                <%--                    <button type="submit" class="btn btn-primary float-end">업로드</button>--%>
                <%--                </form>--%>

                <%--                <table id="dg" title="Files" class="easyui-datagrid" style="width:700px;height:250px"--%>
                <%--                       url="get_files.jsp"--%>
                <%--                       toolbar="#toolbar" pagination="true"--%>
                <%--                       rownumbers="true" fitColumns="true" singleSelect="true">--%>
                <%--                    <thead>--%>
                <%--                    <tr>--%>
                <%--                        <th field="name" width="50">File Name</th>--%>
                <%--                        <th field="size" width="50">Size</th>--%>
                <%--                        <th field="date" width="50">Upload Date</th>--%>
                <%--                        <th field="action" width="50" formatter="formatActions">Actions</th>--%>
                <%--                    </tr>--%>
                <%--                    </thead>--%>
                <%--                </table>--%>
            </div>
        </div>
    </div>
</div>

</body>

</html>