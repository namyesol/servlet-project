package com.service.notice;

import java.util.Collections;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.common.Page;
import com.common.PageResponseDTO;
import com.config.MySqlSessionFactory;
import com.dao.notice.NoticeDAO;
import com.dto.notice.NoticeDTO;
import com.dto.notice.NoticeDetailsDTO;

public class NoticeService {

	private NoticeDAO dao;

	public NoticeService() {
		this.dao = new NoticeDAO();
	}
	
	public void createNotice(Long memberNum, NoticeDTO notice) {
		SqlSession session = getSession();
		try {
			dao.insert(session, notice); 
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

	public NoticeDTO getNoticeByNo(Long noticeNum) {
		SqlSession session = getSession();
		try {
			return dao.getNoticeByNo(session, noticeNum);
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
			return dao.getNotices(session); 
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return Collections.emptyList();
	}

	public NoticeDetailsDTO getNoticeDetailsByNo(Long noticeNum) {
		SqlSession session = getSession();
		try {
			dao.increaseViews(session, noticeNum);
			NoticeDetailsDTO notice = dao.getNoticeDetailsByNo(session, noticeNum);
			session.commit();
			return notice;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return null;
	}

	public PageResponseDTO<NoticeDetailsDTO> getNoticeDetailsList(Page page) {
		SqlSession session = getSession();
		try {
			int count= dao.countNotice(session);
			List<NoticeDetailsDTO> noticeDetailsList = dao.getNoticeDetailsList(session, page);
			return new PageResponseDTO<NoticeDetailsDTO>(page, noticeDetailsList, count);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return new PageResponseDTO<NoticeDetailsDTO>(page, Collections.emptyList(), 0);
	}
	
	public void updateNotice(Long noticeNum, Long memberNum, NoticeDTO updateDTO) {
		SqlSession session = getSession();
		try {
			NoticeDTO notice = dao.getNoticeByNo(session, noticeNum);
	
			if (!memberNum.equals(notice.getMemberNum())) {
				return;
			}
	
			notice.setTitle(updateDTO.getTitle());
			notice.setContent(updateDTO.getContent());
	
			dao.update(session, notice);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public void deleteNotice(Long noticeNum, Long memberNum) {
		SqlSession session = getSession();
		try {
			
			NoticeDTO notice = dao.getNoticeByNo(session, noticeNum);
	
			if (!memberNum.equals(notice.getMemberNum())) {
				return;
			}
	
			dao.delete(session, noticeNum);
			session.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
}
