package com.osaz.danaka.admin.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminDTO {
	public int userNo;			// 회원번호
	public String userId;		// 아이디
	public String userNickname;	// 닉네임
	public String userPwd;		// 비밀번호
	public String userName;		// 이름
	public java.sql.Date birthday;	//생년월일
	public char gender;			// 성별
	public String address;		// 주소
	public String email;		// 이메일
	public String phone;		// 전화번호
	public Date regDate;		// 등록일
	public char status;			// 회원상태
	public char userRole;		// 권한
}
