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
    <title>목록</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe" crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-pagination/1.2.7/jquery.pagination.min.js"></script>
</head>

<body>

<%-- 검색 버튼 --%>
<h1 class="text-center">목록</h1>
<div class="row">
    <div class="col-md-6 offset-md-3">
        <form>
            <div class="input-group mb-3">
                <select class="form-select" name="opt">
                    <option value="0">제목</option>
                    <option value="1">등록일</option>
                </select>
                <input type="text" class="form-control" name="condition" placeholder="검색어를 입력하세요">
                <button type="submit" class="btn btn-primary">검색</button>
            </div>
        </form>
    </div>
</div>

<%-- 검색 버튼 --%>
<table class="table table-striped table-bordered">
    <thead>
    <tr>
        <th>번호</th>
        <th>제목</th>
        <th>조회수</th>
        <th>등록일</th>
    </tr>
    </thead>

    <tbody>
    <%
        if ((boardList != null) && (boardList.size() > 0)) {
            for (BoardVO boardAttr : boardList) {
    %>
    <tr>
        <td><%=boardAttr.getBno()%>
        </td>
        <td><a href="/content.do?bno=<%=boardAttr.getBno()%>"><%=BoardService.nullCheck(boardAttr.getBtitle(), "no title")%>
        </a></td>
        <td><%=boardAttr.getBhit()%>
        </td>
        <td><%=boardAttr.getBdate()%>
        </td>
    </tr>
    <%
        }
    } else {
    %>
    <tr>
        <td colspan="4">목록이 없습니다!</td>
    </tr>
    <%
        }
    %>
    </tbody>

    <tfoot>
    <tr>
        <td colspan="4">
            <input type="button" value="등록" class="btn btn-primary float-end" onclick="location='/insert.do';"/>
        </td>
    </tr>
    </tfoot>

</table>

<%-- 부트스트랩 페이징 --%>
<nav aria-label="Page navigation example">
    <ul class="pagination justify-content-center">
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