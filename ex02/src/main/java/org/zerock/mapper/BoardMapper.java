package org.zerock.mapper;

import java.util.List;

import org.zerock.domain.BoardVO;

public interface BoardMapper {
	
	public BoardVO read(Long bno);
	
	//xml에 작성된 전체 데이터 조회하기 위한 메서드 선언
	public List<BoardVO> getList();
	
	//DB에 데이터 저장
	public void insert(BoardVO board);
	
	//DB에 저장된 데이터 key 확인 (대부분의 insert 이렇게 사용)
	public void insertSelectKey(BoardVO board);
	
	//DB의 데이터 삭제
	public int delete(Long bno);
	
	//DB의 데이터 변경
	public int update(BoardVO board);
}
