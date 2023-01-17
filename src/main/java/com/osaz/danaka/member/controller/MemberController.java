package com.osaz.danaka.member.controller;

import com.osaz.danaka.member.model.dao.MemberMapper;
import com.osaz.danaka.member.model.dto.MemberDTO;
import com.osaz.danaka.member.model.dto.OrderDTO;
import com.osaz.danaka.member.model.dto.WishListDTO;
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
import java.util.List;
import java.util.Random;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MemberController {

    @Autowired
    private final MemberService memberService;

    private final PasswordEncoder passwordEncoder;


    @Autowired
    JavaMailSender mailSender;
    MemberDTO memberDTO;

    @InitBinder("passwordForm")
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(new PasswordFormValidator());
    }


    // # title : 로그인 구현
    // # author : 정근호
    // # description : 로그인 화면 보여주고 에러처리
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



    // # title : 회원가입 페이지
    // # author : 정근호
    // # description : 회원가입 페이지 화면 출력
    @GetMapping("/member/signUp")
    public String signUpPage() {
        return "member/signUp";
    }

    // # title : 회원가입 페이지
    // # author : 정근호
    // # description : 사용자가 입력한 정보로 회원가입
    @PostMapping("/signUpAction")
    public String insertMember(MemberDTO memberDTO) {
        memberService.saveUserData(memberDTO);

        return "redirect:member/login";

    }




    // # title : 아이디 찾기 페이지
    // # author : 정근호
    // # description : 아이디 찾기 페이지 화면 출력
    @GetMapping("/member/id")
    public String findIdView() {
        return "member/id";

    }

    // # title : 아이디 찾기 페이지
    // # author : 정근호
    // # description : 핸드폰 번호와 사용자 이름으로 아이디를 검색한 후 보여줌
    @PostMapping("/findId")
    public String findId(HttpServletRequest request, Model model) {

        String memeberName = request.getParameter("memeberName");
        String phone = request.getParameter("phone");

        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setMemberName(memeberName);
        memberDTO.setPhone(phone);

        MemberDTO user = memberService.findId(memberDTO);

        model.addAttribute("check", 1);
        if (user != null) {
            model.addAttribute("userId", user.getUserId());
            model.addAttribute("check", 0);
        }
        return "member/id";
    }

    // # title : 회원정보 수정 페이지
    // # author : 정근호
    // # description : 회원정보 수정 페이지 출력 이때 로그인한 사용자의 정보도 모델의 담아서 함께 보냄
    @GetMapping("/member/modification")
    public String userUpdateView(@AuthenticationPrincipal MemberDTO memberDTO, Model model) {


        model.addAttribute("member", memberDTO);
        log.info(String.valueOf(memberDTO));
        return "member/modification";

    }


    // # title : 회원정보 수정
    // # author : 정근호
    // # description : 사용자가 변경하고자 하는 입력값을 받아서 회원정보를 수정 한다
    @PostMapping("/member/updateMembers")
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


    // # title : 회원 탈퇴 페이지
    // # author : 정근호
    // # description : 회원 탈퇴 페이지 이때 로그인한 사용자의 정보도 함께 보낸다
    @GetMapping("/member/unregister")
    public String deleteMember(@AuthenticationPrincipal MemberDTO memberDTO, Model model, RedirectAttributes rttr){

        rttr.addAttribute("msg", false);
        model.addAttribute("member", memberDTO);

        return "member/unregister";
    }

    // # title : 회원 탈퇴
    // # author : 정근호
    // # description : 탈퇴를 하려면 사용자가 입력한 비밀번호와 DB의 암호화된 비밀번호를 비교해서 맞으면 탈퇴를 하고 세션을 만료한다
    @PostMapping("/deleteAction")
    public String memberDelete(@AuthenticationPrincipal MemberDTO memberDTO, @RequestParam(value = "password") String password, HttpSession session, RedirectAttributes rttr, Model model
    ) throws Exception {




        model.addAttribute("member", memberDTO);
        log.info( "딜리트액션 ={} ",(memberDTO));
        log.info( "딜리트액션 친비번 ={} ",password);

        String sessionPassword = memberDTO.getPassword();



        log.info( "딜리트액션 친비번 ={} ",password);
        String str;

        if (!(passwordEncoder.matches(password, sessionPassword))) {
           rttr.addAttribute("msg", false);
            str = "redirect:member/unregister";

        } else {
            memberService.deleteMember(memberDTO);
            session.invalidate();
            str = "redirect:member/login";
        }


        return str;

    }



    // # title : 비밀번호 찾기 페이지
    // # author : 정근호
    // # description : 비밀번호 찾기 페이지를 보여줌
    @GetMapping("/member/password")
    public void pwdPage(ModelAndView mv) {

    }

    // # title : 비밀번호 찾기
    // # author : 정근호
    // # description : 비밀번호 찾기 입력받은 이메일로 해당 유저를 찾고 입력한 아이디 까지 맞으면 인증 이메일로 보내고 이메일 인증번호 입력하는 페이지로 넘겨줌
    @PostMapping("/pwAuth")
    public ModelAndView pwAuth(HttpSession session, HttpServletRequest request) throws IOException {
        String email = request.getParameter("email");
        String name = request.getParameter("memberName");



         memberDTO = memberService.selectUser(email);
         session.setAttribute("userId", memberDTO.getUserId());
        log.info(email);
        if (memberDTO != null) {
            Random r = new Random();
            int num = r.nextInt(99999); //랜덤 난수 설정

            if (memberDTO.getMemberName().equals(name)) {
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
                mv.setViewName("member/pwAuth"); //이메일 인증번호 받는페이지
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
    @PostMapping("/pwSet")// 이메일인증 번호
    public String pwSet(@RequestParam(value = "emailInjeung") String emailInjeung,
                        @RequestParam(value = "num") String num, Model model) {

        model.addAttribute("passwordForm", new PasswordForm());
        if(emailInjeung.equals(num)) {
            return "member/passwordUpdate";
        } else {
            return "member/password";
        }
    }
    // # title : 비밀번호 변경 페이지
    // # author : 정근호
    // # description : 비밀번호를 변경하기위해 사용자 맞는지 이메일을 통해서 확인하다, 동일한 이메일을 쓰는 사용자가 있으면 사용자의 정보를 가지고 온다
    @GetMapping("/member/passwordUpdate")
    public String passUpdateForm( Model model,String email) {

        memberDTO = memberService.selectUser(email);

//        model.addAttribute("email", email);
        model.addAttribute("passwordForm", new PasswordForm());
        log.info("비번변경찍먹 = {}", model);
        return "member/passwordUpdate";
    }



    // # title : 새 비밀번호 변경
    // # author : 정근호
    // # description : 비밀번호를 변경하고 로그아웃을 시켜서 세션 만료 시킴
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

        return "redirect:member/logout";

    }

    // # title : 마이페이지 페이지
    // # author : 정근호
    // # description : 사용자가 구매한 목록을 보여주고 사이드바로 장바구니, 회원탈퇴, 회원수정 으로 갈 수 있음
    @GetMapping("member/mypage")
    public String mypageView(@AuthenticationPrincipal MemberDTO memberDTO, Model model){


        List<OrderDTO> orderList = memberService.selectOrder(memberDTO);
        model.addAttribute("orderList", orderList);


        return "member/mypage";
    }

    // # title : 위시리스트 페이지
    // # author : 정근호
    // # description : 사용자가 찜한 상품을 목록으로 보여줌
    @GetMapping("member/wishList")
    public String wishListView(@AuthenticationPrincipal MemberDTO memberDTO, Model model){

        List<WishListDTO> wishList = memberService.selectWishList(memberDTO);
        model.addAttribute("wishList" , wishList);

        return "member/wishList";
    }

    // # title : 구매취소
    // # author : 정근호
    // # description : 구매한 상품을 체크박스를 이용 하거나 개별적으로 선택해서 취소 할 수 있음
    @ResponseBody
    @PostMapping("/member/cancelPurchase")
    public int cancelPurchase(@AuthenticationPrincipal MemberDTO memberDTO,@RequestParam(value = "chbox[]") List<String> chArr ,OrderDTO orderDTO) throws Exception {




        String userNo = memberDTO.getUserNo();

        int result = 0;
        int orderNum = 0;


        if(memberDTO != null) {
            orderDTO.setUserNo(userNo);

            for(String i : chArr) {
                orderNum = Integer.parseInt(i);
                orderDTO.setOrderNo(String.valueOf(orderNum));
                memberService.cancelPurchase(orderDTO);
            }
            result = 1;
            }
            return result;



    }

    // # title : 찜 취소
    // # author : 정근호
    // # description : 찜 한 상품을 체크박스를 이용 하거나 개별적으로 선택해서 취소 할 수 있음
    @ResponseBody
    @PostMapping("/member/cancelWishList")
    public int cancelWishList(@AuthenticationPrincipal MemberDTO memberDTO,@RequestParam(value = "chbox[]") List<String> chArr ,WishListDTO wishListDTO) throws Exception {




        String userNo = memberDTO.getUserNo();

        int result = 0;
        int whisNum = 0;


        if(memberDTO != null) {
            wishListDTO.setUserNo(userNo);

            for(String i : chArr) {
                whisNum = Integer.parseInt(i);
                wishListDTO.setWishNo(String.valueOf(whisNum));
                memberService.cancelWishList(wishListDTO);
            }
            result = 1;
        }
        return result;

    }

    // # title : 아이디 중복 확인
    // # author : 정근호
    // # description : 회원가입시 아이디의 중복을 체크함
    @ResponseBody
    @PostMapping("/idCheck")
    public int idCheck(@RequestParam(value = "userId") String userId){



        int cnt = memberService.idCheck(userId);

        log.info("cnt ={}", cnt);


        return cnt;
    }
    // # title : 이메일 중복 확인
    // # author : 정근호
    // # description : 회원가입시 이메일의 중복을 체크함
    @ResponseBody
    @PostMapping("/emailCheck")
    public int emailCheck(@RequestParam(value = "email") String email){



        int cnt = memberService.emailCheck(email);

        log.info("cnt ={}", cnt);


        return cnt;
    }

}
