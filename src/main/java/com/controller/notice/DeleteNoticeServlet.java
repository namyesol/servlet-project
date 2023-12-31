package com.controller.notice;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dto.MemberDTO;
import com.service.notice.NoticeService;

@WebServlet("/DeleteNoticeServlet")
public class DeleteNoticeServlet extends HttpServlet {

	private static final long serialVersionUID = 7687211089484454336L;

	private NoticeService noticeService;

	public DeleteNoticeServlet() {
		this.noticeService = new NoticeService();
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
			long noticeNum = Long.parseLong(request.getParameter("noticeNum"));

			noticeService.deleteNotice(noticeNum, memberNum);

			response.sendRedirect("/NoticeListServlet");
		}

	}

}
