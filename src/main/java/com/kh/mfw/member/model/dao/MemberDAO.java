package com.kh.mfw.member.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.kh.mfw.member.model.dto.MemberDTO;

public class MemberDAO {

	// DAO 디비와의 직접적인 상호작용, 영속성 작업
	static {
		try {
			// FullClassName : 패키지경로부터 파일까지
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	
	public MemberDTO login(MemberDTO member) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		MemberDTO selectMember = null;
		// 초기화 initialize
		
		String sql = """
					SELECT 
						  MEMBER_ID 
						, MEMBER_PW 
						, MEMBER_NAME 
						, EMAIL 
						, ENROLL_DATE 
					FROM  
						KH_MEMBER 
					WHERE  
						MEMBER_ID = ? 
					AND 
						MEMBER_PW = ? 
		""";
		
		try{
			conn = DriverManager.getConnection("jdbc:oracle:thin:@112.221.156.34:12345:XE", "KH02_KKM", "KH1234");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, member.getMemberId());
			pstmt.setString(2, member.getMemberPw());
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				selectMember = new MemberDTO();
				selectMember.setMemberId(rset.getString("MEMBER_ID"));
				selectMember.setMemberPw(rset.getString("MEMBER_PW"));
				selectMember.setMemberName(rset.getString("MEMBER_NAME"));
				selectMember.setEmail(rset.getString("EMAIL"));
				selectMember.setEnrollDate(rset.getDate("ENROLL_DATE"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rset != null && rset.isClosed()) rset.close();
				if (pstmt != null && pstmt.isClosed()) pstmt.close();
				if (conn != null && conn.isClosed()) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return selectMember;
	}


	public String checkId(String memberId) {
		
		String sql = """
						SELECT
							COUNT(*)
						FROM 
							KH_MEMBER
						WHERE
							MEMBER_ID = ?
				""";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;

		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@112.221.156.34:12345:XE", "KH02_KKM", "KH1234");
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, memberId);
			
			rset = pstmt.executeQuery();
			/* 
			 * case 1 : count(*) 그룹함수를 사용했을 때
			 * 			무조건 ResultSet이 1행이 존재함
			 * 			컬럼값이 0 / 1 인 것으로 조회결과 판별
			 */
			rset.next();
			
			result = rset.getInt("COUNT(*)");
			
			/*
			 * case 2 : MemberId 컬럼을 조회한 경우
			 * 			return memberId를 사용해서 처리
			 * 			return rset.getString("memberId")
			 */
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rset != null && rset.isClosed()) rset.close();
				if (pstmt != null && pstmt.isClosed()) pstmt.close();
				if (conn != null && conn.isClosed()) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		
	}

}
