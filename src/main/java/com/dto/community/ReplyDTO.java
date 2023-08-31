package com.dto.community;

import java.util.Date;

public class ReplyDTO {

    private Long replyNo;
    private Long memberNo;
    private Long comNo;
    private String content;
    private Date createdAt;

    public ReplyDTO() {
    }

    public ReplyDTO(Long memberNo, Long comNo, String content) {
        this.memberNo = memberNo;
        this.comNo = comNo;
        this.content = content;
        this.createdAt = new Date();
    }

	public Long getReplyNo() {
		return replyNo;
	}

	public void setReplyNo(Long replyNo) {
		this.replyNo = replyNo;
	}

	public Long getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(Long memberNo) {
		this.memberNo = memberNo;
	}

	public Long getComNo() {
		return comNo;
	}

	public void setComNo(Long comNo) {
		this.comNo = comNo;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public String toString() {
		return "ReplyDTO [replyNo=" + replyNo + ", memberNo=" + memberNo + ", comNo=" + comNo + ", content=" + content
				+ ", createdAt=" + createdAt + "]";
	}

	
}
