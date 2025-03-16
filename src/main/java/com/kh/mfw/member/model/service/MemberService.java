package com.kh.mfw.member.model.service;

import com.kh.mfw.member.model.dao.MemberDAO;
import com.kh.mfw.member.model.dto.MemberDTO;

public class MemberService {

	public MemberDTO login(MemberDTO member) {
		MemberDAO memberDAO = new MemberDAO();
		
		// 서비스에서 하는 일 : 유효성 검사(Validation) => 근데 이러면 단일 책임 원칙 위반 유효성검사도 하고
		//												   , DAO에 값도 넘김. 그래서 유효성 검사 클래스를 따로 만들어서 호출 하는게 나음.
		//						DAO가 두 개 이상 사용될 때 호출 하는 것도 서비스
		return memberDAO.login(member);
	}

	public int SignUp(MemberDTO member) {
		MemberDAO memberDAO = new MemberDAO();
		
		int result = memberDAO.checkId(member.getMemberId());
		
		if (result > 0) {
			return result;
		}else {
			new MemberDAO().signUp(member);
		}
		
		return result;
	}
	
}
