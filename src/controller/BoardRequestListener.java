package controller;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;

// 서블릿 컨테이너로부터 요청 생성/소멸 시 알림 제공
// HTTP 요청 시 각 메서드 3번씩 호출 (서블릿 컨테이너가 처리 과정에서 필터를 적용)
@WebListener
public class BoardRequestListener implements ServletRequestListener {
    // 요청 생성
    public void requestInitialized(ServletRequestEvent event) {
        System.out.println(String.format("[START] 서블릿 요청 생성 : %s", "requestInitialized"));
    }

    // 요청 소멸
    public void requestDestroyed(ServletRequestEvent event) {
        System.out.println(String.format("[END] 서블릿 요청 소멸 : %s", "requestDestroyed"));
    }
}
