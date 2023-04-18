package controller;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

// 서블릿 컨테이너로부터 세션 생성/소멸 시 알림 제공
@WebListener
public class BoardSessionListener implements HttpSessionListener {
    // 세션 생성
    public void sessionCreated(HttpSessionEvent event) {
        System.out.println(String.format("[START] 서블릿 세션 생성 : %s", "sessionCreated"));

    }

    // 세션 소멸
    public void sessionDestroyed(HttpSessionEvent event) {
System.out.println(String.format("[END] 서블릿 세션 소멸 : %s", "sessionDestroyed"));
    }
}
