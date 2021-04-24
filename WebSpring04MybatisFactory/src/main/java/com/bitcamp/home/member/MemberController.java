package com.bitcamp.home.member;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MemberController {
	@Autowired// 이 어노테이션을 부여하면 각 상황의 타입에 맞는 IoC컨테이너 안에 존재하는 Bean을 자동으로 주입해주게 된다.
	public SqlSession sqlSession;
	
	@RequestMapping("/loginForm")
	public String loginForm() {
		return "member/login";
	}
	
	@RequestMapping(value="/loginOk", method=RequestMethod.POST)
	public ModelAndView loginCheck(MemberVO vo, HttpSession session) {
		//추상클래스 MemberDAoImp와 xml파일을 매핑한다.
		MemberDAOImp dao = sqlSession.getMapper(MemberDAOImp.class);
		MemberVO logVO = dao.loginCheck(vo);
		
		ModelAndView mav = new ModelAndView();
		if(logVO==null) {//로그인실패
			mav.setViewName("redirect:loginForm");
		}else {//로그인성공
			session.setAttribute("logId", logVO.getUserid());
			session.setAttribute("logName", logVO.getUsername());
			mav.setViewName("redirect:/");
		}
		return mav;
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		
		return "home";
	}
}
