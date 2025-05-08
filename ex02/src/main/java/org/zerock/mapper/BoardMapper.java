package org.zerock.mapper;

import java.util.List;
import java.util.Map;

import org.zerock.domain.BoardVO;
import org.zerock.domain.Criterial;

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
	
	//페이징 처리한 전체 데이터 조회
	public List<BoardVO> getListWithPage(Criterial cri);
	
	//DB 전체 데이터의 수 가져오기(페이지 버튼)
	public int getTotalCount(Criterial cri);
	
	//다중 조건 검색
	public List<BoardVO> searchTest(Map<String, Map<String, String>> map);
}
