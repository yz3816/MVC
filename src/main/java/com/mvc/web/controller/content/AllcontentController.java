package com.mvc.web.controller.content;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mvc.web.entity.content.Notice;
import com.mvc.web.entity.content.etcList;
import com.mvc.web.service.contentDAO;

@WebServlet("/board/content/allcontent")
public class AllcontentController extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String userNm = (req.getSession().getAttribute("userNm")).toString();
		String userRank = req.getSession().getAttribute("userRank").toString();
		String userID = req.getSession().getAttribute("userID").toString();

		// 검색

		int page = 1;
		String page_ = req.getParameter("p");

		if (page_ != null && !page_.equals("")) {
			page = Integer.parseInt(page_);
		}

		String qurry_ = req.getParameter("q");
		String qurry = "";

		if (qurry_ != null && !qurry_.equals("")) {
			qurry = qurry_;
		}


		// int count = contentDAO.getInstance().getCount(field, qurry,userRank);

		// req.setAttribute("count", count);

		etcList el = contentDAO.getInstance().getAllContentList(page, qurry, userRank);
		List<Notice> list = el.getNs();
		int count = el.getCount();


		req.setAttribute("name", userNm);
		req.setAttribute("list", list);
		req.setAttribute("count", count);


		req.getRequestDispatcher("/WEB-INF/board/content/AllContentList.jsp").forward(req, resp);

	}

	

}
