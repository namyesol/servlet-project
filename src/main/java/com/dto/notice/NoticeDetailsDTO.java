package com.dto.notice;

import java.util.Date;

public class NoticeDetailsDTO {
	private Long noticeNo;
	private Long memberNo;
	private String memberName;
	private String title;
	private String content;
	private Integer views;
	private Date createdAt;
	

	public NoticeDetailsDTO() {
	}
	
	
	public Long getNoticeNo() {
		return noticeNo;
	}
	public void setNoticeNo(Long noticeNo) {
		this.noticeNo = noticeNo;
	}
	public Long getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(Long memberNo) {
		this.memberNo = memberNo;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getViews() {
		return views;
	}
	public void setViews(Integer views) {
		this.views = views;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}


	@Override
	public String toString() {
		return "NoticeDetails [noticeNo=" + noticeNo + ", memberNo=" + memberNo + ", memberName=" + memberName
				+ ", title=" + title + ", content=" + content + ", views=" + views + ", createdAt=" + createdAt + "]";
	}
	
	
}
