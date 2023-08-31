package com.dao.community;

import java.util.Collections;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.config.MySqlSessionFactory;
import com.dto.community.CommunityDTO;
import com.dto.community.CommunityDetailsDTO;

public class CommunityDAO {

	
	public void insert(CommunityDTO community) {
		SqlSession session = getSession();
		try {
			session.insert("CommunityMapper.insert", community);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	private SqlSession getSession() {
		return MySqlSessionFactory.getSession();
	}
	
	public CommunityDTO getCommunityByNo(Long comNo) {
		SqlSession session = getSession();
		try {
			return session.selectOne("CommunityMapper.getCommunityByNo", comNo);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}

	public List<CommunityDTO> getCommunityByMemNo(Long memNo) {
		SqlSession session = getSession();
		try {
			return session.selectOne("CommunityMapper.getCommunityByMemNo", memNo);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}

	public List<CommunityDTO> getCommunityList() {
		SqlSession session = getSession();
		try {
			return session.selectList("CommunityMapper.getCommunityList");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return Collections.emptyList();
	}

	public void update(CommunityDTO communtiy) {
		SqlSession session = getSession();
		try {
			session.update("CommunityMapper.update", communtiy);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public void delete(Long comNo) {
		SqlSession session = getSession();
		try {
			session.delete("CommunityMapper.delete", comNo);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public void increaseViews(Long comNo) {
		SqlSession session = getSession();
		try {
			session.update("CommunityMapper.increaseViews", comNo);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	
	public CommunityDetailsDTO getCommunityDetailsByNo(Long comNo) {
		SqlSession session = getSession();
		try {
			return session.selectOne("CommunityMapper.getCommunityDetailsByNo", comNo);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}

	public List<CommunityDetailsDTO> getCommunityDetailsList() {
		SqlSession session = getSession();
		try {
			return session.selectList("CommunityMapper.getCommunityDetailsList");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return Collections.emptyList();
	}
	
	
	public Long count() {
		SqlSession session = getSession();
		try {
			return session.selectOne("CommunityMapper.count");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}
	
}
