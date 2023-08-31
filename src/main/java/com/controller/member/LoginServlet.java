package com.controller.member;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dto.MemberDTO;
import com.service.MemberService;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		int member_num = Integer.parseInt(request.getParameter("member_num"));
		String password = (String) request.getParameter("password");
		
		// 파라미터로 받아온 데이터로 임시 dto 생성
		MemberDTO temp = new MemberDTO();
		temp.setMember_num(member_num);
		temp.setPassword(password);
		
		// 조건에 맞는 임시 dto 를 dto 로 불러오기 
		MemberService service = new MemberService();
		MemberDTO dto = service.login(temp);
		
		// 회원처리
		String nextPage = null;
		if (dto != null) {
			// 불러온 dto 가 있을 경우 session 에 저장
			session.setAttribute("login", dto);
			System.out.println(dto);
			nextPage = "MainServlet";
		} else {
			// 불러온 dto 가 없을 경우 main 으로 이동
			nextPage = "main";
			session.setAttribute("mesg", "로그인에 실패했습니다");
			session.setMaxInactiveInterval(60 * 60);
		}
		response.sendRedirect(nextPage);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
