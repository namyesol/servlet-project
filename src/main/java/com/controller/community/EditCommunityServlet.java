package com.controller.community;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.common.Constants;
import com.dto.MemberDTO;
import com.dto.community.CommunityDTO;
import com.service.community.CommunityService;

@WebServlet("/EditCommunityServlet")
public class EditCommunityServlet extends HttpServlet{

	private static final long serialVersionUID = 9197798200517133130L;

	private CommunityService communityService;
	
	public EditCommunityServlet() {
		this.communityService = new CommunityService();
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		MemberDTO member = (MemberDTO) session.getAttribute(Constants.Login_Member);
		if (member == null) {
			String contextPath = request.getContextPath();
			response.sendRedirect(contextPath + Constants.Login_URL);
		} else {
			Long comNo = Long.parseLong(request.getParameter("comNo"));

			CommunityDTO community = communityService.getCommunityByNo(comNo);

			request.setAttribute("community", community);

			String nextPage = "community/community-edit.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);
			dispatcher.forward(request, response);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		MemberDTO member = (MemberDTO) session.getAttribute(Constants.Login_Member);
		if (member == null) {
			String contextPath = request.getContextPath();
			response.sendRedirect(contextPath + Constants.Login_URL);
		} else {
			Long memberNo = Long.valueOf(member.getMember_num());
			Long comNo = Long.parseLong(request.getParameter("comNo"));
			String title = request.getParameter("title");
			String content = request.getParameter("content");

			CommunityDTO updateDTO = new CommunityDTO();
			updateDTO.setTitle(title);
			updateDTO.setContent(content);
			
			communityService.update(comNo, memberNo, updateDTO);
			
			String contextPath = request.getContextPath();
			response.sendRedirect(contextPath + "/CommunityDetailsServlet" + "?comNo=" + comNo);
		}
	}
}