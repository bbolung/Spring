package org.zerock.repository;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.dto.BoardVO;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		{
			"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
			"file:src/main/webapp/WEB-INF/spring/root-context.xml"
		}
)
@Log4j
public class BoardRepositoryTests {
	
	@Autowired
	private BoardRepository boardRepository;
	
	@Test
	public void test() {
		log.info("boardRepository >> " + boardRepository);
	}
	
	@Test
	public void selectAlltest() {
		List<BoardVO> list = boardRepository.selectAllBoards();
		
		for(BoardVO vo : list)
			log.info(vo);
		
		log.info("--------------------------");
		
		//for문과 동일
		boardRepository.selectAllBoards().forEach(board -> log.info(board));
	}

	@Test
	public void selectOneByNumTest() {
		
		//num = 1인 데이터 정보 조회
		BoardVO vo = boardRepository.selectOneByNum(1);
		log.info("vo >> "  + vo);
	}

	@Test
	public void insertBoardTest() {
		//test용으로 임의로 vo의 데이터 작성
		BoardVO vo = new BoardVO();
		
		vo.setName("이름");
		vo.setPass("암호");
		vo.setEmail("test@test.com");
		vo.setTitle("타이틀");
		vo.setContent("내용");
		
		boardRepository.insertBoard(vo);
	}
	
	@Test
	public void updateBoardTest() {
		//test용으로 임의로 vo의 데이터 작성
		BoardVO vo = new BoardVO();
		
		vo.setNum(41);		//num값으로 데이터 조회하여 변경하기에 반드시 작성!
		vo.setName("이름");
		vo.setPass("암호");
		vo.setEmail("test@test.com");
		vo.setTitle("타이틀2");
		vo.setContent("내용2");
		
		boardRepository.updateBoard(vo);
	}
	
	@Test
	public void deleteBoardTest() {
		//test를 위해 임의의 num 지정
		boardRepository.deleteBoard(41);
	}
	
	@Test
	public void updateReadCountTest() {
		boardRepository.updateReadCount(1);
	}
}

