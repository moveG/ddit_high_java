package kr.or.ddit.board.dao;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.board.util.SqlMapClientFactory;
import kr.or.ddit.board.vo.BoardVO;

public class BoardDaoImpl implements IBoardDao{
	private SqlMapClient smc;
	
	//싱글톤 패턴 적용
	private static IBoardDao dao;
	private BoardDaoImpl() {
		smc = SqlMapClientFactory.getInstance();
	}
	public static IBoardDao getInstance() {
		if(dao == null) {
			dao = new BoardDaoImpl();
		}
		return dao;
	}
	
	//게시글 숫자
	@Override
	public int countBoard() throws SQLException {
		return (Integer) smc.queryForObject("boardStudy.countBoard");
	}
	
	//게시판 리스트 출력
	@Override
	public List<BoardVO> listBoard() throws SQLException {
		return smc.queryForList("boardStudy.listBoard");
	}

	//게시판 상세 내용
	@Override
	public BoardVO detailBoard(int board_no) throws SQLException {
		return (BoardVO) smc.queryForObject("boardStudy.detailBoard", board_no);
	}

	//게시글 클릭 카운트
	@Override
	public int updateHit(int board_no) throws SQLException {
		return smc.update("boardStudy.updateHit", board_no);
	}

	//게시글 작성
	@Override
	public int insertBoard(BoardVO vo) throws SQLException {
		return smc.update("boardStudy.insertBoard", vo);
	}

	//게시글 수정
	@Override
	public int updateBoard(BoardVO vo) throws SQLException {
		return smc.update("boardStudy.updateBoard", vo);
	}

	//게시글 삭제
	@Override
	public int deleteBoard(int board_no) throws SQLException {
		return smc.delete("boardStudy.deleteBoard", board_no);
	}
}
