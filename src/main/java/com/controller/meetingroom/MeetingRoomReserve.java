package com.controller.meetingroom;

import java.io.IOException;
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

@WebServlet("/MeetingRoomReserve")
public class MeetingRoomReserve extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MeetingRoomReserve() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		MemberDTO dto = (MemberDTO) session.getAttribute("login");
		String nextPage = null;
		
		if(dto != null) {//로그인성공시 실행
			nextPage = "app_meeting.jsp";
			int memberNum = dto.getMember_num();//dto에서 memeber_num가져오기
			//예약버튼 클릭 = 1
			String reservation =request.getParameter("buttonValue");
			String meeting_date = request.getParameter("date");
			String meeting_num = request.getParameter("roomNum");
			String meeting_time = request.getParameter("time");
			
			MeetingRoomDTO dtoM = new MeetingRoomDTO(memberNum, meeting_num, meeting_date, meeting_time, reservation);
			MeetingRoomService service = new MeetingRoomService();
			MemberService service2 = new MemberService();
			
			int num = service.reserve(dtoM);
			MemberDTO pagedto = service2.mypage(memberNum);
			
			if(num == 1) {
				session.setAttribute("login",pagedto);
				session.setAttribute("success", "예약이 완료됐습니다.");
			}//if2
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
