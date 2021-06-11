package com.mvc.web.controller.content;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mvc.web.entity.content.Notice;
import com.mvc.web.service.contentDAO;

@WebServlet("/board/content/modify")
public class contentModifyController extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("utf-8");
		
		int id = Integer.parseInt(req.getParameter("id"));
		String title = req.getParameter("title");
		String content = req.getParameter("content");

		int result = contentDAO.getInstance().modify(title, content, id);
		
		if(result == 1) {
			resp.sendRedirect("detail?id="+id);
			
		} else if(result == 0) {
			req.setAttribute("ment", 0);
		}
		
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.setContentType("text/html; charset=UTF-8");
		
		int id = Integer.parseInt(req.getParameter("id"));
		System.out.println("id : " + id);

		Notice nd = contentDAO.getInstance().getDetail(id);
		
		String title = nd.getTitle();
		String content = nd.getContent();
		
		req.setAttribute("title", title);
		req.setAttribute("content", content);
		
	

		req.getRequestDispatcher("/WEB-INF/board/content/Modify.jsp").forward(req, resp);
	}
}
