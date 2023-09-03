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

/**
 * Servlet implementation class FileBoardServlet
 */
@WebServlet("/FileBoardPost")
public class FileBoardPostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int file_board_no = Integer.parseInt(request.getParameter("file_board_no"));
		// 게시글번호 System.out.println(">>> FileCode" + FileBoardNo);

		if (file_board_no == 0) {
			try {
			file_board_no = 0;
			System.out.println(">>> number 000");
			}catch(Exception e){
				e.printStackTrace();
				System.out.println(" >>> number error");
				
			}
		}
		FileBoardService service = new FileBoardService();
		FileBoardDTO dto = service.fileContentOne(file_board_no);
		request.setAttribute("FileBoardPost", dto);
		System.out.println(">>> 게시글 jsp :"+dto);
		RequestDispatcher dir = request.getRequestDispatcher("fileboard/filePostBoard.jsp"); // 메인아니고 게시글
		dir.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
