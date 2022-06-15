package com.javaex.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.PhoneVo;

@Repository
public class PhoneDao {

	@Autowired
	private SqlSession sqlSession;

	@Autowired
	private DataSource dataSource;

	// 0. import java.sql.*;
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String id = "phonedb";
	private String pw = "phonedb";

	//리스트 불러오기
	public List<PhoneVo> getPersonList() {

		System.out.println("Dao>getPersonList");
		List<PhoneVo> phoneList = sqlSession.selectList("phonebook.selectList");
		System.out.println(phoneList);

		return phoneList;
	}
	
	// 사람 추가
	public int personInsert(PhoneVo phoneVo) {
		
		System.out.println("Dao>personInsert");
		int count = sqlSession.insert("phonebook.personInsert", phoneVo);
		System.out.println(count);
		
		return count;
		
	}

	private void getConnection() {
		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			// Class.forName(driver);

			// 2. Connection 얻어오기
			// conn = DriverManager.getConnection(url, id, pw);
			// System.out.println("접속성공");
			conn = dataSource.getConnection();

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
	}

	private void close() {
		// 5. 자원정리
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
			System.out.println("error:" + e);
		}
	}

	

	// 사람 삭제
	public int personDelete(int personId) {
		int count = 0;
		getConnection();

		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = ""; // 쿼리문 문자열만들기, ? 주의
			query += " delete from person ";
			query += " where person_id = ? ";
			pstmt = conn.prepareStatement(query); // 쿼리로 만들기

			pstmt.setInt(1, personId);// ?(물음표) 중 1번째, 순서중요

			count = pstmt.executeUpdate(); // 쿼리문 실행

			// 4.결과처리
			System.out.println(count + "건 삭제되었습니다.");

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		close();
		return count;
	}

	// 1명 정보 가져오기
	public PhoneVo getPerson(int personId) {
		PhoneVo phoneVo = null;

		this.getConnection();

		try {

			// 3. SQL문 준비 / 바인딩 / 실행
			// SQL문 준비
			String query = "";
			query += " select  person_id, ";
			query += "         name, ";
			query += "         hp, ";
			query += "         company ";
			query += " from person ";
			query += " where person_id = ? ";

			// 바인딩
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, personId);

			// 실행
			rs = pstmt.executeQuery();

			// 4.결과처리
			while (rs.next()) {

				int id = rs.getInt("person_id");
				String name = rs.getString("name");
				String hp = rs.getString("hp");
				String company = rs.getString("company");

				phoneVo = new PhoneVo(id, name, hp, company);
			}

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		this.close();

		return phoneVo;
	}

	// 사람 수정
	public int personUpdate(PhoneVo phoneVo) {
		int count = 0;
		getConnection();

		try {

			// 3. SQL문 준비 / 바인딩 / 실행
			String query = ""; // 쿼리문 문자열만들기, ? 주의
			query += " update person ";
			query += " set name = ? , ";
			query += "     hp = ? , ";
			query += "     company = ? ";
			query += " where person_id = ? ";

			pstmt = conn.prepareStatement(query); // 쿼리로 만들기

			pstmt.setString(1, phoneVo.getName()); // ?(물음표) 중 1번째, 순서중요
			pstmt.setString(2, phoneVo.getHp()); // ?(물음표) 중 2번째, 순서중요
			pstmt.setString(3, phoneVo.getCompany()); // ?(물음표) 중 3번째, 순서중요
			pstmt.setInt(4, phoneVo.getPersonId()); // ?(물음표) 중 4번째, 순서중요

			count = pstmt.executeUpdate(); // 쿼리문 실행

			// 4.결과처리
			System.out.println(count + "건 수정되었습니다.");

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		close();
		return count;
	}

}