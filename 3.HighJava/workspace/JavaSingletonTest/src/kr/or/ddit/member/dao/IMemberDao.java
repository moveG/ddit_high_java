package kr.or.ddit.member.dao;

import java.util.List;

import kr.or.ddit.member.vo.MemberVO;

/**
 * 실제 DB와 연결해서 SQL문을 수행하여 결과를 받아 Service에 전달하는 Dao의 interface
 * @author PC-15
 */
public interface IMemberDao {
	/**
	 * MemberVO에 담겨진 자료를 DB에 insert하는 메서드
	 * @param mv DB에 insert할 자료가 저장된 MemberVO객체
	 * @return DB작업이 성공하면 1이상의 값이 반환되고 실패하면 0이 반환된다.
	 */
	public int insertMember(MemberVO mv);
	
	/**
	 * 주어진 회원ID가 존재하는지 여부를 알아내기 위한 메서드
	 * @param memId 회원ID
	 * @return 해당 회원ID가 있으면 true, 없으면 false
	 */
	public boolean chkMember(String memId);
	
	/**
	 * DB mymember테이블의 전체 레코드를 가져와서 List에 담아 반환하는 메서드
	 * @return MemberVO객체를 담고 있는 List객체
	 */
	public List<MemberVO> getAllMemberList();
	
	/**
	 * 하나의 MemberVO객체 자료를 이용하여 DB에 update하는 메서드
	 * @param mv update할 회원정보가 담긴 MemberVO 객체
	 * @return 작업성공 : 1, 작업실패 : 0
	 */
	public int updateMember(MemberVO mv);
	
	/**
	 * 회원ID를 입력받아 해당 회원정보를 삭제하는 메서드
	 * @param memId
	 * @return 작업성공 : 1, 작업실패 : 0 
	 */
	public int deleteMember(String memId);	
	
	/**
	 * MemberVO에 담긴 자료를 이용하여 회원을 검색하는 메서드
	 * @param mv 검색할 자료가 담긴 MemberVO객체
	 * @return 검색된 결과를 담은 List
	 */
	public List<MemberVO> getSearchMember(MemberVO mv);
}
