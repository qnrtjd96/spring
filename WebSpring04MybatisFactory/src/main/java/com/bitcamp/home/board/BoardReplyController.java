package com.bitcamp.home.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BoardReplyController {
	@Autowired
	SqlSession sqlSession;
	
	//해당글의 댓글목록선택
	@RequestMapping("/replyList")
	@ResponseBody
	public List<BoardReplyVO> replyList(int no){
		BoardReplyDAOImp dao = sqlSession.getMapper(BoardReplyDAOImp.class);

		return dao.replyList(no);
	}
	@RequestMapping("/replyWriteOk")
	@ResponseBody			//원글번호, 댓글
	public String replyWriteOk(BoardReplyVO vo, HttpSession session, HttpServletRequest req, HttpServletResponse res) throws Exception {
		BoardReplyDAOImp dao = sqlSession.getMapper(BoardReplyDAOImp.class);
		String userid = (String)session.getAttribute("logId");
		System.out.println("userid = " + userid);
		if(userid==null) {
			res.sendRedirect(req.getContextPath()+"/loginForm");
		}else {
			vo.setUserid(userid);
		}
		vo.setIp(req.getRemoteAddr());
		
		return dao.replyInsert(vo)+"개 추가됨.";
	}
	
	//댓글삭제
	@RequestMapping("/replyDel")
	@ResponseBody
	public int replyDel(int num, HttpSession session) {
		BoardReplyDAOImp dao = sqlSession.getMapper(BoardReplyDAOImp.class);
		String userid =(String)session.getAttribute("logId");
		return dao.replyDelete(num, userid);
	}
	
	//댓글 수정
	@RequestMapping("/replyEditOk")
	@ResponseBody
	public int replyEditOk(BoardReplyVO vo, HttpSession session) {
		BoardReplyDAOImp dao =  sqlSession.getMapper(BoardReplyDAOImp.class);
		vo.setUserid((String)session.getAttribute("logId"));
		
		System.out.println("vo = " + vo.getContent());
		return dao.replyUpdate(vo);
	}
}
