package org.zerock.mapper;

import static org.junit.Assert.*;

import java.util.stream.IntStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.Criterial;
import org.zerock.domain.ReplyVO;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class ReplyMapperTests {

	@Autowired
	private ReplyMapper mapper;
	
	public Long[] bnoArr = {
			786442L,786441L,786440L, 786439L, 786438L
	};
	
	@Test
	public void testCreate() {
		IntStream.range(1, 10).forEach(i -> {
			ReplyVO vo = ReplyVO.builder()
					.bno(bnoArr[i%5])
					.reply("댓글 테스트" + i)
					.replyer("replyer"+i)
					.build();
			mapper.insert(vo);
		});
	}
	
	@Test
	public void testRead() {
		log.info(mapper.read(9L));
	}
	
	//성공하면 console에 1 출력, 실패 0 출력
	@Test
	public void testDelete() {
		log.info(mapper.delete(9L));
	}
	
	//성공하면 console에 1 출력, 실패 0 출력
	@Test
	public void testUpdate() {
		ReplyVO vo = ReplyVO.builder()
				.reply("댓글 수정내용")
				.rno(8L)
				.build();
		
		log.info(mapper.update(vo));
	}
	
	@Test
	public void testGetList() {
		Criterial cri = new Criterial();
		Long bno = 786442L;
		
		mapper.getListWithPaging(cri, bno)
			.forEach(reply -> log.info(reply));
	}
}
