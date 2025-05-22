package com.project.service;

import com.project.domain.ReviewVO;

public interface ReviewService {
	
	//단건 조회
	public ReviewVO get(Long bno);
	
	//등록
	public void register(ReviewVO vo);
	
	//수정
	public boolean modifiy(ReviewVO vo);
	
	//삭제
	public boolean remove(Long bno);
	
	//조회수 증가
	public void updateReadCount(Long bno);
}