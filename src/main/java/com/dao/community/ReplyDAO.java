package com.dao.community;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.dto.community.ReplyDTO;
import com.dto.community.ReplyDetailsDTO;

public class ReplyDAO {
	
	public void insert(SqlSession session, ReplyDTO reply) {
		session.insert("ReplyMapper.insert", reply);
	}

	public ReplyDTO getReplyByNo(SqlSession session, Long replyNo) {
		return session.selectOne("ReplyMapper.getReplyByNo", replyNo);
	}

	public List<ReplyDTO> getReplyListByComNo(SqlSession session, Long comNo) {
		return session.selectList("ReplyMapper.getReplyListByComNo");
	}

	public void update(SqlSession session, ReplyDTO reply) {
		session.update("ReplyMapper.update", reply);
	}

	public void delete(SqlSession session, Long replyNo) {
		session.delete("ReplyMapper.delete", replyNo);
	}
	
	public List<ReplyDetailsDTO> getReplyDetailsListByComNo(SqlSession session, Long comNo) {
		return session.selectList("ReplyMapper.getReplyDetailsListByComNo", comNo);
	}
}
