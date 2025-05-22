package com.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.domain.Criteria;
import com.project.domain.ReviewVO;
import com.project.service.ReviewService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/review")
@RequiredArgsConstructor
@Log4j
public class ReviewController {
	
	private final ReviewService service;
	
	//목록 페이지
	@GetMapping("/reviewList")
	public void list(Criteria cri, Model model) {
		
	}
	
	@PostMapping("/reviewRegister")
	public String register(ReviewVO vo) {
		log.info("register...");
		
		service.register(vo);
		
		return "redirect:/review/reviewList";
	}
	
	@GetMapping({"/reviewGet", "/reviewModify"})
	public void getReview(@RequestParam Long bno, Criteria cri, Model model) {
		log.info("get...modify...");
		
		model.addAttribute("review", service.get(bno));
		model.addAttribute("cri", cri);
	}
	
	@PostMapping("/reviewRemove")
	public String remove(Long bno, RedirectAttributes rttr) {
		log.info("remove...");
		
		service.remove(bno);
		
		rttr.addFlashAttribute("result", "삭제 성공했습니다.");
		
		return "redirect:/review/reviewList";
	}
	
	@PostMapping("/reviewModify")
	public String modify(ReviewVO vo, RedirectAttributes rttr) {
		log.info("modify...");
		
		service.modifiy(vo);
		
		rttr.addFlashAttribute("result", "수정 성공했습니다.");
		
		return "redirect:/review/reviewList";
	}
	
}