<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"
        isThreadSafe="true" %>

<%@ include file="/view/template/taglib.jsp" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <%@ include file="/view/template/head-meta.jsp" %>
    <%@ include file="/view/template/head-link.jsp" %>
    <%@ include file="/view/template/head-script.jsp" %>

    <script>
        $(function () {
            // 검색 버튼 클릭 이벤트 처리
            $('#searchForm').submit(function (event) {
                event.preventDefault();
                var opt = $('select[name="opt"]').val();
                var condition = $('input[name="condition"]').val();
                $.ajax({
                    url: '/json/list.do',
                    type: 'POST',
                    dataType: "json",
                    data: {
                        opt: opt,
                        condition: condition
                    },
                    success: function (data) {
                        debugger;

                        // $('#boardList').html(data.boardList);
                        // var boardList = data.boardList;
                        console.log(data);
                        <c:set var="boardList" value='${data.boardList}'/>
                    }
                });
            });
            // 등록 버튼 클릭 이벤트 처리
            $('#insertBtn').click(function () {
                location.href = '/insert.do';
            });
            // 페이지 번호 클릭 이벤트 처리
            $(document).on('click', '.page-link', function (event) {
                event.preventDefault();
                var pageNum = $(this).attr('data-page');
                $.ajax({
                    url: '/json/list.do',
                    type: 'POST',
                    data: {
                        pageNum: pageNum
                    },
                    success: function (data) {
                        debugger;

                        // JSTL에서 데이터 바인딩?
                        // HTML 템플릿을 통해 데이터 바인딩?
                        // HTML 동적 생성을 통해 데이터 바인딩?

                        // var html = "";
                        // var boardList = data.boardList;
                        // for (var i = 0; i < boardList.length; i++) {
                        //     html += "<tr>" +
                        //         "<td>" + boardList[i].bno + "</td>" +
                        //         "<td>" + boardList[i].btitle + "</td>" +
                        //         "<td>" + boardList[i].bhit + "</td>" +
                        //         "<td>" + boardList[i].bdate + "</td>" +
                        //         "</tr>";
                        // }
                        // $("#boardList tbody").append(html);
                        // $("#totalBoardCount").text("총 게시물 수: " + boardList.length + "개");
                    },
                    error: function (xhr, status, error) {
                        console.error("에러 발생");
                    }
                });
            });
        });
    </script>
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
                        <form id="searchForm">
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
                    <a>총 게시물 수: ${fn:length(data.boardList)}개</a>
                </span>

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
                    <c:if test="${not empty data.boardList}">
                        <c:forEach items="${data.boardList}" var="boardAttr">
                            <tr>
                                <td>${boardAttr.bno}</td>
                                <td>
                                    <a href="/java/content.do?bno=${boardAttr.bno}">
                                        <c:out value="${boardAttr.btitle}" default="제목 없음"/>
                                    </a>
                                </td>
                                <td>${boardAttr.bhit}</td>
                                <td>${boardAttr.bdate}</td>
                            </tr>
                        </c:forEach>
                    </c:if>
                    <c:if test="${empty data.boardList}">
                        <tr>
                            <td colspan="4">목록이 없습니다!</td>
                        </tr>
                    </c:if>
                    </tbody>
                    <tfoot>
                    <tr>
                        <td colspan="4">
                            <input type="button" class="btn btn-primary float-end" value="등록"
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
</div>
</body>
</html>