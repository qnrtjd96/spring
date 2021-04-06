package com.bitcamp.myapp.member;

import com.bitcamp.myapp.DBCPConnection;

public class MemberDAO extends DBCPConnection implements MemberDAOImpl {

	@Override
	public void login(MemberVO vo) {
		try {
			getConn();
			
			sql="select username from register where userid=? and userpwd=?";
			
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getUserid());
			pstmt.setString(2, vo.getUserpwd());
			rs= pstmt.executeQuery();
			
			if(rs.next()) {
				vo.setUsername(rs.getString(1));
			}
			
		}catch (Exception e) {
			System.out.println("로그인 에러발생 ==> " + e.getMessage());
		}finally {
			setClose();
		}
	}

	@Override
	public int memberInsert(MemberVO vo) {
		int result=0;
		try {
			getConn();
			sql="insert into register(no, userid, username, userpwd, tel, email, zipcode, addr, detailaddr, interest, regdate)"
					+" values(memsq.nextval,?,?,?,?,?,?,?,?,?,sysdate)";
			
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, vo.getUserid());
			pstmt.setString(2, vo.getUsername());
			pstmt.setString(3, vo.getUserpwd());
			pstmt.setString(4, vo.getTel());
			pstmt.setString(5, vo.getEmail());
			pstmt.setString(6, vo.getZipcode());
			pstmt.setString(7, vo.getAddr());
			pstmt.setString(8, vo.getDetaladdr());
			pstmt.setString(9, vo.getinterest());
			
			result= pstmt.executeUpdate();
		}catch (Exception e) {
			System.out.println("회원가입실패 ==> " + e.getMessage());
		}finally {
			setClose();
		}
		return result;
	}

	@Override
	public int memberUpdate(MemberVO vo) {
		int result=0;
		try {
			getConn();
			sql="update register set tel=?, email=?, zipcode=?, addr=?, detailaddr=?, interest=?"
					+" where userid=? and userpwd=?";
			pstmt= conn.prepareStatement(sql);
			pstmt.setString(1, vo.getTel());
			pstmt.setString(2, vo.getEmail());
			pstmt.setString(3, vo.getZipcode());
			pstmt.setString(4, vo.getAddr());
			pstmt.setString(5, vo.getDetaladdr());
			pstmt.setString(6, vo.getinterest());
			
			pstmt.setString(7, vo.getUserid());
			pstmt.setString(8, vo.getUserpwd());
			
			result = pstmt.executeUpdate();
			
		}catch (Exception e) {
			System.out.println("회원 수정 실패 ==> " + e.getMessage());
		}finally {
			setClose();
		}
		return result;
	}

	@Override
	public void memberSelect(MemberVO vo) {
		try {
			getConn();
			
			sql = "select username, tel, email, zipcode, addr, detailaddr, interest from register where userid=? ";
			
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, vo.getUserid());
			
			rs= pstmt.executeQuery();
			if(rs.next()) {
				vo.setUsername(rs.getString(2));
				vo.setTel(rs.getString(2));
				vo.setEmail(rs.getString(3));
				vo.setZipcode(rs.getString(4));
				vo.setAddr(rs.getString(5));
				vo.setDetaladdr(rs.getString(6));
				vo.setInterst(rs.getString(7));
			}
		}catch (Exception e) {
			System.out.println("회원가져오기 오류 ==> " + e.getMessage());
		}finally {
			setClose();
		}
	}
}
