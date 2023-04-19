<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="java.sql.*" %>
<%@page import="java.util.*, model.*" %>

<%
    BoardVO boardAttr = (BoardVO) request.getAttribute("boardAttr");
%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>상세보기</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
</head>
<body>
<!-- 상단 메뉴 -->
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container">
        <a class="navbar-brand" href="#">게시판</a>
    </div>
</nav>

<!-- 좌측 사이드 바와 내용 -->
<div class="container mt-4">
    <div class="row">
        <div class="col-md-3">
            <div class="card">
                <div class="card-header">주요 기능</div>
                <ul class="list-group list-group-flush">
                    <li class="list-group-item"><a href="#">메뉴 1</a></li>
                    <li class="list-group-item"><a href="#">메뉴 2</a></li>
                    <li class="list-group-item"><a href="#">메뉴 3</a></li>
                </ul>
            </div>
        </div>

        <div class="col-md-9">
            <div class="card">
                <div class="card-header">내용</div>
                <div class="card-body">
                    <table class="table table-bordered">
                        <tbody>
                        <tr>
                            <th style="width: 20%">번호</th>
                            <td><%=boardAttr.getBno()%>
                            </td>
                        </tr>
                        <tr>
                            <th style="width: 20%">제목</th>
                            <td><%=boardAttr.getBtitle()%>
                            </td>
                        </tr>
                        <tr>
                            <th>내용</th>
                            <td><%=boardAttr.getBcont()%>
                            </td>
                        </tr>
                        <tr>
                            <th>조회수</th>
                            <td><%=boardAttr.getBhit()%>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                    <div class="btn-group" role="group">
                        <a class="btn btn-outline-secondary" href="/list.do">목록</a>
                        <a class="btn btn-danger" id="deleteBtn">삭제</a>
                        <a class="btn btn-primary" href="/update.do?bno=<%=boardAttr.getBno()%>">수정</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-35E0yWU7yVTvEuhuJ4O4KX8MNoZMkJmJx/e05KjNCQyfOF5y5q3PKgffBwhC+imq"
        crossorigin="anonymous"></script>
<script type="text/javascript" src="/js/delete.js"></script>
</body>
</html>
