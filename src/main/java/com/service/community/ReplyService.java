package com.service.community;

import java.util.List;

import com.dao.community.ReplyDAO;
import com.dto.community.ReplyDTO;
import com.dto.community.ReplyDetailsDTO;


public class ReplyService {

    private ReplyDAO dao;
  
    public ReplyService() {
    	this.dao = new ReplyDAO();
    }


    public void save(ReplyDTO reply) {
        dao.insert(reply);
    }

    public List<ReplyDetailsDTO> getReplyDetailsListByComNo(Long comNo) {
        return dao.getReplyDetailsListByComNo(comNo);
    }
    

	public void update(Long replyNo, Long memberNo, ReplyDTO updateDTO) {
		ReplyDTO reply = dao.getReplyByNo(replyNo);
		
		if (!memberNo.equals(reply.getMemberNo())) {
			return;
		}
		
		reply.setContent(updateDTO.getContent());
		dao.update(reply);
	}
    
    public void delete(Long replyNo, Long memberNo) {
    	
    	ReplyDTO reply = dao.getReplyByNo(replyNo);
    	
    	if (!memberNo.equals(reply.getMemberNo())) {
    		return;
    	}
    	
    	dao.delete(replyNo);
    }



}
