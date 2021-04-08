package com.bitcamp.jdbc;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
	public JdbcTemplate JdbcTemplate;
	public JdbcTemplate getJdbcTemplate() {
		return JdbcTemplate;
	}
	
	//어노테이션의미 : 메소드 자동실행 -->언제? 컨트롤이 호출될때, servlet-Context.xml에 있는 template객체를
	//jdbcTemplate와 Constants.jdbcTemplate에도 셋팅한다.
	@Autowired
	public void setJdbcTemplate(JdbcTemplate template) {
		JdbcTemplate = template;
		Constants.jdbcTemplate = template;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		
		return "home";
	}
	
}
