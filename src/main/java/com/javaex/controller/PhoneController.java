package com.javaex.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.PhoneService;
import com.javaex.vo.PhoneVo;

@Controller
public class PhoneController {
	
	//필드
	@Autowired
	private PhoneService phoneService; // = new PhoneDao(); (잦은 사용으로 공통으로 빼기)
	
	//생성자
	
	
	//메소드-gs
	
	
	//메소드-일반
	//전화번호 리스트
	@RequestMapping(value="/list", method={RequestMethod.GET, RequestMethod.POST})
	public String list(Model model) {
		
		System.out.println("PhoneController>list()");
		
		//service를 통해서 phonelist(주소)를 가져온다
		//PhoneDao phoneDao = new PhoneDao();
		List<PhoneVo> phoneList = phoneService.getPersonList();
		
		//ds 데이터 보내기 --> request attribute에 넣는다
		model.addAttribute("phoneList", phoneList);
		
		return "list";
		
	}
	
	//전화번호 등록폼
	@RequestMapping(value="/writeForm", method={RequestMethod.GET, RequestMethod.POST})
	public String writeForm() {
		
		System.out.println("PhoneController>writeForm()");
		
		return "writeForm";
		
	}
	
	//전화번호 등록폼
	@RequestMapping(value="/writeForm", method={RequestMethod.GET, RequestMethod.POST})
	public String writeForm2() {
		
		System.out.println("PhoneController>writeForm2()");
		
		return "writeForm";
		
	}
	
	//전화번호 등록
	@RequestMapping(value="/write", method={RequestMethod.GET, RequestMethod.POST})
	public String write(@ModelAttribute PhoneVo phoneVo) {
		
		System.out.println("PhoneController>write()");
		
		
		/* *** 디스페쳐 서블릿(DS)이 해주는 것들 ***
		//파라미터 꺼내기
		//System.out.println(name);
		//System.out.println(hp);
		//System.out.println(company);
		
		//vo로 묶기
		//PhoneVo phoneVo = new PhoneVo(name, hp, company);
		*/
		
		System.out.println(phoneVo);
		
		//service로 저장하기
		//PhoneDao phoneDao = new PhoneDao();
		int count = phoneService.personInsert2();
		System.out.println(count);
		
		return "redirect:/list";
		//return "redirect:http://localhost:8088/phonebook3/list";
		
	}
	
	//전화번호 등록
	@RequestMapping(value="/write2", method={RequestMethod.GET, RequestMethod.POST})
	public String write2(@RequestParam("name") String name,
						 @RequestParam("hp") String hp,
						 @RequestParam("company") String company) {
		
		System.out.println("PhoneController>write2()");
		
		//파라미터 꺼내기
		//System.out.println(name);
		//System.out.println(hp);
		//System.out.println(company);
		
		//vo로 묶기
		PhoneVo phoneVo = new PhoneVo(name, hp, company);
		System.out.println(phoneVo);
		
		//dao로 저장하기
		//PhoneDao phoneDao = new PhoneDao();
		int count = phoneService.personInsert(phoneVo);
		System.out.println(count);
		
		return "redirect:http://localhost:8088/phonebook3/list";
		
	}
	
	//전화번호 수정폼
	@RequestMapping(value="/modifyForm", method={RequestMethod.GET, RequestMethod.POST})
	public String modifyForm(@RequestParam("no") int no, Model model) {
		
		System.out.println("PhoneController>modifyForm()");
		
		//dao(수정)
		//PhoneDao phoneDao = new PhoneDao();
		PhoneVo phoneVo = phoneService.getPerson(no);
		System.out.println(phoneVo);
		
		model.addAttribute("phoneVo", phoneVo);
		
		return "modifyForm";
		
	}
	
	//전화번호 수정폼
	@RequestMapping(value="/modifyForm2", method={RequestMethod.GET, RequestMethod.POST})
	public String modifyForm2(Model model, @RequestParam("no") int no) {
		
		System.out.println("PhoneController>modifyForm2()");
		
		Map<String, Object> pMap = phoneService.getPerson2(no);
		model.addAttribute("pMap", pMap);

		return "modifyForm2";
		
	}
	
	//전화번호 수정
	@RequestMapping(value="/modify", method={RequestMethod.GET, RequestMethod.POST})
	public String modify(@ModelAttribute PhoneVo phoneVo) {
		
		System.out.println("PhoneController>modify()");
		
		System.out.println(phoneVo);
		
		//dao로 저장하기
		//PhoneDao phoneDao = new PhoneDao();
		int count = phoneService.personUpdate(phoneVo);
		System.out.println(count);
		
		return "redirect:/list";
		
	}
	
	//전화번호 삭제
	@RequestMapping(value="/delete/{no}", method={RequestMethod.GET, RequestMethod.POST})
	public String delete(@PathVariable("no") int no) {
		
		System.out.println("PhoneController>delete()");
		
		//파라미터 꺼내기
		System.out.println(no);
		
		//dao로 처리하기(삭제)
		//PhoneDao phoneDao = new PhoneDao();
		int count = phoneService.personDelete(no);
		System.out.println(count);
		
		return "redirect:/list";
		
	}
	
	//전화번호 삭제
	@RequestMapping(value="/delete2", method={RequestMethod.GET, RequestMethod.POST})
	public String delete2(@RequestParam("no") int num) {
		
		System.out.println("PhoneController>delete2()");
		
		//파라미터 꺼내기
		System.out.println(num);
		
		//dao로 처리하기(삭제)
		//PhoneDao phoneDao = new PhoneDao();
		int count = phoneService.personDelete(num);
		System.out.println(count);
		
		return "redirect:/list";
		
	}
	
	//test메소드
	@RequestMapping(value="/test", method={RequestMethod.GET, RequestMethod.POST})
	public String test() {
		
		System.out.println("PhoneController>test()");
		
		//다오
		return "test";
		
	}
	
		//등록메소드
		//수정메소드
		//삭제메소드
		//리스트메소드
	

}
