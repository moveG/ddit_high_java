package kr.or.ddit.member.dao;

import java.sql.SQLException;
import java.util.List;

import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.member.vo.PagingVO;

public interface IMemberDao {
	//회원리스트 숫자
	public int countMember(PagingVO pagingVO) throws SQLException;
	
	//회원리스트 출력, 회원 검색
	public List<MemberVO> listMember(PagingVO pagingVO) throws SQLException;
	
	//회원 상세내용
	public MemberVO detailMember(String id) throws SQLException;
	
	//회원 추가
	public int insertMember(MemberVO vo) throws SQLException;
	
	//회원 수정
	public int updateMember(MemberVO vo) throws SQLException;
	
	//회원 삭제
	public int deleteMember(String id) throws SQLException;
	
	//아이디 중복 검사
	public int idCheck(String id) throws SQLException;
}
