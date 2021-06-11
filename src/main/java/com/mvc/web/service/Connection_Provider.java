package com.mvc.web.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Connection_Provider {

	public static Connection getConnection() throws ClassNotFoundException {
		Connection conn = null;

		try {
			String url = "jdbc:mysql://localhost:3306/study?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
			String id = "root";
			String pass = "1234";
			String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver);
			conn = DriverManager.getConnection(url, id, pass);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	
// 객체 로 rs, result 해서 select,update,delete를 한곳에서 처리가능	

}
