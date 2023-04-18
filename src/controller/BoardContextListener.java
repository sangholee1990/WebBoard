package controller;

import org.xml.sax.SAXException;
import utils.WebUtils;
import utils.XMLUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebListener;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;
import java.util.logging.Logger;


// 서블릿 컨테이너로부터 컨텍스트 시작/종료 시 알림 제공
@WebListener
public class BoardContextListener implements ServletContextListener {

    // 컨텍스트 시작
    public void contextInitialized(ServletContextEvent event) {
        System.out.println(String.format("[START] 서블릿 컨테이너 : %s", "init"));

    }

    // 컨텍스트 종료
    public void contextDestroyed(ServletContextEvent event) {
        System.out.println(String.format("[END] 서블릿 컨테이너 : %s", "init"));
    }
}

