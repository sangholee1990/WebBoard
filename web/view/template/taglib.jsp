<%-- JSP에서 자주 사용하는 지시자 --%>
<%--<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>--%>

<%-- isELIgnored="false"를 통해 EL 표현식 사용 --%>
<%-- EL 표현식 예시 : <%=board.getBno()%> -> ${board.bno} --%>

<%-- isThreadSafe="true"를 통해 다중 스레드에서 동시 객체를 공유하여 안전하게 처리  --%>
<%-- 다양한 import 라이브러리 지원 등 --%>
<%--<%@page language="java" contentType="text/html; charset=UTF-8"--%>
<%--         pageEncoding="UTF-8" isELIgnored="false" isThreadSafe="true"--%>
<%--         import="java.util.*, javax.servlet.http.*, model.*" %>--%>

<%-- JSTL core 라이브러리 --%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- JSTL prefix 및 uri 변경 라이브러리 --%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
