package com.mvc.web.controller.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mvc.web.entity.user.Login;
import com.mvc.web.entity.user.User;
import com.mvc.web.service.userDAO;
import com.mysql.cj.Session;

@WebServlet("/user/login")
public class LoginController extends HttpServlet {

	private static final Integer cookieExpire = 60 * 60 * 24 * 30; // 1month 초 분 시 일

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html; charset=UTF-8");
		
		String pid = req.getParameter("id");
		String ppass = req.getParameter("pass");
		String remember = req.getParameter("remember");

		Login lg = new Login(pid, ppass);

		User ur = (userDAO.getInstance().logincheck(lg));

		int result = ur.getNumber();
		
		// 클라이언트 ip 추출
		String userIP = req.getHeader("X-Forwarded-For");
		if(userIP==null) {
			userIP=req.getRemoteAddr();
		}
		
		// 접속한 인원 테이블에 접속자 정보 입력
		
		userDAO.getInstance().setLoginInfo(pid,userIP);

		switch (result) {
		case 1:
			// 로그인 성공
			// 리스트로 보낸다
			String userID = ur.getId();
			String userNm = ur.getName();
			String userRank = ur.getRank();
			String useremail = ur.getEmail();

			HttpSession session = req.getSession();
			session.setAttribute("userID", userID);
			session.setAttribute("userNm", userNm);
			session.setAttribute("userRank", userRank);
			session.setAttribute("useremail", useremail);

			if (remember != null) {
				setCookie("sid", pid, resp);
			} else {
				setCookie("sid", "", resp);
			}

			System.out.println("세션 : " + (req.getSession().getAttribute("userID")).toString());
			System.out.println("세션 : " + (req.getSession().getAttribute("userNm")).toString());
			System.out.println("세션 : " + (req.getSession().getAttribute("userRank")).toString());
			System.out.println("세션 : " + (req.getSession().getAttribute("useremail")).toString());

			resp.sendRedirect("/board/content/list");

			break;
		case -1:
			// 로그인 화면 그대로
			req.setAttribute("ment", "패스워드 불일치");
			doGet(req, resp);
			break;
		case 0:
			// 로그인 화면 그대로
			req.setAttribute("ment", "아이디 불일치");
			doGet(req, resp);
			break;
		}

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String userID = get_Cookie("sid", req);
		req.setAttribute("id", userID);

		req.getRequestDispatcher("/WEB-INF/board/user/Login.jsp").forward(req, resp);

	}

	private String get_Cookie(String cid, HttpServletRequest req) {

		String ret = "";

		if (ret == null) {
			return ret;
		}

		Cookie[] cookies = req.getCookies();

		if (cookies == null) {
			return ret;
		}

		for (Cookie ck : cookies) {
			if (ck.getName().equals(cid)) {

				ret = ck.getValue();
				ck.setMaxAge(cookieExpire);
				System.out.println("ck name : " + ck.getName());
				System.out.println("ck value : " + ck.getValue());
				System.out.println("ck ret : " + ret);
				break;
			}
		}

		return ret;
	}

	private void setCookie(String cid, String pid, HttpServletResponse resp) {

		Cookie ck = new Cookie(cid, pid);
		ck.setPath("/");
		ck.setMaxAge(cookieExpire);
		resp.addCookie(ck);

	}

}
