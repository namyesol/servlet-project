package com.controller.notice;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dto.MemberDTO;
import com.dto.notice.NoticeDTO;
import com.service.notice.NoticeService;

@WebServlet("/NewNoticeServlet")
public class NewNoticeServlet extends HttpServlet {

	private static final long serialVersionUID = 7493222382728034284L;

	private NoticeService noticeService;

	public NewNoticeServlet() {
		this.noticeService = new NoticeService();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		MemberDTO member = (MemberDTO) session.getAttribute("login");
		if (member == null) {
			response.sendRedirect("/");
		} else {
			String nextPage = "notice/notice-new.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);
			dispatcher.forward(request, response);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		MemberDTO member = (MemberDTO) session.getAttribute("login");

		if (member == null) {
			response.sendRedirect("/");
		} else {

			Long memberNum = Long.valueOf(member.getMember_num());

			String title = request.getParameter("title");
			String content = request.getParameter("content");

			NoticeDTO notice = new NoticeDTO(memberNum, title, content);

			noticeService.createNotice(memberNum, notice);

			String contextPath = request.getContextPath();
			response.sendRedirect(contextPath + "/NoticeDetailsServlet" + "?noticeNum=" + notice.getNoticeNum());
		}
	}

}
