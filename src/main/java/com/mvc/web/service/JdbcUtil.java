package com.mvc.web.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class JdbcUtil {

	public static void close(Connection conn) {

		if (conn != null) {
			try {

				conn.close();

			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

	public static void close(PreparedStatement psmt) {

		if (psmt != null) {
			try {

				psmt.close();

			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

	public static void close(ResultSet rs) {

		if (rs != null) {
			try {

				rs.close();

			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

}
