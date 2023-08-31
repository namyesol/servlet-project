package com.service.community;

import java.util.Collections;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.config.MySqlSessionFactory;
import com.dao.community.ReplyDAO;
import com.dto.community.ReplyDTO;
import com.dto.community.ReplyDetailsDTO;


public class ReplyService {

    private ReplyDAO dao;
  
    public ReplyService() {
    	this.dao = new ReplyDAO();
    }

    public void save(ReplyDTO reply) {
    	SqlSession session = getSession();
    	try {
            dao.insert(session, reply);
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

    public List<ReplyDetailsDTO> getReplyDetailsListByComNo(Long comNo) {
		SqlSession session = getSession();
		try {
			return dao.getReplyDetailsListByComNo(session, comNo);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return Collections.emptyList();
    }
    

	public void update(Long replyNo, Long memberNo, ReplyDTO updateDTO) {
		SqlSession session = getSession();
		try {
			ReplyDTO reply = dao.getReplyByNo(session, replyNo);
			
			if (!memberNo.equals(reply.getMemberNo())) {
				return;
			}
			
			reply.setContent(updateDTO.getContent());
			dao.update(session, reply);
			
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		
	}
    
    public void delete(Long replyNo, Long memberNo) {
    	SqlSession session = getSession();
    	try {
	    	ReplyDTO reply = dao.getReplyByNo(session, replyNo);
	    	
	    	if (!memberNo.equals(reply.getMemberNo())) {
	    		return;
	    	}
	    	
	    	dao.delete(session, replyNo);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
    }

}
