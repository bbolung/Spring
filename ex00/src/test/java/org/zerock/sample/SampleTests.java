package org.zerock.sample;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;

//서버 구동하지 않고 web에서 test
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class SampleTests {
	
	@Autowired
	private Restaurant restaurant;
	
	@Test
	public void testRest() {
		log.info("--------------------------");
		log.info("restaurant : " + restaurant);
		restaurant.sample();
	}
}
