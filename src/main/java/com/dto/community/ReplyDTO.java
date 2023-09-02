package com.dto.community;

import java.util.Date;

public class ReplyDTO {

    private Long replyNum;
    private Long memberNum;
    private Long comNum;
    private String content;
    private Date createdAt;

    public ReplyDTO() {
    }

    public ReplyDTO(Long memberNum, Long comNum, String content) {
        this.memberNum = memberNum;
        this.comNum = comNum;
        this.content = content;
        this.createdAt = new Date();
    }

	public ReplyDTO(Long replyNum, Long memberNum, Long comNum, String content, Date createdAt) {
		super();
		this.replyNum = replyNum;
		this.memberNum = memberNum;
		this.comNum = comNum;
		this.content = content;
		this.createdAt = createdAt;
	}

	public Long getReplyNum() {
		return replyNum;
	}

	public void setReplyNum(Long replyNum) {
		this.replyNum = replyNum;
	}

	public Long getMemberNum() {
		return memberNum;
	}

	public void setMemberNum(Long memberNum) {
		this.memberNum = memberNum;
	}

	public Long getComNum() {
		return comNum;
	}

	public void setComNum(Long comNum) {
		this.comNum = comNum;
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
		return "ReplyDTO [replyNum=" + replyNum + ", memberNum=" + memberNum + ", comNum=" + comNum + ", content="
				+ content + ", createdAt=" + createdAt + "]";
	}

    
	
}
