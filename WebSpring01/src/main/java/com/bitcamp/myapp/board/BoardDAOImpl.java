package com.bitcamp.myapp.board;

import java.util.List;

public interface BoardDAOImpl {
	//글등록
	public int boardInsert(BoardVO vo);
	
	//글 전체 조회
	public List<BoardVO> boardAllRecord();
	
	//글 조회
	public void boardSelect(BoardVO vo);
	//public BoardVO boardSelect(int no);
	//글 수정
	public int boardUpdate(BoardVO vo);
	
	//글 삭제
	public int boardDelete(BoardVO vo);
	
	//조회수
	public void hitCount(int no);
}
