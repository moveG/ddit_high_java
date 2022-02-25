package kr.or.ddit.member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.member.vo.PagingVO;

public class MemberList extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		int pageNo = request.getParameter("pageNo") == null
				? 1
				: Integer.parseInt(request.getParameter("pageNo"));
		String searchKey = request.getParameter("searchKey") == null
				? ""
				: request.getParameter("searchKey");
		
		String searchWord = request.getParameter("searchWord") == null
				? ""
				: request.getParameter("searchWord");
		
		IMemberService service = MemberServiceImpl.getInstance();
		
		PagingVO pagingVO = new PagingVO();
		pagingVO.setSearchKey(searchKey);
		pagingVO.setSearchWord(searchWord);
		
		int totalCnt = service.countMember(pagingVO);
		//한 페이지당 게시물 숫자
		pagingVO.setCountPerPage(10);
		pagingVO.setCurrentPageNo(pageNo);
		//페이징 버튼(이전, 이후) 등장
		pagingVO.setPageSize(5);
		pagingVO.setTotalCount(totalCnt);
		
		List<MemberVO> list = service.listMember(pagingVO);
		int listSize = list.size();
		
		request.setAttribute("list", list);
		request.setAttribute("listSize", listSize);
		request.setAttribute("pagingVO", pagingVO);
		request.getRequestDispatcher("member/memberList.jsp").forward(request, response);
	}
}
