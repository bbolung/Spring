package com.project.mapper;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.project.domain.ReviewVO;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class ReviewMapperTests {
	
	@Autowired
	private ReviewMapper mapper;
	
	@Test
	public void testRead() {
		log.info(mapper.read(1L));
	}
	
	@Test
	public void testInsertKey() {
		ReviewVO vo = ReviewVO.builder()
				.img("no image")
				.title("Test 공연/행사명2")
				.writer("Test writer2")
				.content("Test 내용2")
				.build();
		
		mapper.insertSelectKey(vo);
	}
	
	@Test
	public void testDelete() {
		int result = mapper.delete(25L);
		log.info("Delete Result>> " + result);
	}
	
	@Test
	public void testUpdate() {
		ReviewVO vo = ReviewVO.builder()
				.img("new image")
				.title("Test New 공연/행사명")
				.content("Test New 내용")
				.bno(24L)
				.build();
		
		int result = mapper.update(vo);
		log.info("Update Result>> " + result);
	}
	
	@Test
	public void updateReadCount() {
		mapper.updateReadCount(24L);
	}

}