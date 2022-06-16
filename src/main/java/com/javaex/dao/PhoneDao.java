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
	
	//사람 삭제
	public int personDelete(int personId) {
		
		
		System.out.println("Dao>personDelete");
		int count = sqlSession.delete("phonebook.personDelete", personId);
		System.out.println(count);
		
		return count;
		
	}
	
	//사람 수정
	public int personUpdate(PhoneVo phoneVo) {
		
		System.out.println("Dao>personUpdate");
		int count = sqlSession.update("phonebook.personUpdate", phoneVo);
		System.out.println(count);
		
		return count;
		
	}
	
	//1명 정보 가져오기
	public PhoneVo getPerson(int personId) {
		
		System.out.println("Dao>getPerson");
		PhoneVo phoneVo = sqlSession.selectOne("phonebook.getPerson", personId);
		System.out.println(phoneVo);
		
		return phoneVo;
		
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


	// 1명 정보 가져오기
	public PhoneVo getPerson2(int personId) {
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



}