package utils;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import controller.Action;
import controller.ActionForward;

public class WebUtils {

    public static void main(String[] args) {
    }

    private static final Gson gson = new Gson();

    public static void writeResponse(HttpServletRequest request, HttpServletResponse response) {

        response.setContentType("application/json; charset=UTF-8");

        Map<String, Object> mapData = new HashMap<>();

        try {
            mapData = WebUtils.extractAttributes(request);
            WebUtils.writeJsonResponse(response, mapData);
        } catch (IOException ioe) {
            System.out.println(String.format("[ERROR] IOException : %s", ioe.getMessage()));
            WebUtils.handleError(request, response);
        } catch (Exception e) {
            System.out.println(String.format("[ERROR] Exception : %s", e.getMessage()));
            WebUtils.handleError(request, response);
        }
    }

    public static String getRequestCommand(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        String contextPath = request.getContextPath();
        return requestURI.substring(contextPath.length());
    }

    public static Action createActionInstance(ActionForward actionInfo) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        Class<?> className = Class.forName(actionInfo.getClassName());
        return (Action) className.newInstance();
    }


    public static void handleError(HttpServletRequest request, HttpServletResponse response) {
        try {
//            Map<String, Object> mapData = new HashMap<>();
//            mapData.put("error", "오류 발생");
//            WebUtils.writeJsonResponse(response, mapData);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "오류 발생");
        } catch (IOException ioe) {
            System.out.println(String.format("[ERROR] IOException : %s", ioe.getMessage()));
        } catch (Exception e) {
            System.out.println(String.format("[ERROR] Exception : %s", e.getMessage()));
        }
    }

    public static void handleResponse(HttpServletRequest request, HttpServletResponse response, ActionForward actionInfo) throws ServletException, IOException {
        // 비동기 통신에서 화면 전환없이 데이터 접근/전달
        if (actionInfo.isJson()) {
            WebUtils.writeResponse(request, response);
        } else if (actionInfo.isRedirect()) {
            // 동기 통신에서 데이터 접근/전달없이 화면 전환
            response.sendRedirect(actionInfo.getPath());
        } else {
            // 동기 통신에서 데이터 접근/전달 및 화면 전환
            RequestDispatcher dispatcher = request.getRequestDispatcher(actionInfo.getPath());
            dispatcher.forward(request, response);
        }
    }

    public static Map<String, Object> extractAttributes(HttpServletRequest request) {
        Map<String, Object> mapData = new HashMap<>();
        Enumeration<String> attributeNames = request.getAttributeNames();
        while (attributeNames.hasMoreElements()) {
            String key = attributeNames.nextElement();
            Object val = request.getAttribute(key);
            mapData.put(key, val);
        }
        return mapData;
    }

    public static void writeJsonResponse(HttpServletResponse response, Map<String, Object> mapData) throws IOException {
        response.setContentType("application/json");

        try (PrintWriter out = response.getWriter()) {
            out.print(gson.toJson(mapData));
            out.flush();
        }
    }

}
