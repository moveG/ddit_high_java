package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.board.vo.BoardVO;

public interface IBoardService {
	//게시글 숫자
	public int countBoard();
	
	//게시판 리스트 출력
	public List<BoardVO> listBoard();
	
	//게시판 상세 내용
	public BoardVO detailBoard(int board_no);
	
	//게시글 클릭 카운트
	public int updateHit(int board_no);
	
	//게시글 작성
	public int insertBoard(BoardVO vo);
	
	//게시글 수정
	public int updateBoard(BoardVO vo);
	
	//게시글 삭제
	public int deleteBoard(int board_no);
}
