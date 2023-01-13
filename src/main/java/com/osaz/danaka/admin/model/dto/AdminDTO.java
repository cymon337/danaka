package com.osaz.danaka.admin.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminDTO {
	public int userNo;
	public String userId;
	public String userNickname;
	public String userPwd;
	public String userName;
	public java.sql.Date birthday;
	public char gender;
	public String address;
	public String email;
	public String phone;
	public Date regDate;
	public char status;
	public char userRole;
}
