package org.zerock.mapper;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.BoardVO;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardMapperTests {
	
	@Autowired
	private BoardMapper mapper;
	
	@Test
	public void testRead() {
		log.info(mapper.read(1L));
	}
	
	//전체 데이터 조회하여 List로 출력
	@Test
	public void testGetList() {
		//list = sql에서 조회한 전체 데이터
		List<BoardVO> list = mapper.getList();
		
		//BoardVO 타입인 list를 하나씩 출력하는 for문
		for(BoardVO vo : list) {
			log.info(vo);
		}
	}
	
	//DB에 입력받은 데이터 저장(bno값 모름)
	@Test
	public void testInsert() {
		BoardVO vo = BoardVO.builder()
				.title("test title")
				.content("test content")
				.writer("test00")
				.build();
		
		mapper.insert(vo);
	}
	
	//DB에 입력받은 데이터 저장(Key값 bno 확인O)
	@Test
	public void testInsertKey() {
		BoardVO vo = BoardVO.builder()
				.title("test title")
				.content("test content")
				.writer("test00")
				.build();
		
		mapper.insertSelectKey(vo);
	}
	
	//DB에서 해당 데이터(bno) 삭제
	@Test
	public void testDelete() {
		int result = mapper.delete(8L);
		log.info("result >> " +result);		//result = 1 (성공), 0(실패)		
	}
	
	//DB에서 해당 데이터(bno) 변경
	@Test
	public void testUpdate() {
		BoardVO vo = BoardVO.builder()
				.title("수정 제목")
				.content("수정 내용")
				.writer("update00")
				.bno(6L)
				.build();
		
		int result = mapper.update(vo);
		log.info("result >> " + result); 	//result = 1 (성공), 0(실패)
	}
}
