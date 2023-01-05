package com.osaz.danaka.member.controller;

import com.osaz.danaka.member.model.dto.MemberDTO;
import com.osaz.danaka.member.model.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {

    @Autowired
    private final MemberService memberService;

//    @GetMapping // 아무런 URL이 없는 (localhost:8080) 경우는 loginPAge로 Redirect.
//    public  String loginRedirect(){
//        return "redirect:/login";

//    }

    @GetMapping("/member/login")
    public String loginPage(){
        return "member/login";
    }

    @GetMapping("/member/signUp")
    public String signUpPage(){
        return "member/signUp";
    }

    @PostMapping("/signUp_action")
    public String insertMember(MemberDTO memberDTO){
        memberService.saveUserData(memberDTO);
        return "redirect:member/login";
    }

}
