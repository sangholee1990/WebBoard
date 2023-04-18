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
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script type="text/javascript" src="/js/delete.js"></script>
</head>

<body>
<div class="container">
    <h1 class="my-4">상세 조회</h1>
    <table class="table table-striped table-bordered">
        <form id="contentForm">
            <input type="hidden" name="bno" value="<%=boardAttr.getBno()%>"/>
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
            <tr>
                <td colspan="2">
                    <a class="btn btn-outline-secondary" href="/list.do">목록</a>
                    <%--                <a class="btn btn-outline-danger"  href="/delete.do?bno=<%=boardAttr.getBno()%>">삭제</a>--%>
                    <a class="btn btn-danger" id="deleteBtn">삭제</a>
                    <a class="btn btn-primary" href="/update.do?bno=<%=boardAttr.getBno()%>">수정</a>
                </td>
            </tr>
        </form>
    </table>
</div>
</body>
</html>