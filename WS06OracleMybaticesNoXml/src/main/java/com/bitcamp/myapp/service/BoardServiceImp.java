package com.bitcamp.myapp.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.bitcamp.myapp.dao.BoardDAO;
import com.bitcamp.myapp.vo.BoardVO;
import com.bitcamp.myapp.vo.SearchVO;

@Service
public class BoardServiceImp implements BoardService {
	
	@Inject
	BoardDAO dao;
	
	public List<BoardVO> allList(){
		return dao.allList();
	}

	@Override
	public BoardVO boardSelect(int no) {
		return dao.boardSelect(no);
	}

	@Override
	public int boardInsert(BoardVO vo) {
		
		return dao.boardInsert(vo);
	}

	@Override
	public List<BoardVO> SearchBoard(SearchVO vo) {
		return dao.searchBoard(vo);
	}

	@Override
	public BoardVO boardEditSelect(int no) {
		return dao.boardEditSelect(no);
	}

	@Override
	public int baordEditUpdate(BoardVO vo) {
		return dao.baordEditUpdate(vo);
	}

	@Override
	public int boardDelete(BoardVO vo) {
		return dao.boardDelete(vo);
	}

	@Override
	public int boardMultiDelete(int[] noList) {
		return dao.boardMultiDelete(noList);
	}
}
