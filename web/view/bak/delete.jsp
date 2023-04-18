<%@page import="service.BoardService" %>
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
    <script type="text/javascript" src="/js/delete.js"></script>
</head>
<body>
<h2 class="title">삭제하기</h2>
<div id="option">
    <form id="deleteForm">
        <table id="table">

            <tr>
                <td><input type="hidden" name="bno" value="<%=boardAttr.getBno()%>"/></td>
            </tr>

            <!-- 				<tr>
                                <th>비밀번호</th>
                                <td><input type="password" name="password" size="50" /></td>
                            </tr> -->

            <tr>
                <th colspan="2">
                    <input type="button" value="삭제" id="deleteBtn"/>
                    <input type="button" value="취소" id="resetBtn"/>
                </th>
            </tr>

        </table>
    </form>
</div>
</body>
</html>