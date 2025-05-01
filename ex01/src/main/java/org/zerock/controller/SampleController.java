package org.zerock.controller;


import java.text.SimpleDateFormat;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
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
//localhost:8080/sample/*
@RequestMapping("/sample")
@Log4j
public class SampleController {
	
	/*
	 * 반환타입 : void
	 	/ : /view/sample.jsp
	 	/basic : /view/sample/basic.jsp
	 	/basicOnlyGet : /view/sample/basicOnlyGet.jsp
      -> 반환타입 void인 경우 view 화면은 경로명으로 찾음
     
     * 반환타입 : String
     	/ex01 : /view/ex01.jsp
      -> 반환타입 String인 경우 view 화면은 return 값으로 찾음
      -> 날짜 받는 경우 : @InitBinder/@DateTimeFormat 둘 중 하나 사용
      
     * 반환타입 : 객체
     	
	 */
	
	//localhost:8080/sample/
	// view/sample.jsp
	@RequestMapping("/")
	public void basic() {
		log.info("basic..........");
	}
	
	//localhost:8080/sample/basic
	// view/sample/basic.jsp
	@RequestMapping(value="/basic", method= {
			RequestMethod.GET, RequestMethod.POST})
	public void basicGet() {
		log.info("basic get............");
	}
	
	//localhost:8080/sample/basicOnlyGet
	// view/sample/basicOnlyGet.jsp
	@RequestMapping("/basicOnlyGet")
	public void basicGet2() {
		log.info("basic get only get");
	}
	
	//localhost:8080/sample/ex01?name=kim&age=20
	// view/ex01.jsp
	@GetMapping("/ex01")
	public String ex01(SampleDTO dto) {
		log.info(dto);
		return "ex01";
	}
	
	//localhost:8080/sample/ex02?name=kim&age=20 : 쿼리스트링(문자열로 인식) -> int형 자동 변환
	// view/ex02.jsp + model(name, age)
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
	
	//localhost:8080/sample/ex03?title=spring&dueDate=2025-05-01
	// view/ex03.jsp
	//@InitBinder/@DateTimeFormat 둘 중 하나 사용해야 Error 발생X
	@GetMapping("/ex03")
	public String ex03(TodoDTO dto) {
		log.info(dto);
		return "ex03";
	}
	
	//localhost:8080/sample/ex04?name=kim&age=20&page=5
	// view/sample/ex04.jsp + model(page) + dto(name, age)
	//@RequestParam에 담아서 view 화면에 전달
	@GetMapping("/ex04")
	public String ex04(SampleDTO dto, @RequestParam int page, Model model) {
		log.info(dto);
		log.info(page);
		model.addAttribute("page", page);
		return "sample/ex04";
	}
	
	//객체는 전달되지만 일반 자료형은 전달되지 않기에 @ModelAttribute에 담아서 view 화면에 전달
	@GetMapping("/ex04")
	public String ex04_1(@ModelAttribute("sampleDTO") SampleDTO dto) {
		log.info(dto);
		return "sample/ex04";
	}
	
	//localhost:8080/sample/rttr?name=kim&age20
	// view/rttr.jsp
	//RedirectAttributes : 한번만 전달
	@GetMapping("/rttr")
	public String rttr(SampleDTO dto, RedirectAttributes rttr) {
		rttr.addFlashAttribute("dto", dto);
		
		return "redirect:sample/rttr";
	}
	
	//localhost:8080/sample/ex06
	//@ResponseBody : java객체를 json으로 변환
	@GetMapping("/ex06")
	public @ResponseBody SampleDTO ex06() {
		log.info("/ex06................");
		
		/*
		SampleDTO dto = new SampleDTO();
		dto.setAge(10);
		dto.setName("name");
		return dto;
		*/
		
		//lombok 사용 -> 객체(SampleDTO)에 @Builder 추가
		return SampleDTO.builder()
				.name("park")
				.age(29)
				.build();
	}
	
	//localhost:8080/sample/ex06_1 + json 데이터 전달(postman...사용)
	//@ResponseBody : json객체를 java로 변환
	@GetMapping("/ex06_1")
	public String ex06_1(@RequestBody SampleDTO dto) {
		log.info("/ex06_1................");
		log.info(dto);
		
		return "ex06_1";
	}
	
	//ResponseEntity : json으로 변환하여 반환 + 상태 코드값까지 반환
	//HttpHeaders : 객체 생성하여 웹 + f12 - network에서 Content-type 변경O
	@GetMapping("/ex07")
	public ResponseEntity<String> ex07(){
		
		//{"name" : "홍길동"}
		String msg = "{\"name\" : \"홍길동\"}";
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-type", "application/json");
		return new ResponseEntity<String>(msg, headers, HttpStatus.ACCEPTED);
	}
}
