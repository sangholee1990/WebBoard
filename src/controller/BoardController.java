package controller;

import java.io.File;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import utils.WebUtils;
import utils.XMLUtils;

import java.util.logging.Logger;

/**
 * 서블릿 컨테이너
 * 게시판 관련 Controller
 */
@WebServlet(name = "BoardFrontController", urlPatterns = "*.do")
public class BoardController extends HttpServlet {

//     private EntityManagerFactory emf;

    private static Map<String, ActionForward> actionRequestMapping = new Hashtable<String, ActionForward>();

    private final static Logger log = Logger.getGlobal();

    @PostConstruct
    public void postConstruct() {
        System.out.println(String.format("[START] 서블릿 컨테이너 : %s", " postConstruct"));
    }

    @Override
    public void init() throws ServletException {
        super.init();

        System.out.println(String.format("[START] 서블릿 컨테이너 : %s", " init"));


        try {

            // XML 설정 파일을 이용하여 읽기
//            String configInfo = "E:/SYSTEMS/dev/workspace/01.CodeReview/WebsiteBoard/web/config/action.xml";

            // 웹 어플리케이션 최상단 배포 위치
            // C:\dev\workspace\01.CodeReview\WebBoard\classes\artifacts\websiteBoard_war_exploded
            String configInfo = getServletContext().getRealPath("config/action.xml");
            System.out.println(String.format("[CHECK] configInfo : %s", configInfo));

            Map<String, ActionForward> mapping = XMLUtils.parseActionXml(configInfo);
            System.out.println(String.format("[CHECK] mapping : %s", mapping));

            actionRequestMapping.putAll(mapping);

        } catch (ParserConfigurationException | SAXException | IOException e) {
            System.out.println(String.format("[ERROR] XML 파일 읽기 오류 : %s", e.getMessage()));
        } catch (Exception e) {
            System.out.println(String.format("[ERROR] 서블릿 컨테이너 : %s : %s", "Exception", e.getMessage()));
        } finally {
            System.out.println(String.format("[END] 서블릿 컨테이너 : %s", "init"));
        }
    }

//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        System.out.println(String.format("[END] 서블릿 컨테이너 : %s", "doGet"));
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        System.out.println(String.format("[END] 서블릿 컨테이너 : %s", "doPost"));
//    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println(String.format("[START] 서블릿 컨테이너 : %s", "service"));

        Action action = null;

        // 요청정보 Body에 있는 문자열들을 인자값으로 지정한 문자코드로 인코딩
        request.setCharacterEncoding("UTF-8");

        // 응답정보 문자열들을 인자값으로 지정한 문자코드로 인코딩
        response.setContentType("text/html;charset=UTF-8");

        // /list.do
        String RequestURI = request.getRequestURI();
//        System.out.println(String.format("[CHECK] RequestURI : %s", RequestURI));

        // root
        String contextPath = request.getContextPath();
//        System.out.println(String.format("[CHECK] contextPath : %s", contextPath));

        // /list.do
        String command = RequestURI.substring(contextPath.length());
        System.out.println(String.format("[CHECK] command : %s", command));

        try {
            // urlName (command)으로부터 className, path에 대한 설정 정보 가져오기
            ActionForward actinInfo = actionRequestMapping.get(command);
            System.out.println(String.format("[CHECK] actinInfo : %s", actinInfo));

            if (actinInfo == null) {
                throw new Exception("action 정보가 없습니다.");
            }

            try {
                // className에 대한 객체 생성
                Class<?> ClassName = Class.forName(actinInfo.getClassName());

                // 객체화한 클래스는 Object형이다. 그러므로 강제 다운캐스팅해야 한다.
                action = (Action) ClassName.newInstance();

                // 오버라이딩한 execute() 호출
                action.execute(request, response, actinInfo);

            } catch (RuntimeException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                System.out.println(String.format("[ERROR] RuntimeException | ClassNotFoundException | InstantiationException | IllegalAccessException : %s", e.getMessage()));
                WebUtils.writeResponse(response, "오류 발생");
            }

            // 비동기 Ajax에서 데이터 결과를 servlet에서 응답 처리
            if (actinInfo.getReturnData() != null) {
                WebUtils.writeResponse(response, actinInfo.getReturnData());
                return;
            }

            // 동기에서 데이터 전달없이 바로가기
            // 동기에서 데이터 전달을 통해 이동
            if (actinInfo.isRedirect()) {
                response.sendRedirect(actinInfo.getPath());
            } else {
                RequestDispatcher dispatcher = request.getRequestDispatcher(actinInfo.getPath());
                dispatcher.forward(request, response);
            }

        } catch (Exception e) {
            System.out.println(String.format("[ERROR] 서블릿 컨테이너 : %s : %s", "Exception", e.getMessage()));
            WebUtils.writeResponse(response, "오류 발생");
        } finally {
            System.out.println(String.format("[END] 서블릿 컨테이너 : %s", "service"));
        }
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println(String.format("[END] 서블릿 컨테이너 : %s", " preDestroy"));
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(String.format("[END] 서블릿 컨테이너 : %s", "doPut"));
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(String.format("[END] 서블릿 컨테이너 : %s", "doDelete"));
    }

    @Override
    protected void doHead(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // HEAD 메서드는 GET 메서드의 요청과 동일한 응답을 요구하지만, 응답 본문을 포함하지 않습니다.
        System.out.println(String.format("[END] 서블릿 컨테이너 : %s", "doHead"));
    }

    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // OPTIONS 메서드는 목적 리소스의 통신을 설정
        System.out.println(String.format("[END] 서블릿 컨테이너 : %s", "doOptions"));
    }

    @Override
    protected void doTrace(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //TRACE 메서드는 목적 리소스의 경로를 따라 메시지 loop-back 테스트
        System.out.println(String.format("[END] 서블릿 컨테이너 : %s", "doTrace"));
    }


    // 테스트
//    public static void main(String[] args) {
//        String configInfo = System.getProperty("user.dir") + File.separator + "src/config/action.xml";
//        System.out.println(String.format("[CHECK] configInfo : %s", configInfo));
//
////        configInfo = this.getClass().getResource("/")
//        configInfo = BoardController.class.getResource(".").getPath();
//        System.out.println(String.format("[CHECK] configInfo : %s", configInfo));
//
//        configInfo = BoardController.class.getResource("/").getPath();
//        System.out.println(String.format("[CHECK] configInfo : %s", configInfo));
//
////        configInfo = System.getProperty("java.class.path") + File.separator + "src/config/action.xml";
////        configInfo = System.getProperty("java.class.path");
////        System.out.println(String.format("[CHECK] configInfo : %s", configInfo));
//
////        InputStream inStream = BoardFrontController.getClassLoader().getResourceAsStream("Web-INF/web.xml");
////        System.out.println(inStream);
////        String path = HttpServletRequest.getSession().getServletContext().getRealPath("/WEF-INF");
//
//    }
}