package com.bitcamp.myapp.board;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bitcamp.myapp.DBCPConnection;
import com.bitcamp.myapp.reply.ReplyVO;

public class BoardReplyDAO extends DBCPConnection implements BoardReplyDAOImp {
	
	@Override
	public int replyInsert(final BoardReplyVO vo) {
		String sql = "insert into boardReply(num, no, content, userid, ip) "
						+" values(boardsq.nextval,?,?,?,?) ";
		return template.update(sql, new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, vo.getNo());
				ps.setString(2, vo.getContent());
				ps.setString(3, vo.getUserid());
				ps.setString(4, vo.getIp());
			}
		});
	}

	@Override
	public int replyUpdate(BoardReplyVO vo) {
		String sql ="update boardReply set content=? where num=? and userid=?";
		return template.update(sql, vo.getContent(), vo.getNum(), vo.getUserid());
	}

	@Override
	public int replyDelete(int num, String userid) {
		String sql ="delete from boardReply where num=? and userid=?";
		return template.update(sql, num, userid);
	}

	@Override
	public List<BoardReplyVO> replyAllRecord(int no) {
		List<BoardReplyVO> list = new ArrayList<BoardReplyVO>();
		BoardReplyVO vo = new BoardReplyVO();
		try {
			getConn();
			String sql = "select num, content, userid, replydate from boardreply where no=? order by num ";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();	
			while(rs.next()) {
				vo.setNo(rs.getInt(1));
				vo.setUserid(rs.getString(2));
				vo.setContent(rs.getString(3));
				list.add(vo);
			}
		}catch (Exception e) {
			System.out.println("댓글 선택 에러 ==> " + e.getMessage());
		}finally {
			setClose();
		}
		return list;
	}

}
