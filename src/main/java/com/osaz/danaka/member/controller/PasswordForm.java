package com.osaz.danaka.member.controller;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;


// # title : PasswordForm 생성
// # author : 정근호
// # description :변경할 비밀번호 전달받을 Form 클래스를 생성
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PasswordForm {

/*    @Length(min = 8, max = 50)*/
    private String newPassword;

 /*   @Length(min = 8, max = 50)*/
    private String newPasswordConfirm;
}
