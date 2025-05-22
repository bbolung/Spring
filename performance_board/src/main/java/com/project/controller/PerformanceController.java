package com.project.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.domain.Criteria;
import com.project.domain.PageDTO;
import com.project.domain.PerformanceVO;
import com.project.service.PerformanceService;

import lombok.extern.log4j.Log4j;


@Controller
@RequestMapping("/performance")
@Log4j
public class PerformanceController {
	
	 private final PerformanceService performanceService;

	    @Autowired
	    public PerformanceController(PerformanceService performanceService) {
	        this.performanceService = performanceService;
	    }
	    
	    

	    @GetMapping("/performanceList")
	    public String showList(Criteria cri, Model model) {
	    	
	    	System.out.println(">>>>> /performance/performanceList 요청 들어옴");
	    	
	    	// 1. 페이징 된 목록 불러오기
	        List<PerformanceVO> list = performanceService.getList(cri);
	        list.forEach(vo -> vo.setImgKey(performanceService.extractImgKey(vo.getImage())));
	        ;
	        
	        // 2. 전체 데이터 수
	        int total = performanceService.getTotal(cri);
	        
	        // 3. 모델에 데이터 담기
	        model.addAttribute("performanceList", list); // 현재 페이지 공연 목록
	        model.addAttribute("pageMaker", new PageDTO(cri, performanceService.getTotal(cri))); // 페이징 정보
	        
	        return "performance/performanceList";
	    }
	    
	/*    @GetMapping("/performanceGet")
	    public String getPerformanceDetail(String imgKey, Model model) {
	        System.out.println("imgKey: " + imgKey);
	        PerformanceVO performance = performanceService.findByImgKey(imgKey);
	        System.out.println("performance 객체: " + performance);
	        model.addAttribute("performance", performance);
	        return "performance/performanceGet"; 
	    }*/
	    
	    @GetMapping("/performanceGet")
	    public String getPerformanceDetail(String imgKey, Model model) {
	        System.out.println("imgKey: " + imgKey);
	        PerformanceVO performance = performanceService.getOrSavePerformance(imgKey);
	        System.out.println("performance 객체: " + performance);
	        model.addAttribute("performance", performance);
	        return "performance/performanceGet"; 
	    }
	    
	    
	    

}
