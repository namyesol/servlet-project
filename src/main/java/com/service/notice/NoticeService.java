package com.service.notice;

import java.util.List;

import com.dao.notice.NoticeDAO;
import com.dto.notice.NoticeDTO;
import com.dto.notice.NoticeDetailsDTO;

public class NoticeService {

	private NoticeDAO dao;

	public NoticeService() {
		this.dao = new NoticeDAO();
	}
	
	public void createNotice(Long memberNo, NoticeDTO notice) {
		dao.insert(notice);
	}

	public NoticeDTO getNoticeByNo(Long noticeNo) {
		return dao.getNoticeByNo(noticeNo);
	}

	public List<NoticeDTO> getNotices() {
		return dao.getNotices();
	}

	public NoticeDetailsDTO getNoticeDetailsByNo(Long noticeNo) {
		dao.increaseViews(noticeNo);
		return dao.getNoticeDetailsByNo(noticeNo);
	}

	public List<NoticeDetailsDTO> getNoticeDetailsList() {
		return dao.getNoticeDetailsList();
	}
	
	public void updateNotice(Long noticeNo, Long memberNo, NoticeDTO updateDTO) {

		NoticeDTO notice = dao.getNoticeByNo(noticeNo);

		if (!memberNo.equals(notice.getMemberNo())) {
			return;
		}

		notice.setTitle(updateDTO.getTitle());
		notice.setContent(updateDTO.getContent());

		dao.update(notice);
	}

	public void deleteNotice(Long noticeNo, Long memberNo) {

		NoticeDTO notice = dao.getNoticeByNo(noticeNo);

		if (!memberNo.equals(notice.getMemberNo())) {
			return;
		}

		dao.delete(noticeNo);
	}

}
