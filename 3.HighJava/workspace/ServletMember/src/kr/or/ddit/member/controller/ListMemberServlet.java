package kr.or.ddit.member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.cmm.vo.PagingVO;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.member.vo.MemberVO;

@WebServlet("/member/memList.do")
public class ListMemberServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		//1. 요청 페이지 번호
		int pageNo = req.getParameter("pageNo") == null
				? 1
				: Integer.parseInt(req.getParameter("pageNo"));
		
		//2. 서비스 객체 생성
		IMemberService memberService = MemberServiceImpl.getInstance();
		
		//3. 페이징 객체 생성
		int totalCnt = memberService.selectTotalCnt();
		PagingVO pagingVO = new PagingVO();
		pagingVO.setCountPerPage(10);
		pagingVO.setCurrentPageNo(pageNo);
		pagingVO.setPageSize(15);
		pagingVO.setTotalCount(totalCnt);
		
		//4. 회원정보 조회
		List<MemberVO> memList = memberService.getAllMemberList(pagingVO);
		req.setAttribute("memList", memList);
		req.setAttribute("pagingVO", pagingVO);
		
		//5. view페이지로 전달
		req.getRequestDispatcher("/WEB-INF/views/member/memList.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		doGet(req, resp);
	}
}
