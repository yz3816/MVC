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

@WebServlet("/board/content/regedit")
public class contentregeditController extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html; charset=UTF-8");

		String userNm = (req.getSession().getAttribute("userNm")).toString();
		String userID = (req.getSession().getAttribute("userID")).toString();
		req.setAttribute("u_name", userNm);
		req.setAttribute("u_id", userID);

		String title_action = req.getParameter("write_title");

		String content_action = req.getParameter("write_content");

		String button_ = req.getParameter("wr");

		String button = "";

		// dao 에 인서트에 진행

		if (button_ != null && !button_.equals("")) {
			button = button_;
			if (button.equals("입력")) {

				Notice nc = new Notice(userID, title_action, content_action);
				int result = contentDAO.getInstance().write(nc);
				System.out.println("controller result : " + result);

			}
		}

		resp.sendRedirect("list");

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("페이지 방문");
		req.getRequestDispatcher("/WEB-INF/board/content/Regedit.jsp").forward(req, resp);
	}

}
