package com.controller.notice;

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
import com.common.Page;
import com.common.PageResponseDTO;
import com.dto.MemberDTO;
import com.dto.notice.NoticeDetailsDTO;
import com.service.notice.NoticeService;

@WebServlet("/NoticeListServlet")
public class NoticeListServlet extends HttpServlet {

	private static final long serialVersionUID = 640676923100690094L;

	private NoticeService noticeService;

	public NoticeListServlet() {
		this.noticeService = new NoticeService();
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
			Page page = buildPageFrom(request);
			
			PageResponseDTO<NoticeDetailsDTO> pageResponse = noticeService.getNoticeDetailsList(page);

			request.setAttribute("pageResponse", pageResponse);

			String nextPage = "notice/notice-list.jsp";
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
