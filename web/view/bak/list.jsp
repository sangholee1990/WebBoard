<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%-- 외부에서 자바 import 예시 --%>
<%@page import="service.BoardService" %>
<%@page import="java.util.*, model.*" %>
<%
    BoardDAO dao = new BoardDAO();
    List<BoardVO> boardList = (List<BoardVO>) request.getAttribute("boardList");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>게시판 목록</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">

    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-pagination/1.2.7/jquery.pagination.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe" crossorigin="anonymous"></script>
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
        <td colspan="4" align="right"><a>총 게시물 수 : <%=boardList.size()%>개
        </a></td>
    </tr>

    <tr>
        <th>번호</th>
        <th>제목</th>
        <th>조회수</th>
        <th>등록일</th>
    </tr>
    <%
        if ((boardList != null) && (boardList.size() > 0)) {
            for (BoardVO boardAttr : boardList) {
    %>
    <tr>
        <th><%=boardAttr.getBno()%>
        </th>
        <th><a href="/content.do?bno=<%=boardAttr.getBno()%>"><%=BoardService.nullCheck(boardAttr.getBtitle(), "no title")%>
        </a></th>
        <th><%=boardAttr.getBhit()%>
        </th>
        <th><%=boardAttr.getBdate()%>
        </th>
    </tr>
    <%
        }
    } else {
    %>
    <tr>
        <th colspan="5">목록이 없습니다!</th>
    </tr>

    <%
        }
    %>

    <tr>
        <th colspan="5">
            <input type="button" value="등록" style="float: right;" onclick="location='/insert.do';"/>
        </th>
    </tr>

    <%-- 부트스트랩 --%>
    <nav aria-label="Page navigation example">
        <ul class="pagination">
            <li class="page-item">
                <a class="page-link" href="#" aria-label="Previous">
                    <span aria-hidden="true">&laquo;</span>
                    <span class="sr-only">Previous</span>
                </a>
            </li>
            <li class="page-item"><a class="page-link" href="#">1</a></li>
            <li class="page-item"><a class="page-link" href="#">2</a></li>
            <li class="page-item"><a class="page-link" href="#">3</a></li>
            <li class="page-item">
                <a class="page-link" href="#" aria-label="Next">
                    <span aria-hidden="true">&raquo;</span>
                    <span class="sr-only">Next</span>
                </a>
            </li>
        </ul>
    </nav>

    <%-- 제이쿼리 --%>
<%--    <div id="pagination"></div>--%>

</table>
</body>


</html>

<%--<script>--%>
<%--    $('#pagination').pagination({--%>
<%--        dataSource: '/data',--%>
<%--        pageSize: 10,--%>
<%--        callback: function (data, pagination) {--%>
<%--            // 서버에서 받은 데이터를 가지고 HTML 페이지 생성--%>
<%--            var html = createPage(data);--%>
<%--            $('#container').html(html);--%>
<%--        }--%>
<%--    });--%>
<%--</script>--%>