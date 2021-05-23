package com.bitcamp.myapp.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bitcamp.myapp.service.MemberService;
import com.bitcamp.myapp.vo.MemberVO;

@Controller
public class MemberController {
	
	@Inject
	MemberService memberService;
	
	@RequestMapping("/loginForm")
	public String login() {
		
		return "member/login";
	}
	
	@RequestMapping(value="/loginOk", method=RequestMethod.POST)
	public ModelAndView loginOk(MemberVO vo, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		MemberVO logVO = memberService.login(vo);
		if(logVO==null) { //로그인실패
			mav.setViewName("redirect:loginForm");
			System.out.println("일루오는거?111");
		}else {//로그인성공
			logVO.setLogStatus("Y");
			session.setAttribute("logId", logVO.getUserid());
			session.setAttribute("logName", logVO.getUsername());
			session.setAttribute("logStatus", logVO.getLogStatus());
			mav.setViewName("home");
		}
		return mav;
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "home";
	}
}
