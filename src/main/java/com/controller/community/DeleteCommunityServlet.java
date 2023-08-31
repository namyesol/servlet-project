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
import com.service.community.CommunityService;

@WebServlet("/DeleteCommunityServlet")
public class DeleteCommunityServlet extends HttpServlet {

	private static final long serialVersionUID = -5928338049985630888L;

	private CommunityService communityService;

	public DeleteCommunityServlet() {
		this.communityService = new CommunityService();
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		MemberDTO member = (MemberDTO) session.getAttribute(Constants.Login_Member);
		if (member == null) {
			String contextPath = request.getContextPath();
			response.sendRedirect(contextPath + Constants.Login_URL);
		} else {
			Long comNo = Long.parseLong(request.getParameter("comNo"));
			Long memNo = Long.valueOf(member.getMember_num());
			
			communityService.delete(comNo, memNo);
			
			String contextPath = request.getContextPath();
			response.sendRedirect(contextPath + "/CommunityListServlet");
		}
	}

}
