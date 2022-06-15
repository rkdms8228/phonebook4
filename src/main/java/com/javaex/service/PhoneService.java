package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.PhoneDao;
import com.javaex.vo.PhoneVo;

@Service
public class PhoneService {
	
	//필드
	@Autowired
	private PhoneDao phoneDao;
	
	//생성자
	
	
	//메소드-gs
	
	
	//메소드-일반
	//전화번호 리스트
	public List<PhoneVo> getPersonList() {
		
		System.out.println("Servise>getPersonList");
		
		List<PhoneVo> phoneList = phoneDao.getPersonList();
		
		return phoneList;
		
	}
	
	//전화번호 등록
	public int personInsert(PhoneVo phoneVo) {
		
		System.out.println("Servise>personInsert");
		
		int count = phoneDao.personInsert(phoneVo);
		
		return count;
		
	}
	
	//전화번호 삭제
	public int personDelete(int no) {
		
		int count = phoneDao.personDelete(no);
		
		return count;
		
	}
	
	//전화번호 수정폼(정보 가져오기)
	public PhoneVo getPerson(int no) {
		
		PhoneVo phoneVo = phoneDao.getPerson(no);
		
		return phoneVo;
		
	}
	
	//전화번호 수정폼(정보 가져오기)
	public int personUpdate(PhoneVo phoneVo) {
		
		int count = phoneDao.personUpdate(phoneVo);
		
		return count;
		
	}

}
