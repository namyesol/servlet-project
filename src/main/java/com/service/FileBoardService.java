package com.service;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import com.config.MySqlSessionFactory;
import com.dao.FileBoardDAO;
import com.dto.FileBoardDTO;

public class FileBoardService {
	//메인 목록보기
	public List<FileBoardDTO> fileContentList(String file_path) {
		SqlSession session = MySqlSessionFactory.getSession();
		List<FileBoardDTO> list = null;
		try {
			FileBoardDAO dao = new FileBoardDAO();
			 list = dao.fileContentList(session, file_path);
		}catch(Exception e){
			e.printStackTrace();
			System.out.println(" >>> serivce error");
		}finally {
			session.close();
		}
		return list;
	}
	//게시글 선택 - 게시글보기
	public FileBoardDTO fileContentOne(int file_board_no){
		SqlSession session = MySqlSessionFactory.getSession();
		FileBoardDTO dto = null;
		try {
		FileBoardDAO dao = new FileBoardDAO();
		dto = (FileBoardDTO) dao.fileContentOne(session, file_board_no); //서비스에서 계속 에러나서 형변환
		}catch(Exception e){
		e.printStackTrace();
		System.out.println(" >>> serivce error");
		}finally {
		session.close();
		}
		return dto;
		}

}
