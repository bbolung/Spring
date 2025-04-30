package org.zerock.exception;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import lombok.extern.log4j.Log4j;

//평소엔 작동X, Controller 예외 발생하면 가로채서 작동
//http://localhost:8080/sample/ex02?name=aa&age=aa -> int형 age인데 문자 입력하면 error 발생
//사용자에게 보여줄 error_page 화면에 표시
@ControllerAdvice
@Log4j
public class CommonExceptionAdvice {
	
	//@ExceptionHandler(Exception.class) -> 모든 예외 처리
	@ExceptionHandler(Exception.class)
	public String except2(Exception ex, Model model) {
		
		log.error("Exception.........." + ex.getMessage());
		model.addAttribute("exception",ex);
		log.error(model);
		return "error_page2";
	}
	
	//@ExceptionHandler(NumberFormatException.class) -> format이 안맞는 예외 처리
	
	//이동하려고 하는 페이지가 없는 경우(잘못 요청) web.xml에 NoHandlerFoundException 추가하고 사용
	@ExceptionHandler(NoHandlerFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String handle404(NoHandlerFoundException ex) {
		return "custom404";
	}
	
	
}
