<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="java.sql.*" %>
<%@page import="java.util.*, model.*" %>

<%
    BoardVO boardAttr = (BoardVO) request.getAttribute("boardAttr");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>상세보기</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
    <script src="/js/jquery.js"></script>
</head>

<body>
<div class="container">
    <h1 class="my-4">수정</h1>
    <form action="/update.do" method="post">
        <input type="hidden" name="bno" value="<%=boardAttr.getBno()%>">
        <div class="form-group">
            <label for="btitle">제목</label>
            <input type="text" class="form-control" id="btitle" name="btitle" value="<%=boardAttr.getBtitle()%>">
        </div>
        <div class="form-group">
            <label for="bcont">내용</label>
            <textarea class="form-control" id="bcont" name="bcont" rows="10"><%=boardAttr.getBcont()%></textarea>
        </div>
         <div class="form-group">
            <label for="bhit">조회수</label>
            <input type="text" class="form-control" id="bhit" name="bhit" value="<%=boardAttr.getBhit()%>" readonly>
        </div>


        <div class="form-group text-center">
            <button type="submit" class="btn btn-primary">수정</button>
            <a href="/java/list.do" class="btn btn-secondary">목록</a>
        </div>
    </form>
</div>

</body>
</html>