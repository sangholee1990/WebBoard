<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>글쓰기</title>
    <script type="text/javascript" src="/js/jquery.js"></script>
    <script type="text/javascript" src="/js/insert.js"></script>
    <!-- 부트스트랩 추가 -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-6 col-md-offset-3">
            <h2 class="text-center">등록</h2>
            <form id="insertForm">
                <div class="form-group">
                    <label for="btitle">제목</label>
                    <input type="text" name="btitle" id="btitle" class="form-control"/>
                </div>

                <div class="form-group">
                    <label for="bcont">내용</label>
                    <textarea name="bcont" id="bcont" rows="9" cols="37" class="form-control"></textarea>
                </div>

                <div class="form-group text-center">
                    <input type="button" value="등록" id="insertBtn" class="btn btn-primary"/>
                    <input type="button" value="삭제" class="btn btn-danger" id="resetBtn"/>
                    <a href="/java/list.do" class="btn btn-outline-secondary">목록</a>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>