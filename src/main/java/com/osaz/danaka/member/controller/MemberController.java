package com.osaz.danaka.member.controller;

import com.osaz.danaka.member.model.dao.MemberMapper;
import com.osaz.danaka.member.model.dto.MemberDTO;
import com.osaz.danaka.member.model.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Slf4j
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
                            Model model ){


        model.addAttribute("error", error);
        model.addAttribute("exception", exception);

        return "member/login";
    }

    @GetMapping("/username")
    @ResponseBody
    public String currentUserName(Authentication authentication)
    {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return userDetails.getUsername();
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

//    @GetMapping("/member/modification")
//    public String userModification(){
//        return "member/modification";
//    }

    @GetMapping("/member/id")
    public String findIdView(){
        return "member/id";

    }

    @PostMapping("/find_id")
    public String findId(HttpServletRequest request, Model model){

        String userName = request.getParameter("userName");
        String phone = request.getParameter("phone");

        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setMemberName(userName);
        memberDTO.setPhone(phone);

        MemberDTO user = memberService.findId(memberDTO);

        model.addAttribute("check", 1);
        if (user != null) {
            model.addAttribute("userId", user.getUserId());
            model.addAttribute("check", 0);
        }
        return "member/id";
    }

    @GetMapping("/member/modification")
    public String userUpdateView(@AuthenticationPrincipal MemberDTO memberDTO, Model model){



        model.addAttribute("member", memberDTO);
        log.info(String.valueOf(memberDTO));
        return "member/modification";

    }
//    , @RequestParam(value = "userId") String userId)

    @PostMapping("/updateMembers")
    public String userUpdate(@RequestParam(value = "nickname") String nickname,
                             @RequestParam(value = "email") String email, @RequestParam(value = "phone") String phone,
                           String userId ) {

        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setUserNickname(nickname);
        memberDTO.setEmail(email);
        memberDTO.setPhone(phone);
        memberDTO.setUserId(userId);



            memberService.updateUser(memberDTO);


        log.info(String.valueOf(memberDTO));


        return "redirect:member/login";
    }





}
