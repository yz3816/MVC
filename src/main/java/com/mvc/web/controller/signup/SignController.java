package com.mvc.web.controller.signup;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.PageContext;

import com.mvc.web.entity.user.Register;
import com.mvc.web.entity.user.User;
import com.mvc.web.service.userDAO;

@WebServlet("/user/signup")
public class SignController extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html; charset=UTF-8");

		String pid = req.getParameter("id");
		String ppass = req.getParameter("password");
		String pname = req.getParameter("name");
		String pemail = req.getParameter("email");

		Register rt = new Register(pid, ppass, pname, pemail);

		int result = userDAO.getInstance().signup(rt);

		if (result > 0) {
			
			resp.sendRedirect("/user/success");			

		}

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.getRequestDispatcher("/WEB-INF/board/user/Signup.jsp").forward(req, resp);

	}

}
