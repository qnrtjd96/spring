package com.bitcamp.myapp.reply;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bitcamp.myapp.board.BoardVO;

@Controller
public class ReplyController {
	
	//댓글쓰기
	@RequestMapping(value="replyWriteOk", method=RequestMethod.POST)
	public ModelAndView replyWriteOk(ReplyVO vo, HttpServletRequest req) {
		ModelAndView mav = new ModelAndView();
		ReplyDAO dao = new ReplyDAO();
		
		vo.setUserid((String)req.getSession().getAttribute("logId"));
		int result = dao.replyInsert(vo);
		if(result>0) {
			mav.setViewName("redirect:boardView");
		}else {
			mav.setViewName("board/boardList");
		}
		return mav;
	}
}
