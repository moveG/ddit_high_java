package kr.or.ddit.basic;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class T10_HttpSessionListenerTest extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		//HttpSession객체 생성 및 소멸 관련 테스트
		HttpSession session = req.getSession();
		
		session.setAttribute("ATTR1", "속성1");	//속성값 추가
		session.setAttribute("ATTR1", "속성11");	//속성값 변경
		session.setAttribute("ATTR2", "속성2");	//속성값 추가
		
		session.removeAttribute("ATTR1");		//속성값 제거
		
		//==================================================================
		//==============MySessionBindingListener 클래스 관련 내용==============//
		//==================================================================
		//HTTP세션 영역 내에 HttpSessionBindingListener를 구현한 객체가 바인딩 되면 호출됨.
		//특정 객체가 세션에 등록되거나 삭제되는 시점을 알고 싶을 때 사용됨.
		MySessionBindingListener bindingListener
							= new MySessionBindingListener();
		session.setAttribute("obj", bindingListener);
		session.removeAttribute("obj");
		
		//==================================================================
		session.invalidate();	//세션객체 삭제
		//세션객체를 삭제하면 세션에 추가했던 속성값들도 하나하나 삭제된다.
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		doGet(req, resp);
	}
}
