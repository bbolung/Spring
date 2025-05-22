package com.project.service;

import java.util.List;

import com.project.domain.Criteria;
import com.project.domain.PerformanceVO;

public interface PerformanceService {
	List<PerformanceVO> fetchPerformances(); // 공공데이터 전체리스트 가져오기
	
	String extractImgKey(String imageUrl);

	PerformanceVO findByImgKey(String imgKey);
	
	PerformanceVO getOrSavePerformance(String imgKey);
	
	// 페이징 처리용 메서드 추가
	List<PerformanceVO> getList(Criteria cri);
	
	// 전체 레코드 조회 or 조건에 맞는 데이터 조회
    int getTotal(Criteria cri);
}
