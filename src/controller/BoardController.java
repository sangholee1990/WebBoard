package controller;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import utils.WebUtils;
import utils.XmlUtils;

/**
 * 서블릿 컨테이너
 * 게시판 관련 Controller
 */
@WebServlet(name = "BoardFrontController", urlPatterns = "*.do")
public class BoardController extends HttpServlet {

//     private EntityManagerFactory emf;

    private static Map<String, ActionForward> actionRequestMapping = new Hashtable<String, ActionForward>();

    @PostConstruct
    public void postConstruct() {
        System.out.println(String.format("[START] 서블릿 컨테이너 : %s", " postConstruct"));
    }

    @Override
    public void init() throws ServletException {
        super.init();

        System.out.println(String.format("[START] 서블릿 컨테이너 : %s", " init"));

        try {
            // 웹 어플리케이션 최상단 배포 위치
            String configInfo = getServletContext().getRealPath("config/action.xml");
            System.out.println(String.format("[CHECK] configInfo : %s", configInfo));

            Map<String, ActionForward> mapping = XmlUtils.parseActionXml(configInfo);
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

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println(String.format("[START] 서블릿 컨테이너 : %s", "service"));

        Action action = null;

        // 요청정보 Body에 있는 문자열들을 인자값으로 지정한 문자코드로 인코딩
        request.setCharacterEncoding("UTF-8");

        // 응답정보 문자열들을 인자값으로 지정한 문자코드로 인코딩
        response.setContentType("text/html;charset=UTF-8");

        // /list.do
        String command = WebUtils.getRequestCommand(request);
        System.out.println(String.format("[CHECK] command : %s", command));

        try {
            // urlName (command)으로부터 className, path에 대한 설정 정보 가져오기
            ActionForward actionInfo = actionRequestMapping.get(command);
            System.out.println(String.format("[CHECK] actionInfo : %s", actionInfo));

            if (actionInfo == null) {
                throw new Exception("action 정보가 없습니다.");
            }

            if (!actionInfo.isRedirect()) {
                action = WebUtils.createActionInstance(actionInfo);
                action.execute(request, response, actionInfo);
            }

            // 제목 제공
            request.setAttribute("title", actionInfo.getTitle());

            WebUtils.handleResponse(request, response, actionInfo);

        } catch (RuntimeException | ClassNotFoundException | InstantiationException | IllegalAccessException rcil) {
            System.out.println(String.format("[ERROR] RuntimeException | ClassNotFoundException | InstantiationException | IllegalAccessException : %s", rcil.getMessage()));
            WebUtils.handleError(request, response);
        } catch (Exception e) {
            System.out.println(String.format("[ERROR] Exception : %s : %s", "Exception", e.getMessage()));
            WebUtils.handleError(request, response);
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