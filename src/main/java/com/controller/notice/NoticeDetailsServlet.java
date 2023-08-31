package com.controller.notice;

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

import com.dto.notice.NoticeDetailsDTO;
import com.service.notice.NoticeService;

@WebServlet("/NoticeDetailsServlet")
public class NoticeDetailsServlet extends HttpServlet {

	private static final long serialVersionUID = -3290708711995159911L;

	private NoticeService noticeService;

	public NoticeDetailsServlet() {
		this.noticeService = new NoticeService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		MemberDTO member = (MemberDTO) session.getAttribute(Constants.Login_Member);
		if (member == null) {
			String contextPath = request.getContextPath();
			response.sendRedirect(contextPath + Constants.Login_URL);
		} else {
			long noticeNo = Long.parseLong(request.getParameter("noticeNo"));

			NoticeDetailsDTO notice = noticeService.getNoticeDetailsByNo(noticeNo);

			request.setAttribute("notice", notice);

			String nextPage = "notice/notice-details.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);
			dispatcher.forward(request, response);
		}
	}

}
