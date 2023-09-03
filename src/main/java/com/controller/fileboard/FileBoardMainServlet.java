package com.controller.fileboard;

import java.io.IOException;

import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dto.FileBoardDTO;
import com.service.FileBoardService;

@WebServlet("/FileBoardMain") // 시작 지점
public class FileBoardMainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       	//게시글 목록 불러오기
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String file_path = request.getParameter("file_path");	//게시글번호
		System.out.println(">>> file_path " + file_path);
		
		if(file_path==null) {
			file_path = "top";
			System.out.println(">>> reset top");
		}
		
		FileBoardService service = new FileBoardService();
		List<FileBoardDTO> list = service.fileContentList(file_path);
		request.setAttribute("fileContentList", list);
		System.out.println(list);
		RequestDispatcher dis = request.getRequestDispatcher("fileboard/fileBoardMain.jsp");
		dis.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
