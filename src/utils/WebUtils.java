package utils;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

public class WebUtils {

    public static void writeResponse(HttpServletResponse resp, Object attachObj) {

        PrintWriter out;
        JSONObject jsonObj = new JSONObject();

        System.out.println(String.format("[START] writeResponse"));

        try {
            out = resp.getWriter();
            out.print(attachObj);
        } catch (IOException e) {
            jsonObj.put("error", "오류 발생");
            try {
                out = resp.getWriter();
                out.print(jsonObj);
            } catch (IOException e2) {
                System.out.println(String.format("[ERROR] IOException : %s", e2.getMessage()));
            }
        } catch (Exception e) {
            System.out.println(String.format("[ERROR] Exception : %s", e.getMessage()));
        } finally {
            System.out.println(String.format("[END] writeResponse"));
        }
    }
}
