package utils;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import controller.ActionForward;

public class XmlUtils {

    private final static Logger log = Logger.getGlobal();

    private XmlUtils() {
    }

    public static void main(String[] args) {
        String urlName = "/json/list.do";
        System.out.println(urlName);

        boolean result = urlName.toLowerCase().contains("json") ? true : false;
        System.out.println(result);
    }

    /**
     * XML로 정의된 파일을 읽어 ActionForward와 매핑
     *
     * @param inputFile
     * @return
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    public static Map<String, ActionForward> parseActionXml(String inputFile) throws ParserConfigurationException, SAXException, IOException {

        Map<String, ActionForward> actionMap = new Hashtable<String, ActionForward>();

        if (inputFile == null) return actionMap;

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(inputFile);
        doc.getDocumentElement().normalize();
        NodeList actionList = doc.getElementsByTagName("action");

        for (int i = 0; i < actionList.getLength(); i++) {
            Node actionNode = actionList.item(i);

            if (actionNode.getNodeType() == Node.ELEMENT_NODE) {
                Element action = (Element) actionNode;
                String urlName = action.getAttribute("urlName");
                String className = action.getAttribute("className");
                String path = action.getAttribute("path");
//                boolean isRedirect = action.getAttribute("isRedirect").toLowerCase().equals("true") ? true : false;
                String isRedirect = action.getAttribute("isRedirect");
                String title = action.getAttribute("title");
                boolean isJson = urlName.toLowerCase().contains("json") ? true : false;

                // 객체 설정
                ActionForward actFor = new ActionForward();
                actFor.setUrlName(urlName);
                actFor.setClassName(className);
                actFor.setTitle(title);
                actFor.setJson(isJson);

                if (path != null && !path.isEmpty()) {
                    actFor.setPath(path);
                }

                if (isRedirect.toLowerCase().equals("true")) {
                    actFor.setRedirect(true);
                }

                actionMap.put(urlName, actFor);
            }
        }
        return actionMap;
    }
}
