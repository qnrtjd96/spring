package com.bitcamp.myapp.service;

import java.util.List;

import com.bitcamp.myapp.vo.BoardVO;
import com.bitcamp.myapp.vo.SearchVO;

public interface BoardService {

	public List<BoardVO> allList();
	public BoardVO boardSelect(int no);
	public int boardInsert(BoardVO vo);
	public List<BoardVO> SearchBoard(SearchVO vo);
	public BoardVO boardEditSelect(int no);
	public int baordEditUpdate(BoardVO vo);
	public int boardDelete(BoardVO vo);
	public int boardMultiDelete(int[] noList);
}
