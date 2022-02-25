package kr.or.ddit.board.service;

import java.sql.SQLException;
import java.util.List;

import kr.or.ddit.board.dao.BoardDaoImpl;
import kr.or.ddit.board.dao.IBoardDao;
import kr.or.ddit.board.vo.BoardVO;

public class BoardServiceImpl implements IBoardService{
	private IBoardDao dao;

	//싱글톤 패턴 적용
	private static IBoardService service;
	private BoardServiceImpl() {
		dao = BoardDaoImpl.getInstance();
	}
	public static IBoardService getInstance() {
		if(service == null) {
			service = new BoardServiceImpl();
		}
		return service;
	}
	
	//게시글 숫자
	@Override
	public int countBoard() {
		int cnt = 0;
		
		try {
			cnt = dao.countBoard();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	//게시판 리스트 출력
	@Override
	public List<BoardVO> listBoard() {
		List<BoardVO> list = null;
		
		try {
			list = dao.listBoard();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	//게시판 상세 내용
	@Override
	public BoardVO detailBoard(int board_no) {
		BoardVO vo = null;
		
		try {
			vo = dao.detailBoard(board_no);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vo;
	}

	//게시글 클릭 카운트
	@Override
	public int updateHit(int board_no) {
		int cnt = 0;
		
		try {
			cnt = dao.updateHit(board_no);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	//게시글 작성
	@Override
	public int insertBoard(BoardVO vo) {
		int cnt = 0;
		
		try {
			cnt = dao.insertBoard(vo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	//게시글 수정
	@Override
	public int updateBoard(BoardVO vo) {
		int cnt = 0;
		
		try {
			cnt = dao.updateBoard(vo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	//게시글 삭제
	@Override
	public int deleteBoard(int board_no) {
		int cnt = 0;
		
		try {
			cnt = dao.deleteBoard(board_no);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}
}
