package org.zerock.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.dto.BoardVO;
import org.zerock.service.BoardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

/*
  /board/boardList : 전체 데이터 반환
  /board/view : 상세 페이지  
 */

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
@Log4j
public class BoardController {
	
	private final BoardService boardService;
	
	//임시 확인용
	@GetMapping("/list")
	public List<BoardVO> List(Model model) {
		log.info("-----list-----");
		return boardService.selectListBoard();
	}
	
	@GetMapping("/boardList")
	public String boardList(Model model) {
		
		List<BoardVO> list = boardService.selectListBoard();
		
		model.addAttribute("boardList", list);
		
		return "boardList";
	}
	
	@GetMapping("/register")
	public String register() {
		return "register";
	}
	
	@PostMapping("/register")
	public String insertBoard(BoardVO vo) {
		//log.info(vo); -> vo 값 홗인용
		boardService.insetBoard(vo);
		return "redirect:/board/boardList";
	}
}

