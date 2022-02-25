package kr.or.ddit.board.dao;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.util.SqlMapClientFactory;

public class BoardDaoImpl implements IBoardDao {
	private static SqlMapClient smc;
	
	//싱글톤 패턴 적용
	private static IBoardDao boardDao;
	private BoardDaoImpl() {
		smc = SqlMapClientFactory.getInstance();
	}
	public static IBoardDao getInstance() {
		if(boardDao == null) {
			boardDao = new BoardDaoImpl();
		}
		return boardDao;
	}
	
	@Override
	public int writeBoard(BoardVO bv) {
		int cnt = 0;
		
		try {
			cnt = smc.update("board.writeBoard", bv);
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int updateBoard(BoardVO bv) {
		int cnt = 0;
		
		try {
			cnt = smc.update("board.updateBoard", bv);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int deleteBoard(int boardNo) {
		int cnt = 0;
		
		try {
			cnt = smc.delete("board.deleteBoard", boardNo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public BoardVO readBoard(int boardNo) {
		BoardVO bvo = null;
		try {
			bvo = (BoardVO) smc.queryForObject("board.readBoard", boardNo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bvo;
	}

	@Override
	public List<BoardVO> searchBoard(BoardVO bv) {
		List<BoardVO> boardList = new ArrayList<BoardVO>();
		
		try {
//			sql = " ORDER BY BOARD_NO DESC ";
			boardList = smc.queryForList("board.searchBoard", bv);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return boardList;
	}

	@Override
	public List<BoardVO> listBoard() {
		List<BoardVO> boardList = new ArrayList<BoardVO>();
		
		try {
			boardList = smc.queryForList("board.listBoard");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return boardList;
	}

	@Override
	public boolean chkNumber(int boardNo) {
		boolean chk = false;
		int cnt = 0;
		
		try {
			cnt = (int) smc.queryForObject("board.chkNumber", boardNo);
			
			if(cnt > 0) {
				chk = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return chk;
	}	
}
