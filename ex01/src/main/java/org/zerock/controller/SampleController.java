package org.zerock.controller;


import java.text.SimpleDateFormat;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.domain.SampleDTO;
import org.zerock.domain.TodoDTO;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/sample")
@Log4j
public class SampleController {
	
	@RequestMapping("/")
	public void basic() {
		log.info("basic..........");
	}
	
	@RequestMapping(value="/basic", method= {
			RequestMethod.GET, RequestMethod.POST})
	public void basicGet() {
		log.info("basic get............");
	}
	
	@RequestMapping("/basicOnlyGet")
	public void basicGet2() {
		log.info("basic get only get");
	}
	
	@GetMapping("/ex01")
	public String ex01(SampleDTO dto) {
		log.info(dto);
		return "ex01";
	}
	
	@GetMapping("/ex02")
	public String ex02(@RequestParam String name, @RequestParam int age, Model model) {
		log.info(name);
		log.info(age);
		model.addAttribute("name", name);
		model.addAttribute("age", age);
		return "ex02";
	}
	
	/*
		@InitBinder
		public void initBinder(WebDataBinder binder) {
			SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd");
			binder.registerCustomEditor(java.util.Date.class,
					new CustomDateEditor(dataFormat, false));
		}
	*/
	
	@GetMapping("/ex03")
	public String ex03(TodoDTO dto) {
		log.info(dto);
		return "ex03";
	}
	
	@GetMapping("/ex04")
	public String ex04(SampleDTO dto, @RequestParam int page, Model model) {
		log.info(dto);
		log.info(page);
		model.addAttribute("page", page);
		return "sample/ex04";
	}
	
	@GetMapping("/rttr")
	public String rttr(SampleDTO dto, RedirectAttributes rttr) {
		rttr.addFlashAttribute("dto", dto);
		
		return "redirect:sample/rttr";
	}
	
	@GetMapping("/ex06")
	//@ResponseBody : java객체를 json으로 변환
	public @ResponseBody SampleDTO ex06() {
		log.info("/ex06................");
		SampleDTO dto = new SampleDTO();
		dto.setAge(10);
		dto.setName("name");
		
		return dto;
	}
	
	@GetMapping("/ex06_1")
	//@ResponseBody : json객체를 java로 변환
	public String ex06_1(@RequestBody SampleDTO dto) {
		log.info("/ex06_1................");
		log.info(dto);
		
		return "ex06_1";
	}
	
	@GetMapping("/ex07")
	//ResponseEntity : json으로 변환하여 반환 + 상태 코드값까지 반환
	//HttpHeaders : 객체 생성하여 웹 + f12 - network에서 Content-type 변경O
	public ResponseEntity<String> ex07(){
		
		//{"name" : "홍길동"}
		String msg = "{\"name\" : \"홍길동\"}";
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-type", "application/json");
		return new ResponseEntity<String>(msg, headers, HttpStatus.ACCEPTED);
	}
}
