package com.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.dto.MeetingRoomDTO;
import com.dto.MemberDTO;

public class MeetingRoomDAO {

	// 회의실 예약하기
	public int reserve(SqlSession session, MeetingRoomDTO dto) {
		int num = session.insert("reserve",dto);
		return num;
	}

	public List<MeetingRoomDTO> select(SqlSession session, int memberNum) {
		List<MeetingRoomDTO> list = session.selectList("select", memberNum);
		return list;
	}

}
