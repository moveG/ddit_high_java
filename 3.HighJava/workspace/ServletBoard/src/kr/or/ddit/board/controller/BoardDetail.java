package kr.or.ddit.board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.board.vo.BoardVO;

/**
 * Servlet implementation class BoardDetail
 */
@WebServlet("/BoardDetail.do")
public class BoardDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardDetail() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		int board_no = Integer.parseInt(request.getParameter("board_no"));
		
		IBoardService service = BoardServiceImpl.getInstance();
		
		//조회수
		int cnt = service.updateHit(board_no);
		//상세페이지
		BoardVO vo = service.detailBoard(board_no);
		
		request.setAttribute("vo", vo);
		
		request.getRequestDispatcher("board/detail.jsp").forward(request, response);
	}
}
