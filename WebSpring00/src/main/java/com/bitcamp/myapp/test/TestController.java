package com.bitcamp.myapp.test;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TestController {

	@RequestMapping(value="/aLink", method = RequestMethod.GET)
	public String test(HttpServletRequest req, Model model) {
		//클라이언트에서 서버로 데이터 가져오기
		String name = req.getParameter("name");
		String age = req.getParameter("age");
		
		//서버에 한번출력
		System.out.println("name = "+ name  + "  age = " + age);
		
		//Model 객체 데이터를 셋팅하면 뷰에서 사용할수 있다.
		model.addAttribute("username", name);
		model.addAttribute("age", age);
		model.addAttribute("msg", "서버에서 클라이언트로 데이터보내기");
		
		return "mappingTest/aLinkView";
	}
	//@RequestParam : 클라이언트 측 데이터를 서버 requset한다.
	@RequestMapping("/aLink2")
	public String test2(@RequestParam("name") String username, @RequestParam("age")	int age, Model model) {
		System.out.println("aLink2 --> " + username + " : " + age);
		
		model.addAttribute("username", username);
		model.addAttribute("age", age);
		model.addAttribute("msg", "@requestParam을 이용한 데이터 처리");
		
		return "mappingTest/aLinkView";
	}
	@RequestMapping("/aLink3") //vo에서 request하여 변수명이 같은곳으로 값을 setting한다.
	public String test3(TestVO vo, Model model) {
		System.out.println("TestVO--> " + vo.getUsername() + ", " + vo.getAge());
		
		vo.setMsg("vo이용한 request테스트중....");
		model.addAttribute("vo", vo);
		
		return "mappingTest/aLinkView2";
	}
	
	@RequestMapping("/aLink4")
	public ModelAndView test4(TestVO vo) {
		System.out.println("TestVO--> " + vo.getUsername() + ", " + vo.getAge());
		vo.setMsg("ModelAndView 테스트 중.....");
		
		//데이터와 뷰파일명을 한번에 가지는 클랙스
		ModelAndView mav = new ModelAndView();
		mav.addObject("vo", vo);
		mav.setViewName("mappingTest/aLinkView2");
		
		return mav;
	}
	//폼으로 이동
	@RequestMapping("/formData")
	public String fromTest() {
		
		return "mappingTest/form";
	}
	@RequestMapping(value="/formDataOk", method=RequestMethod.POST)
	public ModelAndView fromTestOk(TestVO vo) {
		System.out.println("formData----->> "+ vo.getUsername() + " , " + vo.getAge() );
		
		vo.setMsg("폼데이터 전송확인");
		ModelAndView mav = new ModelAndView();
		mav.addObject("vo", vo);
		mav.setViewName("mappingTest/aLinkView2");
		
		return mav;
	}
}
