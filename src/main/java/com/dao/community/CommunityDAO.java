package com.dao.community;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.common.Page;
import com.dto.community.CommunityDTO;
import com.dto.community.CommunityDetailsDTO;

public class CommunityDAO {

	
	public void insert(SqlSession session, CommunityDTO community) {
		session.insert("CommunityMapper.insert", community);
	}

	public CommunityDTO getCommunityByNo(SqlSession session, Long comNo) {
		return session.selectOne("CommunityMapper.getCommunityByNo", comNo);
	}

	public List<CommunityDTO> getCommunityByMemNo(SqlSession session, Long memNo) {	
		return session.selectOne("CommunityMapper.getCommunityByMemNo", memNo);
	}

	public List<CommunityDTO> getCommunityList(SqlSession session ) {
		return session.selectList("CommunityMapper.getCommunityList");
	}

	public void update(SqlSession session, CommunityDTO communtiy) {
		session.update("CommunityMapper.update", communtiy);
	}

	public void delete(SqlSession session, Long comNo) {
		session.delete("CommunityMapper.delete", comNo);
	}

	public void increaseViews(SqlSession session, Long comNo) {
		session.update("CommunityMapper.increaseViews", comNo);		
	}
	
	public CommunityDetailsDTO getCommunityDetailsByNo(SqlSession session, Long comNo) {
		return session.selectOne("CommunityMapper.getCommunityDetailsByNo", comNo);
	}

	public List<CommunityDetailsDTO> getCommunityDetailsList(SqlSession session, Page page) {
		return session.selectList("CommunityMapper.getCommunityDetailsList", page);
	}
	
	public Integer count(SqlSession session) {
		return session.selectOne("CommunityMapper.count");
	}

}
