package com.kh.mfw.member.model.dto;

import java.sql.Date;
import java.util.Objects;

public class MemberDTO {
	 
	private String memberId;
	private String memberPw;
	private String memberName;
	private String email;
	private Date enrollDate;
	
	public MemberDTO(String memberId, String memberPw, String memberName, String email, Date enrollDate) {
		super();
		this.memberId = memberId;
		this.memberPw = memberPw;
		this.memberName = memberName;
		this.email = email;
		this.enrollDate = enrollDate;
	}
	
	public MemberDTO(String memberId, String memberPw, String memberName, String email) {
		super();
		this.memberId = memberId;
		this.memberPw = memberPw;
		this.memberName = memberName;
		this.email = email;
	}

	public MemberDTO() {
		super();
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getMemberPw() {
		return memberPw;
	}

	public void setMemberPw(String memberPw) {
		this.memberPw = memberPw;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getEnrollDate() {
		return enrollDate;
	}

	public void setEnrollDate(Date enrollDate) {
		this.enrollDate = enrollDate;
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, enrollDate, memberId, memberName, memberPw);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MemberDTO other = (MemberDTO) obj;
		return Objects.equals(email, other.email) && Objects.equals(enrollDate, other.enrollDate)
				&& Objects.equals(memberId, other.memberId) && Objects.equals(memberName, other.memberName)
				&& Objects.equals(memberPw, other.memberPw);
	}
	
	@Override
	public String toString() {
		return "MemberDTO [memberId=" + memberId + ", memberPw=" + memberPw + ", memberName=" + memberName + ", email="
				+ email + ", enrollDate=" + enrollDate + "]";
	}
}
