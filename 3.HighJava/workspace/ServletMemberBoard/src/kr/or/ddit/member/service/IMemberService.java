package kr.or.ddit.member.service;

import java.util.List;

import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.member.vo.PagingVO;

public interface IMemberService {
	//회원리스트 숫자
	public int countMember(PagingVO pagingVO);
	
	//회원리스트 출력
	public List<MemberVO> listMember(PagingVO pagingVO);
	
	//회원 상세내용
	public MemberVO detailMember(String id);
	
	//회원 추가
	public int insertMember(MemberVO vo);
	
	//회원 수정
	public int updateMember(MemberVO vo);
	
	//회원 삭제
	public int deleteMember(String id);
	
	//아이디 중복 검사
	public int idCheck(String id);
}
