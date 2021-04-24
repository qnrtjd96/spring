package com.bitcamp.home.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.lang.Nullable;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class Logininterceptor extends HandlerInterceptorAdapter {
	
	//HandlerInterceptorAdapter를 상속받아 Interceptor클래스를 생성한 후 메소드를 오버라이딩한다.
	//컨트롤러가 호출되기전에 먼저 실행되는 메소드
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) throws Exception {
		
		//로그인 여부를 확인하고 로그인상태인 경우 호출된 컨트롤러가 실행되고,
		//				   로그인 안된 경우 로그인 페이지로(컨트롤러로) 이동
		try {
			//1. 로그인 정보 구하기
			String userid = (String)req.getSession().getAttribute("logId");
			if(userid ==null || userid.equals("")) {//로그인이 안된경우
				if(isAjaxRequest(req)) {
					res.sendError(400);
					return false;
				}else {
				//로그인으로 이동
				res.sendRedirect(req.getContextPath()+"/loginForm");
				return false;//접속한 컨트롤러 사용을 중지한다.
				}
			}
		}catch (Exception e) {
			return false;
		}
		return true;
	}
	private boolean isAjaxRequest(HttpServletRequest req) {
		String ajaxHeader = req.getHeader("AJAX");
		if ("true".equals(ajaxHeader)){
	         return true;
	        }else{
	         return false;
	        }
	}
	//컨트롤러가 실행후 view페이지로 이동하기전에 호출되는 메소드
	public void postHandel(HttpServletRequest req, HttpServletResponse res, Object handler, 
			@Nullable ModelAndView modelAndView) throws Exception{
		
	}
	//컨트롤러가 실행후 호출된 메소드
	public void afterComletion(HttpServletRequest req, HttpServletResponse res, Object handler, 
			@Nullable Exception e)throws Exception {
		
	}
}
