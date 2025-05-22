package com.project.service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.project.domain.ReviewVO;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
	"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
	"file:src/main/webapp/WEB-INF/spring/root-context.xml"
})
@Log4j
public class ReviewServiceImplTests {

	@Autowired
	private ReviewService service;
	
	@Test
	public void testGet() {
		service.get(1L);
	}
	
	@Test
	public void testRegister() {
		ReviewVO vo = ReviewVO.builder()
				.img("no image")
				.title("Test 공연/행사명2")
				.writer("Test writer2")
				.content("Test 내용2")
				.build();
		
		service.register(vo);
	}

}