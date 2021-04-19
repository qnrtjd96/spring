package com.bitcamp.home.claseBoard;

import java.util.List;

public interface ClaseBoardDAOImp {
	
	public int claseInsert(ClaseBoardDTO dto);
	public List<ClaseBoardDTO> claseAllRecord();
	public ClaseBoardDTO claseSelect(int no);
	public int hitcount(int no);
	public ClaseBoardDTO oriInfor(int no);//원글의 ref, step, lvl를 선택하는 메소드
	public int lvlcount(ClaseBoardDTO dto);//lvl증가
	public int claseDataInsert(ClaseBoardDTO dto);
	public int getTotalRecord();//총레코드수 구하기
	public int claseUpdate(ClaseBoardDTO dto); //답변형 글수정
	public ClaseBoardDTO getStep(int no);
	public int claseDelete(int no, String userid); //원글삭제
	public int claseDeleteUpdate(int no, String userid); //답글삭제
	
//	public PrevNextVO lagLeadSelect(int no); //이전글 다음글
}
