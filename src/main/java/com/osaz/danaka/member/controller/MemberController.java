package com.osaz.danaka.member.controller;

import com.osaz.danaka.member.model.dao.MemberMapper;
import com.osaz.danaka.member.model.dto.MemberDTO;
import com.osaz.danaka.member.model.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import java.io.IOException;
import java.util.Random;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MemberController {

    @Autowired
    private final MemberService memberService;
    private MemberMapper memberMapper;



    @Autowired
    JavaMailSender mailSender;
    MemberDTO memberDTO;

    @InitBinder("passwordForm")
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(new PasswordFormValidator());
    }

//    @GetMapping // 아무런 URL이 없는 (localhost:8080) 경우는 loginPAge로 Redirect.
//    public  String loginRedirect(){
//        return "redirect:/login";

//    }

    @GetMapping("/member/login")
    public String loginPage(@RequestParam(value = "error", required = false) String error,
                            @RequestParam(value = "exception", required = false) String exception,
                            Model model) {


        model.addAttribute("error", error);
        model.addAttribute("exception", exception);

        return "member/login";
    }

    @GetMapping("/username")
    @ResponseBody
    public String currentUserName(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return userDetails.getUsername();
    }


    @GetMapping("/member/signUp")
    public String signUpPage() {
        return "member/signUp";
    }

    @PostMapping("/signUpAction")
    public String insertMember(MemberDTO memberDTO) {
        memberService.saveUserData(memberDTO);

        return "redirect:member/login";

    }

//    @GetMapping("/member/modification")
//    public String userModification(){
//        return "member/modification";
//    }

    @GetMapping("/member/id")
    public String findIdView() {
        return "member/id";

    }

    @PostMapping("/findId")
    public String findId(HttpServletRequest request, Model model) {

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

    @GetMapping("/member/modification")
    public String userUpdateView(@AuthenticationPrincipal MemberDTO memberDTO, Model model) {


        model.addAttribute("member", memberDTO);
        log.info(String.valueOf(memberDTO));
        return "member/modification";

    }
//    , @RequestParam(value = "userId") String userId)

    @PostMapping("/updateMembers")
    public String userUpdate(@RequestParam(value = "nickname") String nickname,
                             @RequestParam(value = "email") String email, @RequestParam(value = "phone") String phone,
                             String userId) {

        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setUserNickname(nickname);
        memberDTO.setEmail(email);
        memberDTO.setPhone(phone);
        memberDTO.setUserId(userId);


        memberService.updateUser(memberDTO);


        log.info(String.valueOf(memberDTO));


        return "redirect:member/login";
    }

    @GetMapping("/member/unregister")
    public String deleteMember(@AuthenticationPrincipal MemberDTO memberDTO, Model model, RedirectAttributes rttr){

        rttr.addAttribute("msg", false);
        model.addAttribute("member", memberDTO);

        return "member/unregister";
    }

//    @PostMapping("/deleteAction")
//    public String memberDelete(@AuthenticationPrincipal MemberDTO memberDTO, @RequestParam(value = "password") String password, HttpSession session, RedirectAttributes rttr, Model model
//    ) throws Exception {
//
//
//
//
//        model.addAttribute("member", memberDTO);
//        log.info( "딜리트액션 ={} ",(memberDTO));
//        log.info( "딜리트액션 친비번 ={} ",password);
//
//        String sessionPassword = memberDTO.getPassword();
//
//
//
//        log.info( "딜리트액션 친비번 ={} ",password);
//        String str;
//
//        if (!(passwordEncoder.matches(password, sessionPassword))) {
//           rttr.addAttribute("msg", false);
//            str = "redirect:member/unregister";
//
//        } else {
//            memberService.deleteMember(memberDTO);
//            session.invalidate();
//            str = "redirect:member/login";
//        }
//
//
//        return str;
//
//    }

    @GetMapping("/member/passwordUpdate")
    public String passUpdateForm( Model model,String email) {

        memberDTO = memberService.selectUser(email);

//        model.addAttribute("email", email);
        model.addAttribute("passwordForm", new PasswordForm());
        log.info("비번변경찍먹 = {}", model);
        return "member/passwordUpdate";
    }


    @PostMapping("/NewPassword")
    public String updatePassword( @Valid PasswordForm passwordForm, HttpSession session,
                                 Errors errors, Model model, RedirectAttributes attributes, String userId) {

        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setUserId((String) session.getAttribute("userId"));

        if (errors.hasErrors()) {
            model.addAttribute(memberDTO);
            return "member/passwordUpdate";
        }
        memberService.updatePassword(memberDTO, passwordForm.getNewPassword());
        attributes.addFlashAttribute("message", "패스워드를 변경했습니다.");
        /*패스워드 변경후 로그아웃 시키는거 추가할 수 있으면 하자*/
        return "redirect:member/login";

    }
    @GetMapping("/member/password")
    public void pwdPage(ModelAndView mv) {

    }
    @PostMapping("/pwAuth")
    public ModelAndView pwAuth(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String email = request.getParameter("email");
        String name = request.getParameter("userName");



         memberDTO = memberService.selectUser(email);
         session.setAttribute("userId", memberDTO.getUserId());
        log.info(email);
        if (memberDTO != null) {
            Random r = new Random();
            int num = r.nextInt(99999); //랜덤 난수 설정

            if (memberDTO.getUserName().equals(name)) {
                session.setAttribute("email", memberDTO.getEmail());

                String setform = "jgh337337@gmail.com";
                String tomail = email; //받는사람
                String title = "비밀번호 변경 인증 이메일 입니다";
                String content = System.getProperty("line.separator") + "안녕하세요 회원님" + System.getProperty("line.separator") +
                        "비밀번호 찾기(변경) 인증번호는 " + num + " 입니다." + System.getProperty("line.separator");

                try {
                    MimeMessage message = mailSender.createMimeMessage();
                    MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");

                    messageHelper.setFrom(setform);
                    messageHelper.setTo(tomail);
                    messageHelper.setSubject(title);
                    messageHelper.setText(content);

                    mailSender.send(message);

                } catch (Exception e) {
                    throw new RuntimeException();
                }

                ModelAndView mv = new ModelAndView();
                mv.setViewName("member/pwAuth");
                mv.addObject("num", num);
                return mv;
            } else {
                ModelAndView mv = new ModelAndView();
                mv.setViewName("member/password");
                return mv;
            }
        } else {
            ModelAndView mv = new ModelAndView();
            mv.setViewName("member/password");
            return mv;
        }
    }
    @PostMapping("/pwSet")
    public String pwSet(@RequestParam(value = "emailInjeung") String emailInjeung,
                        @RequestParam(value = "num") String num, Model model) {

        model.addAttribute("passwordForm", new PasswordForm());
        if(emailInjeung.equals(num)) {
            return "member/passwordUpdate";
        } else {
            return "member/password";
        }
    }


}
