package org.zerock.persistance;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.Test;

import lombok.extern.log4j.Log4j;

/*
 * root-context.xml의 DB 연결을 사용하지 않고, 직접 DB에 연결한 Test 클래스
 * 이렇게 작성할 경우 insert, update, delete, select 구문을 test할 때 매번 DB 연결 코드 아래처럼 작성해야 함
 */

@Log4j
public class JDBCTests {
	
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String uid = "hr";
	private String pass = "hr";

	//JDBCTests 실행하면 static 자동으로 메모리에 올라감(static은 객체 생성 전에 메모리에 로딩됨)
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testConnection() {
		
		try {
			Connection conn = DriverManager.getConnection(url, uid, pass);
			log.info("conn: " + conn); 		//console에 값이 나오면 DB 정상 연결
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
