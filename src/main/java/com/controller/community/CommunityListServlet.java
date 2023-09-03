package com.controller.community;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.common.Constants;
import com.common.Page;
import com.common.PageResponseDTO;
import com.dto.MemberDTO;
import com.dto.community.CommunityDetailsDTO;
import com.service.community.CommunityService;

@WebServlet("/CommunityListServlet")
public class CommunityListServlet extends HttpServlet {

	private static final long serialVersionUID = 6942534314363340753L;
	
	private static final Logger log = LoggerFactory.getLogger(CommunityListServlet.class);
	
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
			Page page = buildPageFrom(request);
			
			PageResponseDTO<CommunityDetailsDTO> pageResponse = communityService.getCommunityDetailsList(page);
			request.setAttribute("pageResponse", pageResponse);
			
			String nextPage = "community/community-list.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);
			dispatcher.forward(request, response);
		}
	}
	
	private Page buildPageFrom(HttpServletRequest request) {
		String pageParam = request.getParameter("page");
		String sizeParam = request.getParameter("size");
		int pageNumber = pageParam == null ? Page.START_PAGE_OFFSET : Integer.parseInt(pageParam);
		int size = sizeParam == null ? Page.DEFAULT_PAGE_SIZE : Integer.parseInt(sizeParam);
		
		return new Page(pageNumber, size);
	}
	
}
