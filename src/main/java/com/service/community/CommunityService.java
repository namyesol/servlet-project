package com.service.community;

import java.util.List;

import com.dao.community.CommunityDAO;
import com.dao.community.ReplyDAO;
import com.dto.community.CommunityDTO;
import com.dto.community.CommunityDetailsDTO;
import com.dto.community.ReplyDTO;



public class CommunityService {


    private CommunityDAO communityDao;
    private ReplyDAO replyDao;

    public CommunityService() {
        this.communityDao = new CommunityDAO();
        this.replyDao = new ReplyDAO();
    }

    public void save(CommunityDTO community) {
        communityDao.insert(community);
    }

    public CommunityDTO getCommunityByNo(Long comNo) {
        CommunityDTO community = communityDao.getCommunityByNo(comNo);
        return community;
    }

    public List<CommunityDTO> getCommunityList() {
        return communityDao.getCommunityList();
    }

    public void update(Long comNo, Long memberNo, CommunityDTO updateParam) {
        
        CommunityDTO community = communityDao.getCommunityByNo(comNo);

        if (!community.getMemberNo().equals(memberNo)) {
            return;
        }
        
        community.setTitle(updateParam.getTitle());
        community.setContent(updateParam.getContent());
        communityDao.update(community);
    }

    public void delete(Long comNo, Long memberNo) {
        CommunityDTO community = communityDao.getCommunityByNo(comNo);

        if (!community.getMemberNo().equals(memberNo)) {
            return;
        }
        
        List<ReplyDTO> replyList = replyDao.getReplyListByComNo(comNo);
        for (ReplyDTO reply : replyList) {
            replyDao.delete(reply.getReplyNo());
        }
        communityDao.delete(comNo);
    }
    
    public void increaseViews(Long comNo) {
    	communityDao.increaseViews(comNo);
    }

    public CommunityDetailsDTO getCommunityDetailsByNo(Long replyNo) {
        return communityDao.getCommunityDetailsByNo(replyNo); 
    }

    public List<CommunityDetailsDTO> getCommunityDetailsList() {
       return communityDao.getCommunityDetailsList();

    }
}
