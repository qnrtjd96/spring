package com.bitcamp.myapp.board;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BoardReplyController {

		@RequestMapping("/replyWriteOk")
		@ResponseBody			//원글번호, 댓글
		public String replyWriteOk(BoardReplyVO vo, HttpServletRequest req) {
			System.out.println("vo.getno ==" + vo.getNo());
			vo.setUserid((String)req.getSession().getAttribute("logId"));
			vo.setIp(req.getRemoteAddr());
			
			BoardReplyDAO dao = new BoardReplyDAO();
			return dao.replyInsert(vo)+"개 추가됨.";
		}
		
		//댓글 수정
		@RequestMapping("/replyEditOk")
		@ResponseBody
		public String replyEditOk(BoardReplyVO vo, HttpServletRequest req) {
			vo.setUserid((String)req.getSession().getAttribute("logId"));
			BoardReplyDAO dao = new BoardReplyDAO();
			System.out.println("no ==" +vo.getNum());
			return dao.replyUpdate(vo)+ "";
		}
		
		//댓글삭제
		@RequestMapping("/replyDel")
		@ResponseBody
		public String replyDel(int num, HttpServletRequest req) {
			BoardReplyDAO dao = new BoardReplyDAO();
			return dao.replyDelete(num, (String)req.getSession().getAttribute("logId"))+"";
		}
}
