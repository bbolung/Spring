package com.project.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.project.domain.Criteria;
import com.project.domain.PerformanceVO;
@Mapper
public interface PerformanceMapper {
	PerformanceVO selectByImgKey(@Param("imgKey") String imgKey);

	int insert(PerformanceVO performance);
	
	//전체 페이지 목록
	public List<PerformanceVO> getList();
	
	public List<PerformanceVO> getListWithPaging(Criteria cri);

	public int getTotalCount(Criteria cri);
	
	public List<PerformanceVO> searchTest(Map<String, Map<String, String>> map);
	
}
