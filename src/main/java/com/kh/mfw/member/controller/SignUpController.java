package com.kh.mfw.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.mfw.member.model.dto.MemberDTO;
import com.kh.mfw.member.model.service.MemberService;

@WebServlet("/sign-up")
public class SignUpController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SignUpController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 인코딩 설정
		request.setCharacterEncoding("UTF-8");
		
		// 컨트롤러의 역할 : 요청처리 => 사용자가 입력한 값들이 저 멀리 있는 DB Server의 KH_MEMBER테이블에 한 행 INSERT
		String memberId = request.getParameter("memberId");
		String memberPw = request.getParameter("memberPw");
		String memberName = request.getParameter("memberName");
		String email = request.getParameter("email");
		
		MemberDTO member = new MemberDTO(memberId, memberPw, memberName, email);
		//=> 원래 객체는 이렇게 생성해야 하는게 이론적으로 맞음. : 객체는 생성과 동시에 유효해야 한다. but 실무에서는 다를 수 있음
		
		int result = new MemberService().SignUp(member);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
