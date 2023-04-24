package controller;

import javax.servlet.*;
import javax.servlet.annotation.WebListener;


// 서블릿 컨테이너로부터 컨텍스트 시작/종료 시 알림 제공
@WebListener
public class BoardContextListener implements ServletContextListener {

    // 컨텍스트 시작
    public void contextInitialized(ServletContextEvent event) {
        System.out.println(String.format("[START] 서블릿 컨텍스트 생성 : %s", "contextInitialized"));

    }

    // 컨텍스트 종료
    public void contextDestroyed(ServletContextEvent event) {
        System.out.println(String.format("[END] 서블릿 컨텍스트 소멸 : %s", "contextDestroyed"));
    }
}

