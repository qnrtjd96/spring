package com.bitcamp.home.board;

import java.util.List;

public interface BoardDAOImp {
	//리스트
	public List<BoardVO> allList();
	
	//글작성
	public int boardInsert(BoardVO vo);
	
	//글선택해서 들어가는거
	public BoardVO boardSelect(int no);
	
	//글수정
	public int boardUpdate(int no, String subject, String content, String userid);
	
	//글삭제
	public int boardDelete(int no, String userid);
}
