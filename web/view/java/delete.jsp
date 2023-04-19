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
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
</head>
<body>
<div class="container">
    <h2 class="text-center my-4">삭제</h2>
    <form id="deleteForm">
        <input type="hidden" name="bno" value="<%=boardAttr.getBno()%>"/>
        <div class="row justify-content-center">
            <div class="col-md-6">
                <div class="card">
                    <div class="card-body">
                        <div class="form-group">
                            <div class="text-center">
                                <input type="button" value="삭제" class="btn btn-danger mr-2" id="deleteBtn"/>
                                <input type="button" value="취소" class="btn btn-secondary" id="resetBtn"/>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </form>
</div>
</body>
</html>