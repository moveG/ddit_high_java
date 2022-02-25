package kr.or.ddit.board.dao;

import java.sql.SQLException;
import java.util.List;

import kr.or.ddit.board.vo.BoardVO;

public interface IBoardDao {
	//게시글 숫자
	public int countBoard() throws SQLException;
	
	//게시판 리스트 출력
	public List<BoardVO> listBoard() throws SQLException;
	
	//게시판 상세 내용
	public BoardVO detailBoard(int board_no) throws SQLException;
	
	//게시글 클릭 카운트
	public int updateHit(int board_no) throws SQLException;
	
	//게시글 작성
	public int insertBoard(BoardVO vo) throws SQLException;
	
	//게시글 수정
	public int updateBoard(BoardVO vo) throws SQLException;
	
	//게시글 삭제
	public int deleteBoard(int board_no) throws SQLException;
}
