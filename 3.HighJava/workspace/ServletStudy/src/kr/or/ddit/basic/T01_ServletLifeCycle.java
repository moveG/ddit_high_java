package kr.or.ddit.basic;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 서블릿의 라이프사이클을 확인하기 위한 예제
 */
public class T01_ServletLifeCycle extends HttpServlet{
	//서블릿 사용 이유 : HTML을 동적(다이나믹)으로 만들기 위하여
	
	@Override
	public void init() throws ServletException {
		//초기화 코드 작성...
		System.out.println("init() 호출됨...");
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		//실제적인 작업수행이 시작되는 시점...(자바의 메인 메서드 역할)
		super.service(req, resp);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		//메서드 방식이 GET인 경우 호출됨...
		System.out.println("doGet() 호출됨...");
		
		throw new ServletException("서블릿 예외 발생!!!");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		//메서드 방식이 POST인 경우 호출됨...
		System.out.println("doPost() 호출됨...");
	}
	
	@Override
	public void destroy() {
		//객체 소멸시(컨테이너로부터 서블릿 객체 제거시) 필요한 코드 작성...
		System.out.println("destroy() 호출됨...");
	}	
}
