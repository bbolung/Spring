package org.zerock.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criterial;
import org.zerock.mapper.BoardMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Service
@Log4j
@RequiredArgsConstructor	//생성자 주입
public class BoardServiceImpl implements BoardService {
	
	//@RequiredArgsConstructor + final => 객체 주입
	private final BoardMapper mapper;
	
	@Override
	public void register(BoardVO board) {
		log.info("register....." + board);
		mapper.insertSelectKey(board);
	}

	@Override
	public BoardVO get(Long bno) {
		log.info("get....." + bno);
		return mapper.read(bno);
	}

	@Override
	public boolean modify(BoardVO board) {
		log.info("modify.....");
		return mapper.update(board) == 1;		//mapper.update(board) 성공할 경우 = 1 -> return true;
	}

	@Override
	public boolean remove(Long bno) {
		log.info("remove.....");
		return mapper.delete(bno) == 1;
	}

	@Override
	public List<BoardVO> getList(Criterial cri) {
		log.info("getList....");
		return mapper.getListWithPage(cri);
	}

	@Override
	public int getTotal(Criterial cri) {
		log.info("getTotal...");
		
		return mapper.getTotalCount(cri);
	}
}
