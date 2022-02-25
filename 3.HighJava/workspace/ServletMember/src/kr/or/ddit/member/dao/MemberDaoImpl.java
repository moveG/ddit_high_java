package kr.or.ddit.member.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.cmm.vo.PagingVO;
import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.util.SqlMapClientFactory;

public class MemberDaoImpl implements IMemberDao {
	private static SqlMapClient smc;
	
	//싱글톤 패턴 적용
	private static IMemberDao memDao;

	private MemberDaoImpl() {
		smc = SqlMapClientFactory.getInstance();
	}
	
	public static IMemberDao getInstance() {
		if(memDao == null) {
			memDao = new MemberDaoImpl();
		}
		return memDao;
	}

	@Override
	public int insertMember(MemberVO mv) {
		int cnt = 0;
		
		try {
			cnt = smc.update("member.insertMember", mv);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public boolean chkMember(String memId) {
		boolean chk = false;
		int cnt = 0;
		
		try {
			cnt = (int) smc.queryForObject("member.chkMember", memId);
			
			if(cnt > 0) {
				chk = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return chk;
	}

	@Override
	public List<MemberVO> getAllMemberList(PagingVO pagingVO) {
		List<MemberVO> memList = new ArrayList<MemberVO>();
		
		try {
			memList = smc.queryForList("member.selectAllMember", pagingVO);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return memList;
	}

	@Override
	public int updateMember(MemberVO mv) {
		int cnt = 0;
		
		try {
			cnt = smc.update("member.updateMember", mv);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int deleteMember(String memId) {
		int cnt = 0;
		
		try {
			cnt = smc.delete("member.deleteMember", memId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public List<MemberVO> getSearchMember(MemberVO mv) {
		//다이내믹 쿼리(동적 쿼리)
		List<MemberVO> memList = new ArrayList<>();
		
		try {
			memList = smc.queryForList("member.searchMember", mv);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return memList;
	}

	@Override
	public MemberVO getMember(String memId) {
		MemberVO mv = null;
		
		try {
			mv = (MemberVO) smc.queryForObject("member.selectMember", memId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return mv;
	}

	@Override
	public int selectTotalCnt() {
		int cnt = 0;
		
		try {
			cnt = (int) smc.queryForObject("member.selectTotalCnt");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}
}
