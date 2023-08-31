package com.dao.community;

import java.util.Collections;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.config.MySqlSessionFactory;
import com.dto.community.ReplyDTO;
import com.dto.community.ReplyDetailsDTO;


public class ReplyDAO {
	
	public void insert(ReplyDTO reply) {
		SqlSession session = getSession();
		try {
			session.insert("ReplyMapper.insert", reply);
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

	public ReplyDTO getReplyByNo(Long replyNo) {
		SqlSession session = getSession();
		try {
			return session.selectOne("ReplyMapper.getReplyByNo", replyNo);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}

	public List<ReplyDTO> getReplyListByComNo(Long comNo) {
		SqlSession session = getSession();
		try {
			return session.selectList("ReplyMapper.getReplyListByComNo");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return Collections.emptyList();
	}

	public void update(ReplyDTO reply) {
		SqlSession session = getSession();
		try {
			session.update("ReplyMapper.update", reply);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public void delete(Long replyNo) {
		SqlSession session = getSession();
		try {
			session.delete("ReplyMapper.delete", replyNo);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	
	public List<ReplyDetailsDTO> getReplyDetailsListByComNo(Long comNo) {
		SqlSession session = getSession();
		try {
			return session.selectList("ReplyMapper.getReplyDetailsListByComNo", comNo);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return Collections.emptyList();
	}

}
