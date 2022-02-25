package kr.or.ddit.member.service;

import java.sql.SQLException;
import java.util.List;

import kr.or.ddit.member.dao.IMemberDao;
import kr.or.ddit.member.dao.MemberDaoImpl;
import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.member.vo.PagingVO;

public class MemberServiceImpl implements IMemberService{
	private IMemberDao dao;
	
	//싱글톤 패턴 적용
	private static IMemberService service;
	private MemberServiceImpl() {
		dao = MemberDaoImpl.getInstance();
	}
	public static IMemberService getInstance() {
		if(service == null) {
			service = new MemberServiceImpl();
		}
		return service;
	}
	
	//회원리스트 숫자
	@Override
	public int countMember(PagingVO pagingVO) {
		int cnt = 0;
		
		try {
			cnt = dao.countMember(pagingVO);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}
	
	//회원리스트 출력, 회원 검색
	@Override
	public List<MemberVO> listMember(PagingVO pagingVO) {
		List<MemberVO> list = null;
		
		try {
			list = dao.listMember(pagingVO);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	//회원 상세내용
	@Override
	public MemberVO detailMember(String id) {
		MemberVO vo = null;
		
		try {
			vo = dao.detailMember(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vo;
	}

	//회원 추가
	@Override
	public int insertMember(MemberVO vo) {
		int cnt = 0;
		
		try {
			cnt = dao.insertMember(vo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	//회원 수정
	@Override
	public int updateMember(MemberVO vo) {
		int cnt = 0;
		
		try {
			cnt = dao.updateMember(vo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	//회원 삭제
	@Override
	public int deleteMember(String id) {
		int cnt = 0;
		
		try {
			cnt = dao.deleteMember(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}
	
	//아이디 중복 검사
	@Override
	public int idCheck(String id) {
		int cnt = 0;
		
		try {
			cnt = dao.idCheck(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}	
}
