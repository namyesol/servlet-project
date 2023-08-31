package com.service;

import org.apache.ibatis.session.SqlSession;

import com.config.MySqlSessionFactory;
import com.dao.MemberDAO;
import com.dto.MemberDTO;

public class MemberService {
	
	MemberDAO dao;

	// service 생성자 생성 시 dao 생성
	public MemberService() {
		dao = new MemberDAO();
	}

	// 로그인 
	public MemberDTO login(MemberDTO temp) {
		SqlSession session = MySqlSessionFactory.getSession();
		MemberDTO dto = null;
		
		try {
			dto = dao.login(session, temp);
		} finally {
			session.close();
		}
		return dto;
	}

	// 비밀번호 찾기
	public String pwSearch(MemberDTO dto) {
		SqlSession session = MySqlSessionFactory.getSession();
		String password = null;
		
		try {
			password = dao.pwSearch(session, dto);
		} finally {
			session.close();
		}
		return password;
	}

	
	public MemberDTO mypage(int memberno) {
		SqlSession session = MySqlSessionFactory.getSession();
		MemberDTO dto = null;
		try {
			 MemberDAO dao = new MemberDAO();
			 dto = dao.mypage(session, memberno);
		}finally {
			session.close();
		}
		return dto;
	}
	
	
}
