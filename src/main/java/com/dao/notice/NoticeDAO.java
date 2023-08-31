package com.dao.notice;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.dto.notice.NoticeDTO;
import com.dto.notice.NoticeDetailsDTO;

public class NoticeDAO {

	public void insert(SqlSession session, NoticeDTO notice) {
		session.insert("NoticeMapper.insert", notice);
	}

	public NoticeDTO getNoticeByNo(SqlSession session, Long noticeNo) {
		return session.selectOne("NoticeMapper.getNoticeByNo", noticeNo);
	}

	public List<NoticeDTO> getNotices(SqlSession session) {
		return session.selectList("NoticeMapper.getNotices");
	}

	public void update(SqlSession session, NoticeDTO notice) {
		session.update("NoticeMapper.update", notice);
	}

	public void delete(SqlSession session, Long noticeNo) {
		session.delete("NoticeMapper.delete", noticeNo);
	}

	public NoticeDetailsDTO getNoticeDetailsByNo(SqlSession session, Long noticeNo) {
		return session.selectOne("NoticeMapper.getNoticeDetailsByNo", noticeNo);
	}

	public List<NoticeDetailsDTO> getNoticeDetailsList(SqlSession session) {
		return session.selectList("NoticeMapper.getNoticeDetailsList");
	}

	public void increaseViews(SqlSession session, Long noticeNo) {
		session.update("NoticeMapper.increaseViews", noticeNo);
	}
}
