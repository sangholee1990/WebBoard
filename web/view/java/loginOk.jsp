<%@page import="model.BoardDAO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="org.json.simple.JSONObject" %>
<%@page import="java.sql.Connection" %>
<%@page import="java.sql.SQLException" %>


<%
    request.setCharacterEncoding("UTF-8");
    // JSONObject jsonObj = new JSONObject();
    String id = request.getParameter("id");
    String pw = request.getParameter("pw");
    // jsonObj.put("loginResult", "1");
    // out.print(jsonObj);
    // out.prit


    // out.println(id);


    // BoardDAO dao = new BoardDAO();
    // Map<String, Object> requestParameterMap = dao.requestParameterMap(request);

    // if (id == null) out.println("null 값 입니다.");
    //out.println(id);
    // out.println(pw);

//	if (id.equals("root") & pw.equals("1234")) {
    // session.setAttribute("id", id);
    //	response.sendRedirect("welcome.jsp");
//	} else {
//		out.print("로그인을 실패하였습니다.");
    //	response.sendRedirect("login.jsp");
    //}
%>	

