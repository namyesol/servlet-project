package com.dto.community;

import java.util.Date;

public class CommunityDTO {

	private Long comNo;
	private Long memberNo;
	private String title;
	private String content;
	private Integer views;
	private Date createdAt;

	public CommunityDTO() {
	}

	public CommunityDTO(Long memberNo, String title, String content) {
		this.memberNo = memberNo;
		this.title = title;
		this.content = content;
		this.views = Integer.valueOf(0);
		this.createdAt = new Date();
	}

	public Long getComNo() {
		return comNo;
	}

	public void setComNo(Long comNo) {
		this.comNo = comNo;
	}

	public Long getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(Long memberNo) {
		this.memberNo = memberNo;
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
		return "CommunityDTO [comNo=" + comNo + ", memberNo=" + memberNo + ", title=" + title + ", content=" + content
				+ ", views=" + views + ", createdAt=" + createdAt + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((comNo == null) ? 0 : comNo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CommunityDTO other = (CommunityDTO) obj;
		if (comNo == null) {
			if (other.comNo != null)
				return false;
		} else if (!comNo.equals(other.comNo))
			return false;
		return true;
	}

}
