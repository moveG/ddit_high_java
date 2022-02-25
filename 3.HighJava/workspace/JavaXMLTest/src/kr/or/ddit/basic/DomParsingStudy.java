package kr.or.ddit.basic;

import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class DomParsingStudy {
	
	public static void main(String[] args) throws Exception {
		new DomParsingStudy().parse();
	}
	
	public void parse() throws Exception {
		System.out.println("식품의약품안전처 건강기능식품");
		System.out.println("먹는 콜라겐 제품 등록 현황 서비스");
		
		//DocumentBuilder 생성하기
		DocumentBuilder builder = 
			DocumentBuilderFactory.newInstance().newDocumentBuilder();
		
		String apiKey = "YG%2F0XYjgHK6QS3%2B6sQEhP3AdlrgZDQFS%2FHnwUZvKpK1ykTCAunE5iV4r3FgDB%2FdJX33QEuHWVK99viBx9GmH8g%3D%3D";
		String endIdx = "10";
		
		URL url = new URL("http://apis.data.go.kr/1471000/TheEatingCollagenProductService/getEatingCollagenProductList?ServiceKey=" + apiKey
				+ "&pageNo=1&numOfRows=" + endIdx);
		System.out.println(url);
		//DOM parser로부터 입력받은 파일을 파싱하도록 요청
		Document document = builder.parse(url.toString());
		
		//DOM Document 객체로부터 루트 엘리먼트 및 자식 객체 가져오기
		Element root = document.getDocumentElement();
		
		System.out.println("루트 엘리먼트 태그명 : " + root.getTagName());
		
		//전체 등록 개수 가져오기
		String totalCount = root.getElementsByTagName("totalCount").item(0).getTextContent();
		System.out.println(totalCount);
//		endIdx = totalCount;
//		너무 많으면 출력이 안됨
		endIdx = "100";
		
		url = new URL("http://apis.data.go.kr/1471000/TheEatingCollagenProductService/getEatingCollagenProductList?ServiceKey=" + apiKey
				+ "&pageNo=1&numOfRows=" + endIdx);
		
		//DOM parser로부터 입력받은 파일을 파싱하도록 요청
		document = builder.parse(url.toString());
		
		//DOM Document 객체로부터 루트 엘리먼트 및 자식 객체 가져오기
		root = document.getDocumentElement();
		
		//하위 엘리먼트 접근
		NodeList itemNodeList = root.getElementsByTagName("item");
		System.out.printf("%-10s %15s %20s %20s %20s %20s %20s %20s %20s %15s\n",
				"인허가관할기관", "인허가번호", "업소명", "업종명", "인허가일자",
				"품목보고번호", "품목명", "품목대분류", "품목유형명", "보고일자");
		System.out.println("====================================================="
				+ "=========================================================================================");
		
		for(int i = 0; i < itemNodeList.getLength(); i++) {
			Element element = (Element) itemNodeList.item(i);
			
			String instt = element.getElementsByTagName("INSTT_NM")
					.item(0).getTextContent();
			String lcns = element.getElementsByTagName("LCNS_NO")
					.item(0).getTextContent();
			String bss = element.getElementsByTagName("BSS_NM")
					.item(0).getTextContent();
			String induty = element.getElementsByTagName("INDUTY_CD_NM")
					.item(0).getTextContent();
			String prms = element.getElementsByTagName("PRMS_DT")
					.item(0).getTextContent();
			String prdlstNo = element.getElementsByTagName("PRDLST_REPORT_NO")
					.item(0).getTextContent();
			String prdlstNm = element.getElementsByTagName("PRDLST_NM")
					.item(0).getTextContent();
			String htrk = element.getElementsByTagName("HTRK_PRDLST_CD_NM")
					.item(0).getTextContent();
			String cd = element.getElementsByTagName("PRDLST_CD_NM")
					.item(0).getTextContent();
			String dt = element.getElementsByTagName("PRD_PRMS_DT")
					.item(0).getTextContent();
			String str = String.format("%-10s %15s %20s %20s %20s %20s %20s %20s %20s %15s\n", 
					instt, lcns, bss, induty, prms, prdlstNo, prdlstNm, htrk, cd, dt);
			System.out.println(str);
			System.out.println("--------------------------------------------------------------"
					+ "-------------------------------------------------------------------------------");
		}
	}
}
