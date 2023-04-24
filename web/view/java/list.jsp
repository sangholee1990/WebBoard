<%@page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8" isELIgnored="false" isThreadSafe="true"
        import="java.util.*, javax.servlet.http.*, model.*" %>

<%@ include file="/view/template/taglib.jsp" %>

<%
    BoardDAO dao = new BoardDAO();
    List<BoardVO> boardList = (List<BoardVO>) request.getAttribute("boardList");
%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <%@ include file="/view/template/head-meta.jsp" %>
    <%@ include file="/view/template/head-link.jsp" %>
    <%@ include file="/view/template/head-script.jsp" %>
</head>

<body>

<!-- 상단 메뉴 -->
<%@ include file="/view/template/body-header.jsp" %>

<div class="container mt-4">
    <div class="row">
        <!-- 좌측 메뉴 -->
        <%@ include file="/view/template/body-sidebar.jsp" %>

        <%-- 우측 내용  --%>
        <div class="col-md-9">
            <div class="card">

                <%-- 검색 버튼 --%>
                <div class="row">
                    <div class="col-md-8 offset-md-3">
                        <form>
                            <div class="input-group mb-3">
                                <select class="form-select" name="opt">
                                    <option value="0">제목</option>
                                    <option value="1">등록일</option>
                                </select>
                                <input type="text" class="form-control" name="condition" placeholder="검색어 입력">
                                <button type="submit" class="btn btn-primary">검색</button>
                            </div>
                        </form>
                    </div>
                </div>

                <span class="text-end">
                    <a>총 게시물 수: <%=boardList.size()%>개</a>
                </span>

                <%-- 주요 내용 --%>
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
                        <td>
                            <a href="/java/content.do?bno=<%=boardAttr.getBno()%>"><%=BoardService.nullCheck(boardAttr.getBtitle(), "no title")%>
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
                            <input type="button" value="등록" class="btn btn-primary float-end"
                                   onclick="location='/java/insert.do';"/>
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
            </div>
        </div>
    </div>
    <%-- 제이쿼리 --%>
    <%--    <div id="pagination"></div>--%>

</body>

</html>