package kr.or.ddit.cmm.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.fileupload.FileItem;

import kr.or.ddit.cmm.vo.AtchFileVO;

public interface IAtchFileService {
	/**
	 * 첨부파일 목록 저장
	 * @param fileItemMap
	 * @return
	 */
	public AtchFileVO saveAtchList(Map<String, FileItem[]> fileItemMap) throws Exception;
	
	/**
	 * 첨부파일 목록 조회
	 * @param fileVO
	 * @return
	 */
	public List<AtchFileVO> getAtchList(AtchFileVO fileVO);
	
	/**
	 * 첨부파일 세부정보 조회
	 * @param fileVO
	 * @return AtchFileVO 파일 세부정보 담은 객체
	 */
	public AtchFileVO getAtchDetail(AtchFileVO fileVO);
}
