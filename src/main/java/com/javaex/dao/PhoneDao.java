package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.PhoneVo;

@Repository
public class PhoneDao {

	@Autowired
	private SqlSession sqlSession;


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

}