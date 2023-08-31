package com.controller.community;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.common.Constants;
import com.dto.MemberDTO;
import com.dto.community.CommunityDetailsDTO;
import com.dto.community.ReplyDetailsDTO;
import com.service.community.CommunityService;
import com.service.community.ReplyService;

@WebServlet("/CommunityDetailsServlet")
public class CommunityDetailsServlet extends HttpServlet {
	
	private static final long serialVersionUID = 4666471117727119815L;
	
	private CommunityService communityService;
	private ReplyService replyService;
	
	public CommunityDetailsServlet() {
		this.communityService = new CommunityService();
		this.replyService = new ReplyService();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		MemberDTO member = (MemberDTO) session.getAttribute(Constants.Login_Member);
		if (member == null) {
			String contextPath = request.getContextPath();
			response.sendRedirect(contextPath + Constants.Login_URL);
		} else {
			long comNo = Long.parseLong(request.getParameter("comNo"));

			communityService.increaseViews(comNo);
			
			CommunityDetailsDTO communityDetails = communityService.getCommunityDetailsByNo(comNo);
			List<ReplyDetailsDTO> replyDetailsList = replyService.getReplyDetailsListByComNo(comNo);
			
			request.setAttribute("communityDetails", communityDetails);
			request.setAttribute("replyDetailsList", replyDetailsList);

			String nextPage = "community/community-details.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);
			dispatcher.forward(request, response);
		}
	}

}
