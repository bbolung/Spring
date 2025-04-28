package org.zerock.persistance;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;

//root-context.xml의 DB 연결을 사용하는 Test 클래스

@Log4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class JDBCRootContextTests {
	
	@Autowired
	private DataSource dataSource;
	
	@Test
	public void testConnection() {
		
		try {
			Connection conn = dataSource.getConnection();
			log.info("conn: " + conn); 		//console에 값이 나오면 DB 정상 연결
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
