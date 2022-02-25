package kr.or.ddit.basic;

import java.io.IOException;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 서블릿 필터
 * 
 * 1. 사용목적
 *  - 클라이언트의 요청을 수행하기 전에 가로채서 필요한 작업을 수행할 수 있다.
 *  - 클라이언트에 응답정보를 제공하기 전에 응답정보에 필요한 작업을 수행할 수 있다.
 * 
 * 2. 사용예
 *  - 인증 필터
 *  - 데이터 압축 필터
 *  - 인코딩 필터
 *  - 로깅 및 감사처리 필터
 *  - 이미지 변환 필터
 */
public class T08_ServletFilter2 implements Filter{
	@Override
	public void init(FilterConfig config) throws ServletException {
		System.out.println("T08_ServletFilter2 => init() 호출됨.");
		
		//초기화 파라미터 정보 가져오기
		String initParam = config.getInitParameter("init-param");
		
		System.out.println("init-param2 : " + initParam);
	}

	@Override
	public void destroy() {
		//필터 객체가 웹 컨테이너에 의해 서비스로부터 제거되기 이전에 호출됨.
		System.out.println("T08_ServletFilter2 => destroy() 호출됨.");
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain fc)
			throws IOException, ServletException {
		System.out.println("T08_ServletFilter2 => doFilter() 시작...");
		
		//서블릿 수행시간 계산(나노초 단위(10억분의 1초))
		//10억 나노초 = 1초 / 0이 9개
		
		long startTime = System.nanoTime();
		
		//필터체인을 실행한다.(req, resp 객체 전달)
		//필터체인 : 필터를 연결해 묶어놓은 것
		//필터체인(doFilter)이 종료되면 다시 돌아와서 다음 순서(여기서는 System.out.println)를 실행함
		fc.doFilter(req, resp);
		
		System.out.println("수행시간(ns) : " + (System.nanoTime() - startTime) + "나노초");
		
		System.out.println("T08_ServletFilter2 => doFilter() 종료...");
	}
}
