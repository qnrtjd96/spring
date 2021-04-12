package com.bitcamp.jdbc.data;

import java.util.List;

public interface DataDAOImp {

		//글목록
		public List<DataVO> allList();
		
		//글넣기
		public int dataInsert1(DataVO vo);
		
		//레코드 선택
		public DataVO dataselect(int no);
		
		//파일명 선택
		public DataVO getSelectFilename(int no);
		
		//레코드 수정
		public int dataUpdate(DataVO vo);
}
