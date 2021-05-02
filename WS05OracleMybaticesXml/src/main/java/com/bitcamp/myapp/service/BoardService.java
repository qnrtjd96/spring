package com.bitcamp.myapp.service;

import java.util.List;

import com.bitcamp.myapp.vo.BoardVO;

public interface BoardService {
	public List<BoardVO> boardAllRecord();

	public int boardWrite(BoardVO vo);

	public BoardVO boardSelect(int no);

	public int boardEditOk(BoardVO vo);
	
	public int boardDelete(int no, String userid);
}
