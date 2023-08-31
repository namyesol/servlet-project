package com.dao;

import org.apache.ibatis.session.SqlSession;

import com.dto.MemberDTO;

public class MemberDAO {

	// 로그인
	public MemberDTO login(SqlSession session, MemberDTO temp) {
		MemberDTO dto = session.selectOne("login", temp);
		return dto;
	}

	// 비밀번호 찾기
	public String pwSearch(SqlSession session, MemberDTO dto) {
		String password = session.selectOne("pwSearch", dto);
		return password;
	}

	public MemberDTO mypage(SqlSession session, int memberno) {
		 MemberDTO dto = session.selectOne("MemberMapper.mypage", memberno);
			return dto;
	}
}
