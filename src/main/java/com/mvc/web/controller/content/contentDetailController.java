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

@WebServlet("/board/content/detail")
public class contentDetailController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String field_ = req.getParameter("f");
		String qurry_ = req.getParameter("q");
		String field = "title";
		String qurry = "";

		if (field_ != null && field_ != "") {
			field = field_;
		}
		if (qurry_ != null && qurry_ != "") {
			qurry = qurry_;
		}

		int id = 0;
		String par = req.getParameter("id");

		if (par != null && par != "") {
			id = Integer.parseInt(par);
		}
		
		contentDAO.getInstance().Uphit(id);
		Notice nt = contentDAO.getInstance().getDetail(id);

		HttpSession session = req.getSession();
		session.setAttribute("par", par);
		
		req.setAttribute("nt", nt);
		req.setAttribute("f", field);
		req.setAttribute("q", qurry);

		req.getRequestDispatcher("/WEB-INF/board/content/Detail.jsp").forward(req, resp);

	}

}
