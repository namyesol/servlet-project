package com.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.dto.FileBoardDTO;

public class FileBoardDAO {
	//게시글 목록 불러오기
	public List<FileBoardDTO> fileContentList(SqlSession session, String file_path) {
		 List<FileBoardDTO> list = 
				 session.selectList("fileContentList", file_path);
		return list;
	}
	
	//게시글 선택 - 게시글보기
	public FileBoardDTO fileContentOne(SqlSession session, int file_board_no) {
	FileBoardDTO dto=
	session.selectOne("fileContentOne", file_board_no);
	return dto;
	}

}
