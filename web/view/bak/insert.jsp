<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>글쓰기</title>
    <script type="text/javascript" src="/js/jquery.js"></script>
    <script type="text/javascript" src="/js/insert.js"></script>
</head>
<body>
<h2 class="title">글쓰기</h2>
<div id="option">
    <form id="insertForm">
        <table id="table">

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
                    <input type="button" value="등록" id="insertBtn"/>
                    <input type="button" value="취소" id="resetBtn"/>
                </th>
            </tr>

        </table>
    </form>
</div>
</body>
</html>