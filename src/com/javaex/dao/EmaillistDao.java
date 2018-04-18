package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javaex.vo.EmailVO;

public class EmaillistDao {

	public void insert(EmailVO vo) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		int count;

		try {
			//
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			// 3.SQL문 준비/바인딩/실행
			String sql = "INSERT INTO EMAILLIST values( SEQ_EMAILLIST_NO.NEXTVAL,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getLastName());
			pstmt.setString(2, vo.getFirstName());
			pstmt.setString(3, vo.getEmail());

			count = pstmt.executeUpdate();
			System.out.println(count + "건 등록");

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
			// 자세하게 메시지를 처리하고 싶을때
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public List<EmailVO> getList() {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		List<EmailVO> list = new ArrayList<EmailVO>();

		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 2.Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			// 3.SQL문 준비/바인딩/실행
			String sql = "SELECT NO, LAST_NAME, FIRST_NAME, EMAIL" + " FROM EMAILLIST" + " ORDER BY NO DESC";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			// 4.결과처리
			while (rs.next()) {
				int no = rs.getInt("NO");
				String lastName = rs.getString("LAST_NAME");
				String firstName = rs.getString("FIRST_NAME");
				String Email = rs.getString("EMAIL");
				EmailVO vo = new EmailVO(no, Email, firstName, Email);
				list.add(vo);
			}

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
			// 자세하게 메시지를 처리하고 싶을때
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;

	}

}
