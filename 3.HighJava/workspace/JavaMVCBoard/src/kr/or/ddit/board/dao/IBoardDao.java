package kr.or.ddit.board.dao;

import java.util.List;

import kr.or.ddit.board.vo.BoardVO;

public interface IBoardDao {
	/**
	 * 게시글번호 확인
	 * @param boardNo
	 * @return
	 */
	public boolean chkNumber(int boardNo);
	
	/**
	 * 게시물 작성
	 * @param bv
	 * @return
	 */
	public int writeBoard(BoardVO bv);
	
	/**
	 * 게시물 수정
	 * @param bv
	 * @return
	 */
	public int updateBoard(BoardVO bv);
	
	/**
	 * 게시물 삭제
	 * @param boardNo
	 * @return
	 */
	public int deleteBoard(int boardNo);
	
	/**
	 * 게시물 읽기
	 * @return
	 */
	public List<BoardVO> readBoard(int boardNo);
	
	/**
	 * 게시물 검색
	 * @return
	 */
	public List<BoardVO> searchBoard(BoardVO bv);
	
	/**
	 * 게시판 목록
	 * @return
	 */
	public List<BoardVO> listBoard();
}
