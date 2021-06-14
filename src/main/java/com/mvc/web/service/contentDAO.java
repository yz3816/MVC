package com.mvc.web.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mvc.web.entity.content.Notice;
import com.mvc.web.entity.content.Picture;
import com.mvc.web.entity.content.etcList;

public class contentDAO {

	private static contentDAO instance = new contentDAO(); // 페이지 실행되자마자 객체 하나를 생성하고 새 객체를 생성하는게 아니라 한번 생성된 얘만 계속옴

	public static contentDAO getInstance() {

		return instance;
	}

	/* 글 목록 */
	public etcList getList(int page, String field, String qurry, String rank) {

		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		int count=0;
		int start = 1 + (page - 1) * 10;
		int end = page * 10;
		
	
		
		String sql1=" select * , (select count(id) as count    "
				+ "			  from tbl_board    "
				+ "			 where (levenshtein(writeID, ?) <= 2)   "
				+ "			   and useFlag ='Y'    "
				+ "			   and board_id in (select board_id    "
				+ "								 from user_auth    "
				+ "								where rankcd= ?)) as count    "
				+ "  from (select @rownum:=@rownum+1 as num ,n.*    "
				+ "		  from( select *    "
				+ "				  from tbl_board    "
				+ "				where (levenshtein(writeID, ?) <= 2)   "
				+ "				   and useFlag ='Y'    "
				+ "				   and board_id in (select board_id    "
				+ "									 from user_auth    "
				+ "					                where rankcd=? )    "
				+ "				order by regdate desc)n,    "
				+ "		(SELECT @rownum:=0)low) num    "
				+ " where num.num between ? and ?; ";		

		String sql2 =  "	select * ,  (select count(id) as count "
		  		+ "	              from tbl_board "
			    + "	           	where "+field+" like ? "
				+ "	           	  and useFlag ='Y' "
				+ "	           	  and board_id in (select boardID "
				+ "	             				    from user_auth "
				+ "	             			       where rankcd= ?)) as count "
				+ "  from (select @rownum:=@rownum+1 as num ,n.* "
				+ "          from( select * "
				+ "	                 from tbl_board "
				+ "				    where "+field+" like ? "
				+ "					  and useFlag ='Y' "
				+ "                   and board_id in (select boardID "
				+ "									    from user_auth "
				+ "									   where rankcd=? ) "
				+ "	      			order by regdate desc)n, "
				+ "		  (SELECT @rownum:=0)low) num "
				+ "  where num.num between ? and ? "; // 조회 sql	
		
		List<Notice> list = new ArrayList<>(); // list 배열 생성
		Notice ns = null;
		
		try {
			con = Connection_Provider.getConnection();
			//field 검색조건이 타이틀일 경우 
			
			if(field.equals("title")) {
				psmt = con.prepareStatement(sql2);
				psmt.setString(1, "%" + qurry + "%");
				psmt.setString(2, rank);
				psmt.setString(3, "%" + qurry + "%");
				psmt.setString(4, rank);
				psmt.setInt(5, start);
				psmt.setInt(6, end);
				
			}else if(field.equals("writeid")) {
				psmt = con.prepareStatement(sql1);
				psmt.setString(1, qurry);
				psmt.setString(2, rank);
				psmt.setString(3, qurry);
				psmt.setString(4, rank);
				psmt.setInt(5, start);
				psmt.setInt(6, end);
				
			}	
			
			rs = psmt.executeQuery();
			while (rs.next()) {
				int id1 = rs.getInt("id");
				String board_id = rs.getString("board_id");
				String title = rs.getString("title");
				String writeid = rs.getString("writeid");
				String content = rs.getString("content");
				Date regdate = rs.getTimestamp("regdate");
				int hit = rs.getInt("hit");
				count = rs.getInt("count");	
				
				System.out.println("카운트 " + count);

				// 조회 된 값을 입력하여 초기화하는 생성자 생성
				ns = new Notice(id1, title, writeid, content, regdate, hit);
				list.add(ns);

				// list 에 조회된 값이 저장된 notice 객체 추가

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			JdbcUtil.close(con);
			JdbcUtil.close(psmt);
			JdbcUtil.close(rs);

		}		

		System.out.println("dao : " + count);
		etcList el = new etcList(count, list);

		return el;
	}

	public Notice getDetail(int no) {

		String sql = " SELECT tb.id, bm.board_name, tb.title, tb.writeid, tb.content, tb.regdate, tb.hit "
				+ "      FROM tbl_board tb, "
				+ "	          board_master bm "
				+ "     WHERE bm.board_id = tb.board_id "
				+ "       AND tb.useFlag = 'Y' "
				+ "       AND tb.id = ?  ";
		Notice ns = null;

		try {

			Connection con = Connection_Provider.getConnection();
			PreparedStatement psmt = con.prepareStatement(sql);
			psmt.setInt(1, no);

			ResultSet rs = psmt.executeQuery();

			if (rs.next()) {
				String board = rs.getString("board_name");
				int id1 = rs.getInt("id");
				String title = rs.getString("title");
				String writeid = rs.getString("writeid");
				String content = rs.getString("content");
				Date regdate = rs.getTimestamp("regdate");
				int hit = rs.getInt("hit");

				// 조회 된 값을 입력하여 초기화하는 생성자 생성
				ns = new Notice(board,id1, title, writeid, content, regdate, hit);
				// list 에 조회된 값이 저장된 notice 객체 추가

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return ns;
	}

	public int getCount(String field, String qurry, String rank) {

		int count = 0;

		String sql = "select count(*) as count " + "     from (select @rownum:=@rownum+1 as num ,n.* "
				+ "             from( select * " + "		                from tbl_board where " + field + " like ? "
				+ " and useFlag ='Y' " + "			        order by regdate desc)n "
				+ "             where (@rownum:=0)=0)num "; // 조회 sql
		List<Notice> list = new ArrayList<>(); // list 배열 생성

		try {

			Connection con = Connection_Provider.getConnection();
			PreparedStatement psmt = con.prepareStatement(sql);
			psmt.setString(1, "%" + qurry + "%");
			ResultSet rs = psmt.executeQuery();

			if (rs.next()) {

				count = rs.getInt("count");

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return count;

	}

	public int write(Notice nc) {

		int result = 0;

		String sql = "insert into tbl_board(writeid,title,content) values(?,?,?)";
		String userID = nc.getWriteid();
		String title_action = nc.getTitle();
		String content_action = nc.getContent();

		List<Notice> list = new ArrayList<>(); // list 배열 생성

		try {

			Connection con = Connection_Provider.getConnection();
			PreparedStatement psmt = con.prepareStatement(sql);
			psmt.setString(1, userID);
			psmt.setString(2, title_action);
			psmt.setString(3, content_action);
			result = psmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public int modify(String title, String content, int id) {

		int result = 0;

		String sql = "update tbl_board set title=?,content=? where id = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {

			conn = Connection_Provider.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			pstmt.setInt(3, id);

			result = pstmt.executeUpdate();

		} catch (Exception e) {

			e.printStackTrace();

		} finally {

			JdbcUtil.close(conn);
			JdbcUtil.close(pstmt);

		}

		return result;

	}

	public void Uphit(int id) {		

		String sql = "update tbl_board set hit=hit+1 where id = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {

			conn = Connection_Provider.getConnection();
			pstmt = conn.prepareStatement(sql);			
			pstmt.setInt(1, id);

			pstmt.executeUpdate();

		} catch (Exception e) {

			e.printStackTrace();

		} finally {

			JdbcUtil.close(conn);
			JdbcUtil.close(pstmt);

		}
		
		
	}

	public List<Picture> getPictureList() {
		
		List<Picture> list = new ArrayList<>();
		String sql = "select * from tbl_picture";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			conn = Connection_Provider.getConnection();
			pstmt = conn.prepareStatement(sql);						
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				int id = rs.getInt("id");
				String ptitle = rs.getString("ptitle");
				String writeid = rs.getString("writeid");
				String path = rs.getString("path");
				Date date = rs.getTimestamp("regdate");
				String useFlag = rs.getString("useFlag");
				
				Picture pt = new Picture(id,ptitle,writeid,path,date,useFlag);
				list.add(pt);
				
			}

		} catch (Exception e) {

			e.printStackTrace();

		} finally {

			JdbcUtil.close(conn);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(rs);

		}

		return list;
	}


}
