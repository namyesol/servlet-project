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
import com.service.community.CommunityService;

@WebServlet("/CommunityListServlet")
public class CommunityListServlet extends HttpServlet {

	private static final long serialVersionUID = 6942534314363340753L;
	
	private CommunityService communityService;

	public CommunityListServlet() {
		this.communityService = new CommunityService();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		MemberDTO member = (MemberDTO) session.getAttribute(Constants.Login_Member);
		if (member == null) {
			String contextPath = request.getContextPath();
			response.sendRedirect(contextPath + Constants.Login_URL);
		} else {
			List<CommunityDetailsDTO> communityDetailsList = communityService.getCommunityDetailsList();

			request.setAttribute("communityDetailsList", communityDetailsList);

			String nextPage = "community/community-list.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);
			dispatcher.forward(request, response);
		}
	}
	
	
}
