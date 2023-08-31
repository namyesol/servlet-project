package com.controller.member;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dto.MemberDTO;
import com.service.MemberService;

@WebServlet("/PasswordSearchServlet")
public class PasswordSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 데이터 파싱
		int member_num = Integer.parseInt(request.getParameter("member_num"));
		String member_name = (String) request.getParameter("member_name");
		String ssn1 = (String) request.getParameter("ssn1");
		String ssn2 = (String) request.getParameter("ssn2");
		String ssn = ssn1 + "-" + ssn2; // 테이블 형태에 맞춰서 데이터 사용
		
		// dto 생성 
		MemberDTO dto = new MemberDTO();
		dto.setMember_num(member_num);
		dto.setMember_name(member_name);
		dto.setSsn(ssn);
		
		MemberService service = new MemberService();
		// 전달받을 비밀번호
		String password = service.pwSearch(dto);
		String mesg = "";
		String nextPage = "";
		if (password != null) {
			mesg = "입력하신 이메일로 비밀번호를 전송했습니다.";
			nextPage = "MailServlet";
			request.setAttribute("password", password);
		} else {
			mesg = "입력하신 정보는 없는 정보입니다.";
			nextPage = "PasswordSearchUIServlet";
		}
		request.setAttribute("mesg", mesg);
		RequestDispatcher dis = request.getRequestDispatcher(nextPage);
		dis.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
