<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%-- JSTL core 라이브러리 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- JSTL prefix 및 uri 변경 라이브러리 --%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>게시판 목록</title>
</head>
<body>
<h1>게시판 목록</h1>
<div id="search">
    <form>
        <select name="opt">
            <option value="0">제목</option>
            <option value="1">등록일</option>
        </select>
        <input type="text" size="20" name="condition"/>&nbsp;
        <input type="submit" value="검색"/>
    </form>
</div>

<table border="1">
    <tr>
        <td colspan="4" align="right">
            <a>총 게시물 수 : ${fn:length(boardList)}개
            </a>
        </td>
    </tr>

    <tr>
        <th>번호</th>
        <th>제목</th>
        <th>조회수</th>
        <th>등록일</th>
    </tr>
    <c:if test="${not empty boardList}">
        <c:forEach items="${boardList}" var="boardAttr">
            <tr>
                <td>${boardAttr.bno}</td>
                <td><a href="/content.do?bno=${boardAttr.bno}"><c:out value="${boardAttr.btitle}" default="제목 없음"/></a></td>
                <td>${boardAttr.bhit}</td>
                <td>${boardAttr.bdate}</td>
            </tr>
        </c:forEach>
    </c:if>
    <c:if test="${empty boardList}">
        <tr>
            <td colspan="4">목록이 없습니다!</td>
        </tr>
    </c:if>
    <tr>
        <th colspan="5">
            <input type="button" value="등록" style="float: right;" onclick="location='/insert.do';"/>
        </th>
    </tr>
</table>
</body>
</html>