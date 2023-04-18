package service;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

public class BoardService {

    /**
     * 요청한 값을 키와 값으로 받습니다.
     *
     * @param request
     * @return Response
     */
    public static Map<String, Object> requestParameterMap(HttpServletRequest request) {

        Map<String, Object> requestParameterMap = new HashMap<String, Object>();
        Enumeration<String> requestNames = request.getParameterNames();
        while (requestNames.hasMoreElements()) {
            String key = (String) requestNames.nextElement();
            String value = request.getParameter(key);
            if (value == null) value = "";
            requestParameterMap.put(key, value);
        }
        return requestParameterMap;
    }


    /**
     * 결측값을 체크하여 다른 문자로 대치합니다.
     *
     * @param value
     * @return
     */
    public static String nullCheck(String value) {
        return nullCheck(value, "");
    }

    public static String nullCheck(String value, String defaultValue) {
        if (value == null)
            value = defaultValue;
        return value;
    }


}
