package com.osaz.danaka.member.controller;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


// # title : validate 구현
// # author : 정근호
// # description : @InitBinder를 이용해 주입해주었던 validator를 구현
public class PasswordFormValidator implements Validator { //validate 구현
    @Override
    public boolean supports(Class<?> clazz) {//어떤 타입에 대해 validate 할지 결정 함
        return PasswordForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) { //supports PasswordForm 타입에 할당할 수 있는 타입만 받도록 하였기 때문에 target 객체는 PasswordForm으로 캐스팅 할 수 있음
                                                           //  그 이후 새로운 비밀번호와 비밀번호 확인이 동일한지 체크하여 동일하지 않을 경우 에러 객체에 에러 문구를 전달함
        PasswordForm passwordForm = (PasswordForm) target;
        if (!passwordForm.getNewPassword().equals(passwordForm.getNewPasswordConfirm())) {
            errors.rejectValue("newPassword", "wrong.value", "입력한 새 패스워드가 일치하지 않습니다.");
        }
    }
}