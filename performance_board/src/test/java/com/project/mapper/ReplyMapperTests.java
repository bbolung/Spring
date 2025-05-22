package com.project.mapper;

import static org.junit.Assert.*;


import java.util.stream.IntStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.project.domain.Criteria;
import com.project.domain.ReplyVO;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class ReplyMapperTests {

	@Autowired
	private ReplyMapper mapper;
	
	public Long[] bnoArr = {1L, 2L, 3L, 4L, 5L};
	
	@Test
	public void testCreate() {
		IntStream.range(1, 10).forEach(i -> {
			ReplyVO vo = ReplyVO.builder()
					.bno(bnoArr[i%5])
					.reply("댓글 Test" + i)
					.replyer("replyer00" + i)
					.build();
		mapper.insert(vo);
		});
	}
	
	@Test
	public void testRead() {
		log.info(mapper.read(1L));
	}
	
	@Test
	public void testDelete() {
		log.info(mapper.delete(15L));
	}
	
	@Test
	public void testUpdate() {
		ReplyVO vo = ReplyVO.builder()
				.reply("댓글 수정")
				.rno(1L)
				.build();
		
		log.info(mapper.update(vo));
	}
	
	@Test
	public void testGetList() {
		Criteria cri = new Criteria();
		Long bno = 2L;
		
		mapper.getListWithPaging(cri, bno)
			.forEach(reply -> log.info(reply));
	}
	
	@Test
	public void testList2() {
		Criteria cri = new Criteria(2,3);
		
		mapper.getListWithPaging(cri, 2L).forEach(list -> log.info(list));
	}
}