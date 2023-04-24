<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"
        isThreadSafe="true" %>

<%@ include file="/view/template/taglib.jsp" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <%@ include file="/view/template/head-meta.jsp" %>
    <%@ include file="/view/template/head-link.jsp" %>
    <%@ include file="/view/template/head-script.jsp" %>

    <script>
        $(function () {
            var progressBar = $("#progress-bar");
            var progressLabel = $(".progress-label");

            progressBar.progressbar({
                value: true,
                change: function () {
                    progressLabel.text("진행률 : " + progressBar.progressbar("value") + "%");
                },
                complete: function () {
                    progressLabel.text("Complete!");
                    $(".ui-dialog button").last().trigger("focus");
                }
            });

            $('form').ajaxForm({
                url: "/json/fileUpload.do",
                type: "POST",
                beforeSubmit: function (arr, $form, options) {
                    progressBar.progressbar("value", 0);
                },
                uploadProgress: function (event, position, total, percentComplete) {
                    progressBar.progressbar("value", percentComplete);
                },
                success: function (text, status, xhr, element) {
                    progressBar.progressbar("value", 100);
                }
            });
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
                <form id="upload-form" enctype="multipart/form-data">
                    <div class="mb-3">
                        <label for="upload-file" class="form-label">파일 업로드</label>
                        <input type="file" class="form-control" id="upload-file" name="data">
                    </div>
                    <div class="progress-label"></div>
                    <div id="progress-bar"></div>

                    <br>
                    <button type="submit" class="btn btn-primary">업로드</button>
                </form>


            </div>
        </div>
    </div>
</div>

</body>

</html>