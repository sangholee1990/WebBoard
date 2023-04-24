package utils;

import controller.ActionForward;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Map;
import java.util.Properties;

public class TextUtils {

    private static Map<String, ActionForward> actionRequestMapping = new Hashtable<String, ActionForward>();

    public static void main(String[] args) {

        System.out.println(String.format("[START] TXT 읽기"));

        // 컬렉션 클래스(키,값 쌍으로 저장하는 구조) 키 이름이 url 매핑주소값 (/*.do)
        Properties prop = new Properties();

        try {

//            String configInfo = "E:/SYSTEMS/dev/workspace/01.CodeReview/WebsiteBoard/web/config/action.properties";
//            System.out.println();
            String configInfo = "E:/SYSTEMS/dev/workspace/01.CodeReview/WebsiteBoard/web/config/action.properties2";

            try (FileInputStream fis = new FileInputStream(configInfo)) {
                // properties 로드
                prop.load(fis);  // 프로퍼티 파일 로드

                Enumeration<?> enumeration = prop.keys();
                String key;
                while (enumeration.hasMoreElements()) {
                    key = (String) enumeration.nextElement();

                    ActionForward act = new ActionForward();
                    act.setUrlName(key);
                    act.setClassName(prop.getProperty(key));
                    actionRequestMapping.put(key, act);
                }

                System.out.println(String.format("[CHECK] actionRequestMapping : %s", actionRequestMapping));
            }
        } catch (IOException e) {
            System.out.println(String.format("[ERROR] IOException 오류 : 메시지 %s", e.getMessage()));
        } catch (Exception e) {
            System.out.println(String.format("[ERROR] Exception 오류 : 메시지 %s", e.getMessage()));
        } finally {
            System.out.println(String.format("[END] TXT 읽기"));
        }
    }
}