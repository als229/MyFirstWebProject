package com.kh.mfw.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.session.StandardSession;

import com.kh.mfw.member.model.dto.MemberDTO;
import com.kh.mfw.member.model.service.MemberService;

@WebServlet("/sign-in")
public class SignInController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SignInController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 절차
		
		// 1) GET / POST => 요청 전송 방식이 POST라면 인코딩 작업
		request.setCharacterEncoding("UTF-8");
		// setter란? 필드값을 변경해주는 애~
		
		// 2) 요청값이 있는지 없는지 확인~ 있으면 값 꺼내기
		// request.getParameter("input의 name 속성 값");
		//						=> 무조건 input의 name 속성값을 적는 것은 아님
		
		// memberId / memberPw
		String memberId = request.getParameter("memberId");
		String memberPw = request.getParameter("memberPw");
		
		// 3) 값이 두 개 이상일 경우 어딘가에 예쁘게 담기
		MemberDTO member = new MemberDTO();
		member.setMemberId(memberId);
		member.setMemberPw(memberPw);
		// java에서는 new 키워드를 가지고 메모리에 데이터 할당
		
		MemberService memberService = new MemberService();
		// 데이터를 넘기고 받는 걸 고급지게 '객체간의 상호작용' 이라고 함.
		MemberDTO selectMember = memberService.login(member);
		
		// case 1. 아이디와 비밀번호 값이 일치했다면
		// 		   필드값에 회원정보가 담겨있는 MemberDTO 객체의 주소값
		
		// case 2. 유효성검증에 통과하지 못했거나, 아이디 또는 비밀번호가 일치하지 않았따면
		// 		   null 값이 돌아와유
		
//		request.setAttribute("selectMember", selectMember);

		/* 
		 * 로그인에 성공했다면,
		 * 로그인 한 회원의 정보를
		 * 로그아웃 요청이 들어오거나, 브라우저를 종료하기 전 까지는
		 * 계속 사용할 수 있어야 하기 때문에,
		 * Session 이라는 저장소에 값을 담아둘 것
		 */
		// Session의 자료향 : HttpSession
		
		HttpSession session = request.getSession();
		
		session.setAttribute("loginMember", selectMember);
		
		
//		request.getRequestDispatcher("index.jsp").forward(request, response);
		
		/* 포워딩 방식의 특징
		 * 매핑값이 유알엘에 남아있음!!1
		 * 거기서 새로고침하고 다시 주소에 남은 주소로 들어가면 빈 값이 넘어감
		 */ 
		
		/*
		 * send redirect 방식으로 해보자!
		 * ip주소 + context root
		 * sendRedirect : Client에게 재 요청할 URL을 알려주어서
		 * Client가 다시 요청을 보내게 만드는 방법
		 * 
		 * 얘는 클라이언트 브라우저한테 다시 요청해라 라고 하는 거임
		 * 그래서 클라이언트 브라우저에서 저 경로로 다시 쏨
		 */
		String contextPath = request.getContextPath();
		response.sendRedirect(contextPath);
		
		
		// getContextPath() : ContextRoot 가져오는 메서드 
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
