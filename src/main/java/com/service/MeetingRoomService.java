package com.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.config.MySqlSessionFactory;
import com.dao.MeetingRoomDAO;
import com.dto.MeetingRoomDTO;

public class MeetingRoomService {
	MeetingRoomDAO dao;
	
	// service 생성자 생성 시 dao 생성
	public MeetingRoomService() {
		dao = new MeetingRoomDAO();
	}

	// 회의실 예약 
	public int reserve(MeetingRoomDTO dto) {
		SqlSession session = MySqlSessionFactory.getSession();
		int num = 0;
		try {
			num = dao.reserve(session, dto);
			session.commit();
		} finally {
			session.close();
		}
		return num;
	}

	public List<MeetingRoomDTO> select(int memberNum) {
		SqlSession session = MySqlSessionFactory.getSession();
		List<MeetingRoomDTO> list = null;
		try {
			list = dao.select(session, memberNum);
		} finally {
			session.close();
		}
		return list;
	}

	
	
}
