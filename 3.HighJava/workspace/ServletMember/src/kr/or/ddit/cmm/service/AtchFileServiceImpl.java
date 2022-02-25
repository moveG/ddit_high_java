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
	public AtchFileVO saveAtchList(Map<String, FileItem[]> fileItemMap) 
			throws Exception {
		AtchFileVO atchFileVO = new AtchFileVO();
		
		//파일 정보 저장
		//기본 첨부파일 테이블에 INSERT실행
		fileDao.atchInsert(atchFileVO);
		
		//파일 세부정보 저장
		for(FileItem[] itemArr : fileItemMap.values()) {	//value만 뽑아서 배열에 저장
			for(FileItem item : itemArr) {	//저장(업로드) 작업 시작
				File uploadDir = new File(FileUploadRequestWrapper.UPLOAD_DIRECTORY);
				if(!uploadDir.exists()) {	//저장폴더 존재 확인
					uploadDir.mkdir();		//없으면 저장폴더 생성
				}
				
				logger.info("item.getName() => " + item.getName());
				
				String originFileName = item.getName();	//원본파일명
				long fileSize = item.getSize();			//파일크기
				String saveFileName = "";				//저장파일명
				String saveFilePath = "";				//저장파일경로
				File storeFile = null;					//저장파일객체
				
				do {	//파일명이 중복되지 않도록 UUID를 이용하여 파일명을 랜덤값으로 만듦
					saveFileName = UUID.randomUUID().toString().replace("-", "");
					saveFilePath = FileUploadRequestWrapper.UPLOAD_DIRECTORY
							+ File.separator + saveFileName;
					storeFile = new File(saveFilePath);
				}while(storeFile.exists());	//저장파일이 중복되지 않을 때까지 반복
				
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
				
				fileDao.atchDetailInsert(atchFileVO);
				
				item.delete();	//임시 업로드 파일 삭제
			}
		}
		return atchFileVO;
	}

	@Override
	public List<AtchFileVO> getAtchList(AtchFileVO fileVO) {
		return fileDao.getAtchList(fileVO);
	}

	@Override
	public AtchFileVO getAtchDetail(AtchFileVO fileVO) {
		return fileDao.getAtchDetail(fileVO);
	}
}
