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

    public List<ReplyDetailsDTO> getReplyDetailsListByComNum(Long comNum) {
		SqlSession session = getSession();
		try {
			return dao.getReplyDetailsListByComNum(session, comNum);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return Collections.emptyList();
    }
    

	public void update(Long replyNum, Long memberNum, ReplyDTO updateDTO) {
		SqlSession session = getSession();
		try {
			ReplyDTO reply = dao.getReplyByNum(session, replyNum);
			
			if (!memberNum.equals(reply.getMemberNum())) {
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
    
    public void delete(Long replyNum, Long memberNum) {
    	SqlSession session = getSession();
    	try {
	    	ReplyDTO reply = dao.getReplyByNum(session, replyNum);
	    	
	    	if (!memberNum.equals(reply.getMemberNum())) {
	    		return;
	    	}
	    	
	    	dao.delete(session, replyNum);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
    }

}
