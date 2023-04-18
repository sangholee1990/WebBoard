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
    <script src="/js/jquery.js"></script>
</head>

<body>
<h1>상세보기</h1>
<table border="1">
    <tr>
        <th>제목</th>
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
        <th colspan="2">
            <input type="button" value="목록" onclick="location='/list.do';"/>
            <input type="button" value="삭제" onclick="location='/delete.do?bno=<%=boardAttr.getBno()%>';"/>
            <input type="button" value="수정" onclick="location='/update.do?bno=<%=boardAttr.getBno()%>';"/>
        </th>
    </tr>
</table>
</body>

</html>