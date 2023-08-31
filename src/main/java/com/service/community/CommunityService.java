package com.service.community;

import java.util.Collections;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.config.MySqlSessionFactory;
import com.dao.community.CommunityDAO;
import com.dao.community.ReplyDAO;
import com.dto.community.CommunityDTO;
import com.dto.community.CommunityDetailsDTO;
import com.dto.community.ReplyDTO;



public class CommunityService {


    private CommunityDAO communityDao;
    private ReplyDAO replyDao;

    public CommunityService() {
        this.communityDao = new CommunityDAO();
        this.replyDao = new ReplyDAO();
    }

    public void save(CommunityDTO community) {
    	SqlSession session = getSession();
    	try {
    		communityDao.insert(session, community);
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
	        CommunityDTO community = communityDao.getCommunityByNo(session, comNo);
	        return community;
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
			return communityDao.getCommunityList(session);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return Collections.emptyList();
    }

    public void update(Long comNo, Long memberNo, CommunityDTO updateParam) {
		SqlSession session = getSession();
		try {
	        CommunityDTO community = communityDao.getCommunityByNo(session, comNo);
	
	        if (!community.getMemberNo().equals(memberNo)) {
	            return;
	        }
	        
	        community.setTitle(updateParam.getTitle());
	        community.setContent(updateParam.getContent());
	        
	        communityDao.update(session, community);
			
	        session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
    }

    public void delete(Long comNo, Long memberNo) {
		SqlSession session = getSession();
		try {
	        CommunityDTO community = communityDao.getCommunityByNo(session, comNo);
	
	        if (!community.getMemberNo().equals(memberNo)) {
	            return;
	        }
	        
	        List<ReplyDTO> replyList = replyDao.getReplyListByComNo(session, comNo);
	        for (ReplyDTO reply : replyList) {
	            replyDao.delete(session, reply.getReplyNo());
	        }
	        communityDao.delete(session, comNo);
	        
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
			communityDao.increaseViews(session, comNo);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
    }

    public CommunityDetailsDTO getCommunityDetailsByNo(Long replyNo) {
    	SqlSession session = getSession();
    	try {
    		return communityDao.getCommunityDetailsByNo(session, replyNo);
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
    		return communityDao.getCommunityDetailsList(session);
    	} catch (Exception e) {
    		e.printStackTrace();
    	} finally {
    		session.close();
    	}
       
       return Collections.emptyList();
    }
}
