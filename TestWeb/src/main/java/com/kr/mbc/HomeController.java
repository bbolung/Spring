package com.kr.mbc;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	//get 방식으로 "/" 요청 -> home() 실행
	//model이 화면에 보여줄 때 담는 역할(request와 동일한 역할)
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		//request.setAttribute("serverTime", formattedDate); 와 동일
		model.addAttribute("serverTime", formattedDate );
		
		/*
		 * request.getRequestDispatcher("home.jsp").forward(model, locale);와 동일
		 * return "jsp파일명";
		 */
		return "home";
		
		/*
		 * <beans:bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
				<beans:property name="prefix" value="/WEB-INF/views/" />
				<beans:property name="suffix" value=".jsp" />
			</beans:bean>
			
			-> return "home"; ->  /WEB-INF/views/home.jsp로 변경
			-> /WEB-INF/views/home.jsp = view + model(serverTime)
		 */
	}
	
	//get방식으로 "/my" 요청 -> myHome() 실행
	@RequestMapping(value = "/my", method = RequestMethod.GET)
	public String myHome(Model model) {
		
		model.addAttribute("name", "ohsehui");
		
		return "my";
	}
	
}
