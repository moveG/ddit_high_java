package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.board.dao.BoardDaoImpl;
import kr.or.ddit.board.dao.IBoardDao;
import kr.or.ddit.board.vo.BoardVO;

public class BoardServiceImpl implements IBoardService{
	//싱글톤 패턴 적용
	private static IBoardService boardService;
	private IBoardDao boardDao;
	private BoardServiceImpl() {
		boardDao = BoardDaoImpl.getInstance();
	}
	public static IBoardService getInstance() {
		if(boardService == null) {
			boardService = new BoardServiceImpl();
		}
		return boardService;
	}

	@Override
	public int writeBoard(BoardVO bv) {
		return boardDao.writeBoard(bv);
	}

	@Override
	public int updateBoard(BoardVO bv) {
		return boardDao.updateBoard(bv);
	}

	@Override
	public int deleteBoard(int boardNo) {
		return boardDao.deleteBoard(boardNo);
	}

	@Override
	public BoardVO readBoard(int boardNo) {
		return boardDao.readBoard(boardNo);
	}

	@Override
	public List<BoardVO> searchBoard(BoardVO bv) {
		return boardDao.searchBoard(bv);
	}

	@Override
	public List<BoardVO> listBoard() {
		return boardDao.listBoard();
	}

	@Override
	public boolean chkNumber(int boardNo) {
		return boardDao.chkNumber(boardNo);
	}
}
