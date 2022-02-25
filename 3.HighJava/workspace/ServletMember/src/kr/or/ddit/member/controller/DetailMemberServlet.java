package kr.or.ddit.member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.cmm.service.AtchFileServiceImpl;
import kr.or.ddit.cmm.service.IAtchFileService;
import kr.or.ddit.cmm.vo.AtchFileVO;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.member.vo.MemberVO;

@WebServlet("/member/memDetail.do")
public class DetailMemberServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		String memId = req.getParameter("memId");
		
		//1. 서비스 객체 생성하기
		IMemberService memberService = MemberServiceImpl.getInstance();
				
		//2. 회원정보 조회
		MemberVO mv = memberService.getMember(memId);
		
		//3. 첨부파일 정보 조회
		if(mv.getAtchFileId() > 0) {	//첨부파일 존재 확인
			//첨부파일 정보조회
			AtchFileVO fileVO = new AtchFileVO();
			fileVO.setAtchFileId(mv.getAtchFileId());
			
			IAtchFileService fileService = AtchFileServiceImpl.getInstance();
			List<AtchFileVO> atchList = fileService.getAtchList(fileVO);
			
			req.setAttribute("atchList", atchList);
		}
		
		//4. request 객체에 회원정보 저장
		req.setAttribute("mv", mv);
		
		//5. View 화면으로 이동
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/member/memDetail.jsp");
		
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		doGet(req, resp);
	}
}
