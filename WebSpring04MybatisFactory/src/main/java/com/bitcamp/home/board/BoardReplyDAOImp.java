package com.bitcamp.home.board;

import java.util.List;

public interface BoardReplyDAOImp {
	//댓글쓰기
	public int replyInsert(BoardReplyVO vo);
	//댓글수정
	public int replyUpdate(BoardReplyVO vo);
	//댓글삭제
	public int replyDelete(int num, String userid);
	//해당글의 댓글전체목록
	public List<BoardReplyVO> replyList(int no);
}
