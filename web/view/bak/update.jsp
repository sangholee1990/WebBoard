<%@page import="oracle.net.aso.r" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="java.sql.*" %>
<%@page import="java.util.*, model.*" %>

<%
    BoardVO boardAttr = (BoardVO) request.getAttribute("boardAttr");
%>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>글쓰기</title>
    <script type="text/javascript" src="/js/jquery.js"></script>
    <script type="text/javascript" src="/js/update.js"></script>
</head>
<body>
<h2 class="title">수정하기</h2>
<div id="option">
    <form id=updateForm>
        <table id="table">

            <tr>
                <td><input type="hidden" name="bno" value="<%=boardAttr.getBno()%>"/></td>
            </tr>

            <tr>
                <th>제목</th>
                <td><input type="text" name="btitle" id="btitle" size="36"/></td>
            </tr>

            <tr>
                <th>내용</th>
                <td><textarea name="bcont" id="bcont" rows="9" cols="37"></textarea></td>
            </tr>

            <tr>
                <th colspan="2">
                    <input type="button" value="수정" id="updateBtn"/>
                    <input type="button" value="취소" id="resetBtn"/>
                    <input type="button" value="목록" onclick="location='/list.do';"/>
                </th>
            </tr>

        </table>
    </form>
</div>
</body>
</html>