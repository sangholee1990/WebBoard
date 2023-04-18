package test;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLReadTest {

    public static void main(String[] args) throws Exception {

        //***XML문서를 Document라는 객체로 인식.***
        //1.문서를 읽기위한 공장을 만들어야 한다.
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        //2.빌더를 생성
        DocumentBuilder builder = factory.newDocumentBuilder();

        //3.생성된 빌더를 통해서 xml문서를 Document객체로 파싱해서 가져온다.
        //Document org에서 가져온다!!!
        Document doc = builder.parse("src/test/personal.xml");
        Element root = doc.getDocumentElement();
        System.out.println("ROOT: " + root.getNodeName());

        //루트의 자식노드수 구하기.
        NodeList all = root.getChildNodes();
        System.out.println("루트의 지식노드수 :" + all.getLength());

        for (int i = 0; i < all.getLength(); i++) {
            Node node = all.item(i);
            System.out.println("자식노드 : " + node.getNodeName());
        }

        //personal 이라는 이름을 가진 요소들을 모두 가려내는 작업.
        NodeList n_list = root.getElementsByTagName("personal");

        //첫번째 요소 personal이라는 요소
        Element p = (Element) n_list.item(0);
        Node s_node = n_list.item(0);
        NodeList s_list = s_node.getChildNodes();

        for (int i = 0; i < s_list.getLength(); i++) {
            if (!s_node.getNodeName().equals("#text")) {
                Node no = s_node.getFirstChild();
                System.out.println(no.getNodeName());
            }
        }

        //얻어낸 personal의 자식요소들 중에서 name을 구하고 싶을때
        NodeList names = p.getElementsByTagName("name");
        Element el_name = (Element) names.item(0);

        //name의 값을 구한다.
        Node txt = el_name.getFirstChild();
        System.out.println("Names: " + txt.getNodeValue());

        //personal의 두번째 요소중 phone을 구하자!
        p = (Element) n_list.item(1);
        names = p.getElementsByTagName("phone");
        el_name = (Element) names.item(0);
        txt = el_name.getFirstChild();

        System.out.println("phone: " + txt.getNodeValue());
    }
}

