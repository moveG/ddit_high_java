package kr.or.ddit.cmm.service;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.fileupload.FileItem;
import org.apache.log4j.Logger;

import kr.or.ddit.cmm.dao.AtchFileDaoImpl;
import kr.or.ddit.cmm.dao.IAtchFileDao;
import kr.or.ddit.cmm.vo.AtchFileVO;
import kr.or.ddit.util.FileUploadRequestWrapper;

public class AtchFileServiceImpl implements IAtchFileService {
	private static Logger logger = Logger.getLogger(AtchFileServiceImpl.class);
	
	/*
	 * 싱글톤 패턴 적용
	 */
	private static IAtchFileService fileService;
	private IAtchFileDao fileDao;
	
	private AtchFileServiceImpl() {
		fileDao = AtchFileDaoImpl.getInstance();
	}
	
	public static IAtchFileService getInstance() {
		if(fileService == null) {
			fileService = new AtchFileServiceImpl();
		}
		return fileService;
	}
	
	@Override
	public AtchFileVO saveAtchFileList(Map<String, FileItem[]> fileItemMap) 
			throws Exception {
		AtchFileVO atchFileVO = new AtchFileVO();
		
		//파일 정보 저장
		fileDao.insertAtchFile(atchFileVO);	//기본 첨부파일 테이블에  insert실행
		
		//파일 세부정보 저장
		for(FileItem[] itemArr : fileItemMap.values()) {	//value만 뽑아서 배열에 저장
			for(FileItem item : itemArr) {	//저장(업로드) 작업 시작
				File uploadDir = new File(FileUploadRequestWrapper.UPLOAD_DIRECTORY);	//저장폴더 설정
				if(!uploadDir.exists()) {	//저장폴더 존재 확인
					uploadDir.mkdir();		//없으면 저장폴더 생성
				}
				
				logger.info("item.getName() => " + item.getName());
				
				String originFileName = item.getName();	//원본파일명
				long fileSize = item.getSize();			//파일크기
				String saveFileName = "";				//저장파일명
				File storeFile = null;					//저장파일객체
				String saveFilePath = "";				//저장파일경로
				
				do {	//파일명이 중복되지 않도록 UUID를 통해 파일명을 랜덤값으로 만들어줌
					saveFileName = UUID.randomUUID().toString().replace("-", "");
					saveFilePath = FileUploadRequestWrapper.UPLOAD_DIRECTORY 
							+ File.separator + saveFileName;
					storeFile = new File(saveFilePath);
				} while(storeFile.exists());	//저장파일이 중복되지 않을 때까지
				
				//확장명 추출
				String fileExtension = 
						originFileName.lastIndexOf(".") < 0 
						? "" 
						: originFileName.substring(originFileName.lastIndexOf(".") + 1);
				
				//업로드 파일 저장
				item.write(storeFile);
				
				//DB에 파일 세부정보 저장
				atchFileVO.setStreFileNm(saveFileName);
				atchFileVO.setFileSize(fileSize);
				atchFileVO.setOrignlFileNm(originFileName);
				atchFileVO.setFileExtsn(fileExtension);
				atchFileVO.setFileStreCours(saveFilePath);
				
				fileDao.insertAtchFileDetail(atchFileVO);
				
				item.delete();	//임시 업로드 파일 삭제
			}
		}
		return atchFileVO;
	}

	@Override
	public List<AtchFileVO> getAtchFileList(AtchFileVO fileVO) {
		return fileDao.getAtchFileList(fileVO);
	}

	@Override
	public AtchFileVO getAtchFileDetail(AtchFileVO fileVO) {
		return fileDao.getAtchFileDetail(fileVO);
	}
}
