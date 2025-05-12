package org.zerock.mapper;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criterial;

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
	
	//페이징 처리한 전체 목록 조회
	@Test
	public void testPaggin() {
//		List<BoardVO> list = mapper.getListWithPage(new Criterial(1,10)); -> 1page
//		List<BoardVO> list = mapper.getListWithPage(new Criterial(2,10)); -> 2page
		List<BoardVO> list = mapper.getListWithPage(new Criterial(3,10));  //3apge
		
		list.forEach(board -> log.info(board));
	}
	
	//검색 처리(연습용)
	@Test
	public void testSearch() {
		Map<String, String> map  = new HashMap<String, String>();
		
		map.put("T", "수정 제목");
		map.put("C", "은혜");
		map.put("W", "user");
		
		Map<String, Map<String, String>> outer = new HashMap<>();
		
		outer.put("map", map);
//		log.info(outer.get("map")); -> {C=은혜, T=어버이날}
//		log.info( (outer.get("map")).get("T")); -> 어버이날
		
		List<BoardVO> list = mapper.searchTest(outer);
		
		log.info("---------------");
		log.info(list);
	}
	
	//다중 검색 처리
	@Test
	public void testSearch2() {
		Criterial cri = new Criterial();
		
		cri.setKeyword("수정");
		cri.setType("TW");
		
		mapper.getListWithPage(cri)
		.forEach(board -> log.info(board));
	}
	
	//getTotalCount 테스트
	@Test
	public void testTotalCount() {
		Criterial cri = new Criterial();
		
		cri.setKeyword("수정");
		cri.setType("TW");
		
		log.info("total Count : ");
		mapper.getTotalCount(cri);
	}
}
