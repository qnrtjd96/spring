package com.bitcamp.myapp.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.bitcamp.myapp.dao.BoardDAO;
import com.bitcamp.myapp.vo.BoardVO;

@Service
public class BoardServiceImp implements BoardService{
	
	@Inject
	BoardDAO boardDAO;
	
	public List<BoardVO> boardAllRecord(){
		
		return boardDAO.boardAllRecord();
	}

	@Override
	public int boardWrite(BoardVO vo) {
		
		return boardDAO.boardWrite(vo);
	}

	@Override
	public BoardVO boardSelect(int no) {
		
		return boardDAO.boardSelect(no);
	}

	@Override
	public int boardEditOk(BoardVO vo) {
		
		return boardDAO.boardEditOk(vo);
	}

	@Override
	public int boardDelete(int no, String userid) {
		
		return boardDAO.boardDelete(no, userid);
	}
}
