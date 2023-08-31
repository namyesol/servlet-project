package com.dao.notice;

import java.util.Collections;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.config.MySqlSessionFactory;
import com.dto.notice.NoticeDTO;
import com.dto.notice.NoticeDetailsDTO;

public class NoticeDAO {

	public void insert(NoticeDTO notice) {
		SqlSession session = getSession();
		try {
			session.insert("NoticeMapper.insert", notice);
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

	public NoticeDTO getNoticeByNo(Long noticeNo) {
		SqlSession session = getSession();
		try {
			return session.selectOne("NoticeMapper.getNoticeByNo", noticeNo);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}

	public List<NoticeDTO> getNotices() {
		SqlSession session = getSession();
		try {
			return session.selectList("NoticeMapper.getNotices");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return Collections.emptyList();
	}

	public void update(NoticeDTO notice) {
		SqlSession session = getSession();
		try {
			session.update("NoticeMapper.update", notice);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public void delete(Long noticeNo) {
		SqlSession session = getSession();
		try {
			session.delete("NoticeMapper.delete", noticeNo);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public NoticeDetailsDTO getNoticeDetailsByNo(Long noticeNo) {
		SqlSession session = getSession();
		try {
			return session.selectOne("NoticeMapper.getNoticeDetailsByNo", noticeNo);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}

	public List<NoticeDetailsDTO> getNoticeDetailsList() {
		SqlSession session = getSession();
		try {
			return session.selectList("NoticeMapper.getNoticeDetailsList");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return Collections.emptyList();
	}

	public void increaseViews(Long noticeNo) {
		SqlSession session = getSession();
		try {
			session.update("NoticeMapper.increaseViews", noticeNo);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
}
