package com.bitcamp.myapp.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bitcamp.myapp.service.BoardService;
import com.bitcamp.myapp.vo.BoardVO;
import com.bitcamp.myapp.vo.MemberVO;

@Controller
public class BoardController {

	@Inject
	BoardService boardService;
	
	@RequestMapping("/boardList")
	public ModelAndView boardList() {
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("list", boardService.boardAllRecord());
		mav.setViewName("board/boardList");
		
		return mav;
	}
	
	@RequestMapping("/boardWrite")
	public String boardWrite() {
		return "board/boardWrite";
	}
	
	@RequestMapping(value="/boardWriteOk", method=RequestMethod.POST)
	public ModelAndView boardWriteOk(BoardVO vo, HttpServletRequest req, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		MemberVO vo2 = (MemberVO)session.getAttribute("logVO");
				
		vo.setIp(req.getRemoteAddr());
		vo.setUserid(vo2.getUserid());
//		vo.setUserid(((MemberVO) session.getAttribute("logVO")).getUserid());
		
		int cnt = boardService.boardWrite(vo);
		if(cnt>0) {
			mav.setViewName("redirect:boardList");
		}else {
			mav.setViewName("redirect:boardWrite");
		}
		return mav;
	}
	
	@RequestMapping("/boardView")
	public ModelAndView boardView(int no) {
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("vo",boardService.boardSelect(no));
		mav.setViewName("board/boardView");
		return mav;
	}
	
	@RequestMapping("/boardEdit")
	public ModelAndView boardEdit(int no) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("vo", boardService.boardSelect(no));
		mav.setViewName("board/boardEdit");
		return mav;
	}
	
//	@RequestMapping("/boardEdit")
//	public String boardEdit(int no, Model model) {
//		model.addAttribute("vo", boardService.boardSelect(no));
//		return "board/boardEdit";
//	}
	
	@RequestMapping(value="/boardEditOk", method=RequestMethod.POST)
	public ModelAndView boardEditOk(BoardVO vo) {
		ModelAndView mav = new ModelAndView();
		System.out.println("vo.getNo() = " + vo.getNo());
		int cnt = boardService.boardEditOk(vo);
		mav.addObject("no",vo.getNo());
		
		if(cnt>0) {
			mav.setViewName("redirect:boardView");
		}else {
			mav.setViewName("redirect:boardEdit");
		}
		
		return mav;
	}
	
	@RequestMapping("/boardDel")
	public ModelAndView boardDelete(int no,HttpSession session){
		ModelAndView mav = new ModelAndView();
		
		if(boardService.boardDelete(no, ((MemberVO) session.getAttribute("logVO")).getUserid())>0) {//삭제
			mav.setViewName("redirect:boardList");
		}else {//삭제실패
			mav.addObject("no", no);
			mav.setViewName("redirect:boardView");
		}
		
		return mav;
	}
}
