package com.mvc.web.controller.signup;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mvc.web.service.userDAO;

@WebServlet("/board/user/idCheck")
public class IdcheckController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html; charset=utf-8");

		String id = req.getParameter("id");
		System.out.println("id : " + id);
		int check = userDAO.getInstance().idcheck(id);
		req.setAttribute("check", check);
		req.setAttribute("id", id);

		req.getRequestDispatcher("/WEB-INF/board/user/Idcheck.jsp").forward(req, resp);
	}
}
