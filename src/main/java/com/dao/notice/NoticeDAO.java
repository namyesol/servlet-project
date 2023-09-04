package com.dao.notice;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.common.PageRequestDTO;
import com.dto.notice.NoticeDTO;
import com.dto.notice.NoticeDetailsDTO;

public class NoticeDAO {

	public void insert(SqlSession session, NoticeDTO notice) {
		session.insert("NoticeMapper.insert", notice);
	}

	public NoticeDTO getNoticeByNo(SqlSession session, Long noticeNum) {
		return session.selectOne("NoticeMapper.getNoticeByNum", noticeNum);
	}

	public List<NoticeDTO> getNoticeList(SqlSession session) {
		return session.selectList("NoticeMapper.getNoticeList");
	}

	public void update(SqlSession session, NoticeDTO notice) {
		session.update("NoticeMapper.update", notice);
	}

	public void delete(SqlSession session, Long noticeNum) {
		session.delete("NoticeMapper.delete", noticeNum);
	}

	public NoticeDetailsDTO getNoticeDetailsByNo(SqlSession session, Long noticeNum) {
		return session.selectOne("NoticeMapper.getNoticeDetailsByNum", noticeNum);
	}

	public List<NoticeDetailsDTO> getNoticeDetailsList(SqlSession session, PageRequestDTO page) {
		return session.selectList("NoticeMapper.getNoticeDetailsList", page);
	}

	public void increaseViews(SqlSession session, Long noticeNum) {
		session.update("NoticeMapper.increaseViews", noticeNum);
	}
	
	public int countNotice(SqlSession session) {
		return session.selectOne("NoticeMapper.countNotice");
	}
}
