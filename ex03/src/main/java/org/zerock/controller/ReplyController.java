package org.zerock.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.domain.Criterial;
import org.zerock.domain.ReplyVO;
import org.zerock.service.ReplyService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@RestController
@RequiredArgsConstructor
@RequestMapping("/replies")
@Log4j
public class ReplyController {
	
	private final ReplyService service;
	
	@PostMapping(value = "/new")
	public ResponseEntity<String> create(@RequestBody ReplyVO vo){
		log.info("ReplyVO : " + vo);
		int insertCount = service.register(vo);
		
		if(insertCount == 1) {
			//저장 성공 -> 200
			return new ResponseEntity<>("success", HttpStatus.OK);
		}else { 
			//저장 실패 -> 500(서버 문제)
			return new ResponseEntity<>("success", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	//produces 생략O, xml 방식으로 받지X, json만 사용
	//전달받은 값 + HTTP 상태 200 전달
	@GetMapping(value = "/pages/{bno}/{page}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ReplyVO>> getList(
			@PathVariable("bno") Long bno,
			@PathVariable("page") int page
			){
		log.info("getList.....");
		
		Criterial cri = new Criterial(page, 10);
		
		return new ResponseEntity<>(service.getList(cri, bno), HttpStatus.OK);
	}
}
