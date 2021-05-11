package com.bitcamp.myapp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.bitcamp.myapp.vo.BoardVO;
import com.bitcamp.myapp.vo.SearchVO;

public interface BoardDAO {
	@Select("select no, subject, userid, hit, to_char(writedate, 'MM-DD HH:MI') writedate "
			+ " from board order by no desc")
	public List<BoardVO> allList();
	
	@Select("select no, subject, content, userid, hit, writedate from board where no=#{no}")
	public BoardVO boardSelect(int no);
	
	@Insert("insert into board(no, userid, subject, content, ip) values(boardsq.nextval, #{userid}, #{subject}, #{content}, #{ip})")
	public int boardInsert(BoardVO vo);

	@Select("select no, subject, userid, hit, to_char(writedate, 'MM-DD HH:MI') writedate "
			+ " from board where ${searchKey} like '%${searchWord}%' order by no desc")
	public List<BoardVO> searchBoard(SearchVO vo);
	
	@Select({"<script>",
			 "select no, subject, content from board where no=#{no}",
			 "</script>"
	})
	public BoardVO boardEditSelect(int no);

	@Update("update board set subject=#{subject}, content=#{content} where no=#{no} and userid=#{userid}")
	public int baordEditUpdate(BoardVO vo);
	
	@Delete("delete from board where no=#{no} and userid=#{userid}")
	public int boardDelete(BoardVO vo);
	
	//여러개의 레코드 지우기
	@Delete({"<script>",
			 "delete from board where no in ",
			 "<foreach item=\"item\" collection=\"array\" open=\"(\" separator=\",\" close=\")\" >",
			 " #{item}",
			 "</foreach>",
			 "</script>"
	})
	public int boardMultiDelete(int[] noList);
}
