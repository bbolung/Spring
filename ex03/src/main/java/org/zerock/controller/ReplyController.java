package org.zerock.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.domain.Criterial;
import org.zerock.domain.ReplyPageDTO;
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
	//전체 목록 조회
	@GetMapping(value = "/pages/{bno}/{page}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ReplyPageDTO> getList(
			@PathVariable("bno") Long bno,
			@PathVariable("page") int page
			){
		log.info("getList.....");
		
		Criterial cri = new Criterial(page, 10);
		
		return new ResponseEntity<>(service.getListPage(cri, bno), HttpStatus.OK);
	}
	
	//단건 데이터 조회
	@GetMapping(value = "/{rno}",
			produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<ReplyVO> get(@PathVariable("rno") Long rno){
		log.info("get....." + rno);
		
		return new ResponseEntity<ReplyVO>(service.get(rno), HttpStatus.OK);
	}
	
	//DELETE 요청 처리
	@DeleteMapping(value = "/{rno}", produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> remove(@PathVariable("rno") Long rno){
		log.info("remove : " + rno);
		
		return service.remove(rno) == 1 ? new ResponseEntity<String>("success", HttpStatus.OK)
										: new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	//PUT, PATCH 요청 처리
	@RequestMapping(method = {RequestMethod.PUT, RequestMethod.PATCH},
					value = "/{rno}",
					consumes = "application/json",		//json 타입
					produces = {MediaType.TEXT_PLAIN_VALUE})	//성공하면 success로 문자열 출력할 것이기에 반환타입 문자열
	public ResponseEntity<String> modify(@RequestBody ReplyVO vo, @PathVariable("rno") Long rno){
		
		vo.setRno(rno);
		log.info("modify : " + rno);
		
		return service.modify(vo) == 1 ? new ResponseEntity<String>("success", HttpStatus.OK)
										: new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
