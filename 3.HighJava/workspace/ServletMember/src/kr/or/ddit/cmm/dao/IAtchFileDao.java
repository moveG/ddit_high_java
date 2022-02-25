package kr.or.ddit.cmm.dao;

import java.util.List;

import kr.or.ddit.cmm.vo.AtchFileVO;

public interface IAtchFileDao {
	/**
	 * 첨부파일 정보 저장
	 * @param atchFileVO
	 * @return
	 */
	public int atchInsert(AtchFileVO atchFileVO);
	
	/**
	 * 첨부파일 세부정보 저장
	 * @param atchFileVO
	 * @return
	 */
	public int atchDetailInsert(AtchFileVO atchFileVO);
	
	/**
	 * 첨부파일 목록 조회
	 * @param atchFileVO
	 * @return 첨부파일 목록
	 */
	public List<AtchFileVO> getAtchList(AtchFileVO atchFileVO);
	
	/**
	 * 첨부파일 세부정보 조회
	 * @param atchFileVO
	 * @return 첨부파일 세부정보
	 */
	public AtchFileVO getAtchDetail(AtchFileVO atchFileVO);
}
