package com.mvc.web.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mvc.web.entity.content.Notice;
import com.mvc.web.entity.user.Login;
import com.mvc.web.entity.user.Register;
import com.mvc.web.entity.user.User;

public class userDAO {

	private static userDAO instance = new userDAO(); // 페이지 실행되자마자 객체 하나를 생성하고 새 객체를 생성하는게 아니라 한번 생성된 얘만 계속옴

	public static userDAO getInstance() {

		return instance;
	}

	public User logincheck(Login lg) {

		String sql = "select u_name,u_pass,u_email,u_rank " + " from user " + " where u_flag = 'Y' " + " and u_id=? "
				+ " and u_pass = sha2(?,256) ";

		User ur = new User();

		try {

			Connection con = Connection_Provider.getConnection();
			PreparedStatement psmt = con.prepareStatement(sql);
			psmt.setString(1, lg.getId());
			psmt.setString(2, lg.getPass());
			System.out.println("222222" + lg.getId());
			ResultSet rs = psmt.executeQuery();

			if (rs.next()) { // 조회된 값이 있다면 id pass 일치

				String pass = rs.getString("u_PASS");

				ur.setId(lg.getId());
				ur.setName(rs.getString("u_NAME"));
				ur.setRank(rs.getString("u_rank"));
				ur.setEmail(rs.getString("u_EMAIL"));
				ur.setNumber(1);

			} else {
				System.err.println("아이디가 존재하지 않습니다.");
				ur.setNumber(0); // 조회된 값이 업승ㄹ때 id or pass 불일치 혹은 없음
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return ur;
	}

	// idcheck
	public int idcheck(String id) {

		int result = 0;

		String sql = "select u_id from user where u_id = ?";

		try {

			Connection conn = Connection_Provider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				result = 1;
			} else {
				result = 0;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	// 사용자 추가
	public int signup(Register rt) {

		String sql = "insert into user(u_ID,u_PASS,u_NAME,u_EMAIL) values(?,SHA2(?,256),?,?)";
		int result = 0;

		try {

			Connection conn = Connection_Provider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, rt.getId());
			pstmt.setString(2, rt.getPass());
			pstmt.setString(3, rt.getName());
			pstmt.setString(4, rt.getEmail());			

			result = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public void setLoginInfo(String pid, String userIP) {
		

		String sql = "insert into user_swap(userID,userIP,outtime,division) values(?,?,?,?)";
		int result = 0;
		
		try {

			Connection conn = Connection_Provider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pid);
			pstmt.setString(2, userIP);
			pstmt.setString(3, null);
			pstmt.setString(4, "i");			

			result = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}	
		
	}

}
