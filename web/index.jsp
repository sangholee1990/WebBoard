<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"
        isThreadSafe="true" %>

<%--<%@ include file="/view/template/taglib.jsp" %>--%>
<%--일부 브라우저에서 동작하지 않을 수 있습니다.--%>
<%--JSTL 라이브러리가 포함되어 있어야 하므로, 추가적인 설정이 필요합니다.--%>
<%--클라이언트에게 새로운 요청을 보내지 않고, 서버 측에서 내부적으로 처리되므로, 캐시 관련 이슈가 발생할 수 있습니다.--%>
<%--<c:redirect url="/java/list.do" />--%>

<%--
JSTL과 EL을 사용하면 JSP 페이지를 작성하는 것이 더욱 편리해지며, 코드의 가독성도 향상됩니다.
그러나 JSP 페이지를 서블릿으로 변환하는 과정에서, JSTL과 EL이 서블릿 코드로 변환되므로, 이 과정에서 약간의 오버헤드가 발생할 수 있습니다.
하지만 이러한 오버헤드는 매우 작으며, 대부분의 경우 무시해도 무방합니다. 또한 JSP 페이지에서 서블릿 코드를 직접 작성하면 보다 세부적인 로직을 구현할 수 있지만, 코드의 가독성이 저하되며 작성 시간도 더 많이 소요됩니다.
따라서 일반적으로 JSTL과 EL을 사용하여 JSP 페이지를 작성하는 것이 더욱 효율적이며, 속도 차이도 크게 나타나지 않습니다.
--%>

<%
    response.sendRedirect("/java/list.do");
%>