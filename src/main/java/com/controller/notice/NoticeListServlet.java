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

			List<NoticeDetailsDTO> notices = noticeService.getNoticeDetailsList();

			request.setAttribute("notices", notices);

			String nextPage = "notice/notice-list.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);
			dispatcher.forward(request, response);
		}
	}

}
