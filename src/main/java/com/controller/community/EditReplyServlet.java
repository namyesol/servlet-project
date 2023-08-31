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

@WebServlet("/EditReplyServlet")
public class EditReplyServlet extends HttpServlet {

	private static final long serialVersionUID = -7449134374811439274L;
	
	private ReplyService replyService;
	
	public EditReplyServlet() {
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
			Long replyNo = Long.parseLong(request.getParameter("replyNo"));
			Long memberNo = Long.valueOf(member.getMember_num());
			Long comNo = Long.parseLong(request.getParameter("comNo"));
			String content = request.getParameter("content");
			
			ReplyDTO updateDTO = new ReplyDTO();
			updateDTO.setContent(content);
			replyService.update(replyNo, memberNo, updateDTO);
			
			String contextPath = request.getContextPath();
			response.sendRedirect(contextPath + "/CommunityDetailsServlet" + "?comNo=" + comNo);
		}
	}
	
	
}
