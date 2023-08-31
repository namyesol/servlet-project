package com.dto.community;

import java.util.Date;

public class ReplyDetailsDTO {
    private Long replyNo;
    private Long memberNo;
    private String memberName;
    private Long comNo;
    private String content;
    private Date createdAt;

    public ReplyDetailsDTO() {
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

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
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

}
