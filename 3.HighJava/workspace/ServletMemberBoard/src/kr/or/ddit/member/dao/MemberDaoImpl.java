package kr.or.ddit.member.dao;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.member.util.SqlMapClientFactory;
import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.member.vo.PagingVO;

public class MemberDaoImpl implements IMemberDao {
	private SqlMapClient smc;
	
	//싱글톤 패턴 적용
	private static IMemberDao dao;
	private MemberDaoImpl() {
		smc = SqlMapClientFactory.getInstance();
	}
	public static IMemberDao getInstance() {
		if(dao == null) {
			dao = new MemberDaoImpl();
		}
		return dao;
	}
	
	//회원리스트 숫자
	@Override
	public int countMember(PagingVO pagingVO) throws SQLException {
		return (Integer) smc.queryForObject("member.countMember", pagingVO);
	}
	
	//회원리스트 출력, 회원 검색
	@Override
	public List<MemberVO> listMember(PagingVO pagingVO) throws SQLException {
		return smc.queryForList("member.listMember", pagingVO);
	}

	//회원 상세내용
	@Override
	public MemberVO detailMember(String id) throws SQLException {
		return (MemberVO) smc.queryForObject("member.detailMember", id);
	}
	
	//회원 추가
	@Override
	public int insertMember(MemberVO vo) throws SQLException {
		return smc.update("member.insertMember", vo);
	}
	
	//회원 수정
	@Override
	public int updateMember(MemberVO vo) throws SQLException {
		return smc.update("member.updateMember", vo);
	}

	//회원 삭제
	@Override
	public int deleteMember(String id) throws SQLException {
		return smc.delete("member.deleteMember", id);
	}
	
	//아이디 중복 검사
	@Override
	public int idCheck(String id) throws SQLException {
		return (Integer) smc.queryForObject("member.idCheck", id);
	}
}
