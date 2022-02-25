package kr.or.ddit.cmm.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.cmm.vo.AtchFileVO;
import kr.or.ddit.util.SqlMapClientFactory;

public class AtchFileDaoImpl implements IAtchFileDao {
	/*
	 * 싱글톤 패턴 적용
	 */
	private static IAtchFileDao dao;
	private SqlMapClient smc;
	
	private AtchFileDaoImpl() {
		smc = SqlMapClientFactory.getInstance();
	}
	
	public static IAtchFileDao getInstance() {
		if(dao == null) {
			dao = new AtchFileDaoImpl();
		}
		return dao;
	}
	
	@Override
	public int atchInsert(AtchFileVO atchFileVO) {
		int cnt = 0;
		
		try {
			Object obj = smc.insert("atchFile.atchInsert", atchFileVO);
			
			if(obj == null) {
				cnt = 1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int atchDetailInsert(AtchFileVO atchFileVO) {
		int cnt = 0;
		
		try {
			Object obj = smc.insert("atchFile.atchDetailInsert", atchFileVO);
			
			if(obj == null) {
				cnt = 1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public List<AtchFileVO> getAtchList(AtchFileVO atchFileVO) {
		List<AtchFileVO> list = new ArrayList<>();
		
		try {
			list = smc.queryForList("atchFile.atchList", atchFileVO);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public AtchFileVO getAtchDetail(AtchFileVO atchFileVO) {
		AtchFileVO fileVO = null;
		
		try {
			fileVO = (AtchFileVO) smc.queryForObject("atchFile.atchDetail", atchFileVO);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return fileVO;
	}
}
