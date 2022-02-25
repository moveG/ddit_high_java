package kr.or.ddit.basic;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * XML DOM을 이용한 XML문서 생성 예제
 */
public class T01_DOMCreationTest {
	/*
	 * W3C(World Wide Web Consortium) DOM(Document Object Model)
	 * 
	 * 1. DOM은 문서에 접근하는 표준방법으로, 이를 이용하면 플랫폼 및 개발언어에 독립적으로 문서의
	 *    내용, 구조 및 스타일 정보를 동적으로 핸들링(접근, 수정, 삭제)할 수 있다.
	 * 
	 * 2. W3C DOM 표준은 다음과 같이 크게 3가지로 나누어 볼 수 있다.
	 *    - Core DOM : 모든 문서타입을 위한 핵심 표준모델(API)
	 *    - XML DOM  : XML문서를 위한 표준모델
	 *    - HTML DOM : HTML문서를 위한 표준모델
	 *    
	 * 3. 예를들어, HTML DOM은 HTML 엘리먼트 요소 및 속성 정보를 핸들링하기 위한 인터페이스를 제공한다.
	 */
	public void createDoc() throws ParserConfigurationException
								 , IOException
								 , TransformerException {
		//XML문서를 생성하기 위한 DocumentBuilder객체 생성하기
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = dbf.newDocumentBuilder();
		
		//Docment 객체 생성
		Document document = builder.newDocument();
		
		//root 엘리먼트 생성
		Element root = document.createElement("root");
		
		//booklist 엘리먼트 생성
		Element booklist = document.createElement("booklist");
		
		//book 엘리먼트 생성 및 속성값 설정하기
		Element book = document.createElement("book");
		book.setAttribute("isbn", "B001");
		book.setAttribute("kind", "JAVA");
		
		//자식 엘리먼트 생성 및 설정
		Element title = document.createElement("title");
		title.setTextContent("자바초급");
		Element author = document.createElement("author");
		author.setTextContent("이순신");
		Element price = document.createElement("price");
		price.setTextContent("25000");
		
		//book 엘리먼트에 자식 엘리먼트 추가하기
		book.appendChild(title);
		book.appendChild(author);
		book.appendChild(price);
		
		booklist.appendChild(book);
		//==================================
		
		//book 엘리먼트 생성 및 속성값 설정하기
		book = document.createElement("book");
		book.setAttribute("isbn", "B002");
		book.setAttribute("kind", "JAVA");
		
		//자식 엘리먼트 생성 및 설정
		title = document.createElement("title");
		title.setTextContent("자바고급");
		author = document.createElement("author");
		author.setTextContent("김순신");
		price = document.createElement("price");
		price.setTextContent("28000");
		
		//book 엘리먼트에 자식 엘리먼트 추가하기
		book.appendChild(title);
		book.appendChild(author);
		book.appendChild(price);
		
		booklist.appendChild(book);
		//==================================
		
		//book 엘리먼트 생성 및 속성값 설정하기
		book = document.createElement("book");
		book.setAttribute("isbn", "B003");
		book.setAttribute("kind", "DB");
		
		//자식 엘리먼트 생성 및 설정
		title = document.createElement("title");
		title.setTextContent("디비설계");
		author = document.createElement("author");
		author.setTextContent("박순신");
		price = document.createElement("price");
		price.setTextContent("30000");
		
		//book 엘리먼트에 자식 엘리먼트 추가하기
		book.appendChild(title);
		book.appendChild(author);
		book.appendChild(price);
		
		booklist.appendChild(book);
		//==================================
		
		//book 엘리먼트 생성 및 속성값 설정하기
		book = document.createElement("book");
		book.setAttribute("isbn", "B004");
		book.setAttribute("kind", "DB");
		
		//자식 엘리먼트 생성 및 설정
		title = document.createElement("title");
		title.setTextContent("디비구현");
		author = document.createElement("author");
		author.setTextContent("권순신");
		price = document.createElement("price");
		price.setTextContent("35000");
		
		//book 엘리먼트에 자식 엘리먼트 추가하기
		book.appendChild(title);
		book.appendChild(author);
		book.appendChild(price);
		
		booklist.appendChild(book);
		//==================================
		
		//루트 엘리먼트에 booklist 추가하기
		root.appendChild(booklist);
		
		//document에 root 엘리먼트 추가하기
		document.appendChild(root);
		
		//XML문자열로 반환하기
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		DOMSource source = new DOMSource(document);
		StreamResult result = new StreamResult(baos);
		
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		
		//출력 인코딩 설정
		transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		
		//들여쓰기 설정(공백크기 : 2)
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
		
		transformer.transform(source, result);
		
		try(FileOutputStream fos = new FileOutputStream("./src/new_book.xml")){
			fos.write(baos.toByteArray());
		}
		
		System.out.println(new String(baos.toByteArray(), StandardCharsets.UTF_8));
	}
	
	public static void main(String[] args) throws ParserConfigurationException
												, IOException
												, TransformerException {
		new T01_DOMCreationTest().createDoc();
	}
}
