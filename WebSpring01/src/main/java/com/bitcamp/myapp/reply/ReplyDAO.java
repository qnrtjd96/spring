package com.bitcamp.myapp.reply;

import java.util.ArrayList;
import java.util.List;

import com.bitcamp.myapp.DBCPConnection;
import com.bitcamp.myapp.board.BoardVO;

public class ReplyDAO extends DBCPConnection {
	

	public int replyInsert(ReplyVO vo) {
		int result = 0;
		try {
			getConn();
			sql = "insert into reply(no, userid, content) "
					+" values(?,?,?)";
			
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getNo());
			pstmt.setString(2, vo.getUserid());
			pstmt.setString(3, vo.getContent());
			
			result= pstmt.executeUpdate();
			
		}catch (Exception e) {
			System.out.println("글쓰기 에러 ==> " + e.getMessage());
		}finally {
			setClose();
		}
		return result;
	}
	
	//조회
	public List<ReplyVO> ReplySelect(BoardVO vo, ReplyVO vo2) {
		List<ReplyVO> list = new ArrayList<ReplyVO>();
		try {
			getConn();
			
			sql="select no, userid, content from reply where no=? ";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getNo());
			rs = pstmt.executeQuery();	
			while(rs.next()) {
				vo2.setNo(rs.getInt(1));
				vo2.setUserid(rs.getString(2));
				vo2.setContent(rs.getString(3));
				list.add(vo2);
			}
		}catch (Exception e) {
			System.out.println("댓글 선택 에러 ==> " + e.getMessage());
		}finally {
			setClose();
		}
		return list;
	}
}
