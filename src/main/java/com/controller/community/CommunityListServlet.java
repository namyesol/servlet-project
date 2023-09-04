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
			// 페이지네이션 정보 생성
			String pageParam = request.getParameter("page");
			String sizeParam = request.getParameter("size");
			// 요청페이지의 기본 값
			int requestPage = 1;
			// 요청페이지의 기본 크기 
			int size = 5;
			
			// 사용자의 요청이 있다면 페이지 번호와 크기를 변경함
			if (pageParam != null && !pageParam.isEmpty()) {
				requestPage = Integer.parseInt(pageParam);
			}
			if (sizeParam != null && !sizeParam.isEmpty()) {
				size = Integer.parseInt(sizeParam);
			}
			
			Page page = new Page(requestPage, size);
			
			PageResponseDTO<CommunityDetailsDTO> pageResponse = communityService.getCommunityDetailsList(page);
			request.setAttribute("pageResponse", pageResponse);
			
			String nextPage = "community/community-list.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);
			dispatcher.forward(request, response);
		}
	}
	
}
