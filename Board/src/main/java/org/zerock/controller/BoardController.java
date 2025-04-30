package org.zerock.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	//localhost:8080/board/boardList -> 전체 리스트 목록
	@GetMapping("/boardList")
	public String boardList(Model model) {
		
		List<BoardVO> list = boardService.selectListBoard();
		
		model.addAttribute("boardList", list);
		
		return "boardList";
	}
	
	//localhost:8080/board/register(get) -> 게시글 등록
	@GetMapping("/register")
	public String register() {
		return "boardRegister";
	}
	
	//localhost:8080/board/register(post) -> 게시글 등록화면에서 입력한 데이터를 기반으로 DB 등록
	@PostMapping("/register")
	public String insertBoard(BoardVO vo) {
		//log.info(vo); -> vo 값 홗인용
		
		boardService.insertBoard(vo);
		return "redirect:/board/boardList";
	}
	
	//localhost:8080/board/view(get) -> 게시글 상세 페이지
	@GetMapping("/view")
	public String viewBoard(@RequestParam("num") int num, Model model) {
		//log.info("num >> " + num); -> num값 넘어오는지 확인용
		
		//조회수 증가
		boardService.updateReadCount(num);
		
		//DB에서 num의 전체 데이터 가져와서 vo에 저장
		BoardVO vo = boardService.selectOneByNum(num);
		
		//vo에 저장된 num의 데이터를 board 변수에 담아서 boardView.jsp로 전달
		model.addAttribute("board", vo);	
		
		return "boardView";
	}
	
	//localhost:8080/board/check(get) -> 삭제, 수정시 비밀번호 확인 페이지
	@GetMapping("/check")
	public String checkPass(@RequestParam("num") int num, Model model) {
		//log.info("check >> " + num);
		model.addAttribute("num", num);
		
		return "boardCheck";
	}
	
	//localhost:8080/board/check(post) -> 비밀번호 일치(DB) 여부에 따라 삭제, 수정 실행 / 비밀번호 입력 페이지
	@PostMapping("/check")
	public String checkPost(@RequestParam int num, @RequestParam String pass, Model model) {
		//log.info("check Post >> " + num + " : " + pass);
		
		//Service의 checkPassword() 호출 -> true(비밀번호 일치), false(비밀번호 불일치)
		boolean check = boardService.checkPassword(num, pass);
		
		if(check) {
			//비밀번호 일치
			model.addAttribute("num", num);
			return "checkSuccess";
		}else {
			//비밀번호 불일치
			model.addAttribute("message", "비밀번호가 틀립니다.");
			model.addAttribute("num", num);		//2번 이상 틀릴 경우 num값이 전달되지 않기에 error 방지
			return "boardCheck";
		}
	}
	
	//localhost:8080/board/delete(get) -> 해당 데이터(num) DB에서 삭제 후 "/boardList" 재요청
	@GetMapping("/delete")
	public String deleteGet(@RequestParam int num) {
		boardService.deleteBoard(num);
		return "redirect:/board/boardList";
	}
	
	//localhost:8080/board/update(get) -> 해당 게시글 수정 페이지
	@GetMapping("/update")
	public String updateGet(@RequestParam int num, Model model) {
		//log.info("num >> " + num);
		
		BoardVO vo = boardService.selectOneByNum(num);
		model.addAttribute("board", vo);
		
		return "boardUpdate";
	}
	
	//localhost:8080/board/update(post) -> 수정 페이지에서 입력한 내용을 DB 반영 후 "/view" 재요청(해당 게시글 상세페이지)
	@PostMapping("/update")
	public String updateBoard(BoardVO vo) {
		//log.info(vo);
	
		boardService.updateBoard(vo);
		
		return "redirect:/board/view?num=" + vo.getNum();
	}
	
}

