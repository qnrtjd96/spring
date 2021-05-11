package com.bitcamp.myapp.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bitcamp.myapp.service.BoardService;
import com.bitcamp.myapp.vo.BoardVO;
import com.bitcamp.myapp.vo.SearchVO;

@Controller
public class boardController {
	
	@Inject
	BoardService service;
	
	@RequestMapping("/list")
	public ModelAndView boardList() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("list", service.allList());
		mav.setViewName("board/list");
		
		return mav;
	}
	
	@RequestMapping("/view")
	public String boardView(int no, Model model) {
		model.addAttribute("vo",service.boardSelect(no));
		
		return "board/view";
	}
	
	@RequestMapping("/write")
	public String boardWrite() {
		
		return "board/write";
	}
	
	@RequestMapping("/writeOk")
	public ModelAndView boardInsert(BoardVO vo, HttpServletRequest req) {
		vo.setIp(req.getRemoteAddr());
		vo.setUserid((String)req.getSession().getAttribute("logId"));
		
		ModelAndView mav = new ModelAndView();
		if(service.boardInsert(vo)>0) {//글쓰기 성공
			mav.setViewName("redirect:list");
		}else {//실패
			mav.setViewName("redirect:write");
		}
		return mav;
	}
	@RequestMapping("/search")
	public ModelAndView boardSearch(SearchVO vo) {
		ModelAndView mav = new ModelAndView();
		
		System.out.println(vo.getSearchKey());
		System.out.println(vo.getSearchWord());
		mav.addObject("list", service.SearchBoard(vo));
		mav.setViewName("board/list");
		
		return mav;
	}
	
	@RequestMapping("/boardEdit")
	public ModelAndView boardEdit(int no) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("vo",service.boardEditSelect(no));
		mav.setViewName("board/edit");
		return mav;
	}
	
	@RequestMapping("/editOk")
	public ModelAndView editOk(BoardVO vo, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		vo.setUserid((String)session.getAttribute("logId"));
		
		int result = service.baordEditUpdate(vo);
		
		mav.addObject("no", vo.getNo());
		if(result>0) {//성공
			mav.setViewName("redirect:view");
		}else {//실패
			mav.setViewName("redirect:edit");
		}
		
		return mav;
	}
	@RequestMapping("/boardDel")
	public ModelAndView boardDel(BoardVO vo, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		vo.setUserid((String)session.getAttribute("logId"));
		
		int result = service.boardDelete(vo);
		if(result>0) {
			mav.setViewName("redirect:list");
		}else {
			mav.addObject("no", vo.getNo());
			mav.setViewName("redirect:view");
		}
		
		return mav;
	}
	
	//여러개의 레코드를 한번에 삭제하기
	@RequestMapping("/mutiDel")
	public ModelAndView boardMutiDel(BoardVO vo) {
		ModelAndView mav = new ModelAndView();
		for(int no : vo.getNoList()) {
			System.out.println("no = " + no);
		}
		
		int result = service.boardMultiDelete(vo.getNoList());
		
		System.out.println("삭제된 레코드 수 ==> " + result);
		mav.setViewName("redirect:list");
		return mav;
	}
}
