package org.zerock.controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
	"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
	"file:src/main/webapp/WEB-INF/spring/root-context.xml"
})
@Log4j
@WebAppConfiguration	//Server 실행하지 않고 구동하기 위해서 필요
public class BoardControllerTests {
	
	@Autowired		//웹 관련 bean(생성된 객체) 관리
	private WebApplicationContext ctx;
	
	//서버를 실행하지 않고도 HTTP 요청과 응답을 시뮬레이션하기 위한 도구 
	private MockMvc mockMvc;
	
	//Spring MVC 애플리케이션에서 통합 테스트 수행 (실제 서버 실행X, Controller 동작 Test 가능)
	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
	}
	
	@Test
	public void testList() throws Exception {
		log.info(
				mockMvc.perform(MockMvcRequestBuilders.get("/board/list"))
				.andReturn()
				.getModelAndView()
				.getModelMap()
		);
	}
	
	@Test
	public void testRegister() throws Exception {
		String resultPage =  mockMvc.perform(MockMvcRequestBuilders
				.post("/board/register")
				.param("title", "테스트 새글 제목")
				.param("content", "테스트 새글 내용")
				.param("writer", "테스트 새글 작성자")				
				).andReturn()
				.getModelAndView()
				.getViewName();
		
		log.info("resultPage >> " + resultPage);
	}
	
	@Test
	public void testGet() throws Exception {
		log.info(
				mockMvc.perform(MockMvcRequestBuilders
				.get("/board/get")
				.param("bno", "1"))			
				.andReturn()
				.getModelAndView()
				.getModelMap()
		);
	}
	
	@Test
	public void testDelete() throws Exception {
		String resultPage =  mockMvc.perform(MockMvcRequestBuilders
				.post("/board/remove")
				.param("bno", "14")				
				).andReturn()
				.getModelAndView()
				.getViewName();
		
		log.info("resultPage >> " + resultPage);
	}
	
	@Test
	public void testModify() throws Exception {
		String resultPage =  mockMvc.perform(MockMvcRequestBuilders
				.post("/board/modify")
				.param("title", "수정 글 제목")
				.param("content", "수정 글 내용")
				.param("writer", "수정 글 작성자")				
				.param("bno", "13")				
				).andReturn()
				.getModelAndView()
				.getViewName();
		
		log.info("resultPage >> " + resultPage);
	}

}
