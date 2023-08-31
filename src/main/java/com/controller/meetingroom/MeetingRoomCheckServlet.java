package com.controller.meetingroom;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dto.MeetingRoomDTO;
import com.dto.MemberDTO;
import com.service.MeetingRoomService;
import com.service.MemberService;

@WebServlet("/MeetingRoomCheck")
public class MeetingRoomCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MeetingRoomCheckServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		MemberDTO dto = (MemberDTO) session.getAttribute("login");
		String nextPage = null;
		
		if(dto != null) {//로그인성공시 실행
			nextPage = "meetingRoomCheck.jsp";
			int memberNum = dto.getMember_num();//dto에서 memeber_num가져오기
			
			MeetingRoomService serviceM = new MeetingRoomService();
			List<MeetingRoomDTO> list =  serviceM.select(memberNum);
			session.setAttribute("list", list);
			
		}else {
			nextPage = "LoginServlet";
			session.setAttribute("mesg", "로그인이 필요하다.");
		}
		response.sendRedirect(nextPage);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
