package com.project.mapper;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.project.domain.Criteria;
import com.project.domain.PerformanceVO;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class PerformanceMapperTests {
	
	@Autowired
	private PerformanceMapper mapper;

	@Test
	public void testPaggin() {
		List<PerformanceVO> list = 
				mapper.getListWithPaging(new Criteria(1, 10));
		list.forEach(performance -> log.info(performance));
	}
	
	@Test
	public void testSearch() {
		Map<String, String> map = new HashMap<String, String>();
	    map.put("T", "앙상블");
	    map.put("C", "클래식");
	    map.put("P", "세종");

		
	    Map<String, Map<String, String>> outer = new HashMap<>();

	    outer.put("map", map);
	    
	    List<PerformanceVO> list = mapper.searchTest(outer);
	    
	    log.info("------------------- 결과 확인 -------------------");
	    log.info(list);
		
	}
	
	

}
