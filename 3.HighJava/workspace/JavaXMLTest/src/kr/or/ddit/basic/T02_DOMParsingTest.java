package kr.or.ddit.basic;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class T02_DOMParsingTest {
	
	public void parse() throws Exception {
		//DOM document 객체를 생성하기 위한 메서드
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		
		//DocumentBuilder 객체 생성하기
		DocumentBuilder builder = dbf.newDocumentBuilder();
		
		//DOM parser로부터 입력받은 파일을 파싱하도록 요청
		String url = new File("./src/new_book.xml").toURI().toString();
		Document document = builder.parse(url);
		
		//DOM Document 객체로부터 루트 엘리먼트 및 자식 객체 가져오기
		Element root = document.getDocumentElement();
		System.out.println("루트 엘리먼트 태그명 : " + root.getTagName());
		
		//하위 엘리먼트 접근방법1 : getElementsByTagName() 메서드 이용
		NodeList bookNodeList = root.getElementsByTagName("book");
		Node firstBookNode = bookNodeList.item(0);	//첫번째 항목
		Element firstBookElement = (Element) firstBookNode;
		
		//속성 접근방법1 : 엘리먼트 객체의 getAttribute() 메서드 이용
		System.out.println("엘리먼트 객체의 getAttribute() 메서드 이용 : "
								+ firstBookElement.getAttribute("isbn"));
		
		//속성 접근방법2 : 노드 객체의 getAttributes() 메서드 이용(속성 노드에 접근)
		NamedNodeMap nodeMap = firstBookNode.getAttributes();
		System.out.println("노드 객체의 geteAttributes() 메서드 이용 : "
								+ nodeMap.getNamedItem("isbn").getNodeValue());
		
		//하위 엘리먼트 접근방법2 : getChildNodes() 메서드 이용
		NodeList firstBookChildNodeList = firstBookNode.getChildNodes();
		//#text 노드(공백문자) 때문에 인덱스 값을 1로 설정해야함
		Node titleNode = firstBookChildNodeList.item(1);
		Element titleElement = (Element) titleNode;
		System.out.println("titleElement.getTagName() 메서드 이용 : "
				+ titleElement.getTagName());
		System.out.println("titleElement.getTextContent() 메서드 이용 : "
				+ titleElement.getTextContent());
		
		//전체 출력하기
		
		System.out.println("==================================================");
		System.out.printf("%8s %7s %18s %13s %9s\n", "ISBN", "분류", "제목", "저자", "가격");
//		System.out.println("ISBN\t분류\t제목\t저자\t가격");
		System.out.println("==================================================");
		for(int i = 0; i < bookNodeList.getLength(); i++) {
			Node bookNode = bookNodeList.item(i);
			Element element = (Element) bookNode;
			String isbn = element.getAttribute("isbn");
			String kind = element.getAttribute("kind");
			String title = element.getElementsByTagName("title")
					.item(0).getTextContent().trim();
			String author = element.getElementsByTagName("author")
					.item(0).getTextContent().trim();
			String price = element.getElementsByTagName("price")
					.item(0).getTextContent().trim();
			String str = String.format("%8s %8s %10s %10s %8s",
					isbn, kind, title, author, price);
			System.out.println(str);
			
			System.out.println("--------------------------------------------------");
//			System.out.println(isbn + "\t" + kind + "\t" + title + "\t" + author + "\t" + price);
		}
	}
	
	public static void main(String[] args) throws Exception {
		new T02_DOMParsingTest().parse();
	}
}
