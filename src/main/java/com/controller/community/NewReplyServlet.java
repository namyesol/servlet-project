package com.controller.community;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.common.Constants;
import com.dto.MemberDTO;
import com.dto.community.ReplyDTO;
import com.service.community.ReplyService;

@WebServlet("/NewReplyServlet")
public class NewReplyServlet extends HttpServlet {
	
	private static final long serialVersionUID = 6972517525890801447L;

	private ReplyService replyService;
	
	public NewReplyServlet() {
		this.replyService = new ReplyService();
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		MemberDTO member = (MemberDTO) session.getAttribute(Constants.Login_Member);
		if (member == null) {
			String contextPath = request.getContextPath();
			response.sendRedirect(contextPath + Constants.Login_URL);
		} else {
			Long memberNum = Long.valueOf(member.getMember_num());
			Long comNum = Long.valueOf(request.getParameter("comNum"));
			String content = request.getParameter("content");
			
			ReplyDTO reply = new ReplyDTO(memberNum, comNum, content);
			
			replyService.save(reply);
			
			String contextPath = request.getContextPath();
			response.sendRedirect(contextPath + "/CommunityDetailsServlet"+ "?comNum=" + comNum);
		}
	}

}
