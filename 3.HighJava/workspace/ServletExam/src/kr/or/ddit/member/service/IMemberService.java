package kr.or.ddit.member.service;

import java.util.List;

import kr.or.ddit.cmm.vo.PagingVO;
import kr.or.ddit.member.vo.MemberVO;

/**
 * 서비스 객체는 Dao에 설정된 메서드를 원하는 작업에 맞게 호출하여
 * 결과를 받아오고, 받아온 베이터를 Controller에게 보내주는 역할을 수행한다.
 * @author PC-15
 *
 */
public interface IMemberService {
	/**
	 * MemberVO에 담겨진 자료를 DB에 insert하는 메서드
	 * @param mv DB에 insert할 자료가 저장된 MemberVO객체
	 * @return DB작업이 성공하면 1이상의 값이 반환되고 실패하면 0이 반환된다.
	 */
	public int registerMember(MemberVO mv);
	
	/**
	 * 주어진 회원ID가 존재하는지 여부를 알아내기 위한 메서드
	 * @param memId 회원ID
	 * @return 해당 회원ID가 있으면 true, 없으면 false
	 */
	public boolean chkMember(String memId);
	
	/**
	 * 주어진 회원ID에 해당하는 회원 정보를 알아내기 위한 메서드
	 * @param memId 회원ID
	 * @return 해당 회원 정보
	 */
	public MemberVO getMember(String memId);
	
	/**
	 * DB mymember테이블의 전체 레코드를 가져와서 List에 담아 반환하는 메서드
	 * @return MemberVO객체를 담고 있는 List객체
	 */
	public List<MemberVO> getAllMemberList(PagingVO pagingVO);
	
	/**
	 * 전체 회원수를 조회하기 위한 메서드
	 * @return
	 */
	public int selectTotalCnt();
	
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
