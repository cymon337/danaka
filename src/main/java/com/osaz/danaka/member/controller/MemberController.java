package com.osaz.danaka.member.controller;

import com.osaz.danaka.member.model.dao.MemberMapper;
import com.osaz.danaka.member.model.dto.MemberDTO;
import com.osaz.danaka.member.model.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Date;

@Controller
@RequiredArgsConstructor
public class MemberController {

    @Autowired
    private final MemberService memberService;
    private MemberMapper memberMapper;

     MemberDTO memberDTO;

//    @GetMapping // 아무런 URL이 없는 (localhost:8080) 경우는 loginPAge로 Redirect.
//    public  String loginRedirect(){
//        return "redirect:/login";

//    }

    @GetMapping("/member/login")
    public String loginPage(@RequestParam(value = "error", required = false)String error,
                            @RequestParam(value = "exception",required = false)String exception,
                            Model model){
        model.addAttribute("error", error);
        model.addAttribute("exception", exception);

        return "member/login";
    }
//    @PostMapping("/login_action")
//    public String loginAction(){
//
//
//
//        return "redirect:member/login";
//    }


    @GetMapping("/member/signUp")
    public String signUpPage(){
        return "member/signUp";
    }

    @PostMapping("/signUp_action")
    public String insertMember(MemberDTO memberDTO){
        memberService.saveUserData(memberDTO);
        return "redirect:member/login";

    }

    @GetMapping("/member/modification")
    public String userModification(){
        return "member/modification";
    }

    @GetMapping("/member/id")
    public String findIdView(){
        return "member/id";

    }

    @PostMapping("/find_id")
    public String findId(HttpServletRequest request, Model model){

        String userName = request.getParameter("userName");
        String phone = request.getParameter("phone");

        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setUserName(userName);
        memberDTO.setPhone(phone);

        MemberDTO user = memberService.findId(memberDTO);

        model.addAttribute("check", 1);
        if (user != null) {
            model.addAttribute("userId", user.getUserId());
            model.addAttribute("check", 0);
        }
        return "member/id";
    }

//    @PostMapping("/updateMembers_action")
//    public String updateMembers(MemberDTO memberDTO){
//        memberMapper.updateMembers(memberDTO);
//        return "redirect:member/login";
//    }



}
