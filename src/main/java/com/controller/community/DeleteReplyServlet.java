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
import com.service.community.ReplyService;

@WebServlet("/DeleteReplyServlet")
public class DeleteReplyServlet extends HttpServlet {

	private static final long serialVersionUID = -1469839218469414445L;
	
	private ReplyService replyService;
	
	public DeleteReplyServlet() {
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
			Long comNo = Long.parseLong(request.getParameter("comNo"));
			Long memNo = Long.valueOf(member.getMember_num());
			
			replyService.delete(replyNo, memNo);
			
			String contextPath = request.getContextPath();
			response.sendRedirect(contextPath + "/CommunityDetailsServlet" + "?comNo=" + comNo);
		}
	}
}