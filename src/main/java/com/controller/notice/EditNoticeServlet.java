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
import com.dto.notice.NoticeDTO;
import com.service.notice.NoticeService;

@WebServlet("/EditNoticeServlet")
public class EditNoticeServlet extends HttpServlet {

	private static final long serialVersionUID = 6645387813338971677L;
	
	private NoticeService noticeService;

	public EditNoticeServlet() {
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
			String parameter = request.getParameter("noticeNo");
			Long noticeNo = Long.parseLong(parameter);

			NoticeDTO notice = noticeService.getNoticeByNo(noticeNo);

			request.setAttribute("notice", notice);

			String nextPage = "notice/edit-notice.jsp";
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

			String parameter = request.getParameter("noticeNo");
			Long noticeNo = Long.parseLong(parameter);

			String title = request.getParameter("title");
			String content = request.getParameter("content");

			NoticeDTO updateDTO = new NoticeDTO();
			updateDTO.setTitle(title);
			updateDTO.setContent(content);
			
			noticeService.updateNotice(noticeNo, memberNo, updateDTO);

			String contextPath = request.getContextPath();
			response.sendRedirect(contextPath + "/NoticeDetailsServlet" + "?noticeNo=" + noticeNo);
		}
	}

}
