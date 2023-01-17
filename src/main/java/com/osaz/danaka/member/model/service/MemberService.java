package com.osaz.danaka.member.model.service;


import com.osaz.danaka.member.model.dao.MemberMapper;
import com.osaz.danaka.member.model.dto.MemberDTO;
import com.osaz.danaka.member.model.dto.OrderDTO;
import com.osaz.danaka.member.model.dto.WishListDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;


    @Autowired
    MemberMapper memberMapper;

    //회원가입할때 유저의 정보를 담는다
    public void saveUserData(MemberDTO memberDTO) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM HH:mm:sss");
        Date time = new Date();

        memberDTO.setUserPwd(passwordEncoder.encode(memberDTO.getUserPwd()));
        memberDTO.setUserRole("ROLE_USER");
        memberDTO.setStatus("Y");
        memberDTO.setRegDate(format.format(time).toString());
        memberMapper.insertMember(memberDTO);


    }

    //유저의 계정을 비교하여 로그인을 인증한다
    @Override
    public MemberDTO loadUserByUsername(String userId) throws UsernameNotFoundException {
        MemberDTO memberDTO = memberMapper.loginAction(userId);
        if (Objects.isNull(memberDTO)) {
            throw new UsernameNotFoundException("해당하는 유저를 찾을 수 없습니다.");
        }
        return memberDTO;
    }


    //아이디 찾기 하는 기능
    public MemberDTO findId(MemberDTO user) {
        System.out.println("user = " + user);
        System.out.println("이름" + user.getMemberName());
        return memberMapper.findId(user);
    }
    

    //회원 탈퇴 하는 기능
    public MemberDTO deleteMember(MemberDTO memberDTO) {
        memberMapper.deleteMember(memberDTO);
        return memberDTO;
    }

    //회원 정보 수정 기능
    public MemberDTO updateUser(MemberDTO memberDTO) {
        memberMapper.userUpdate(memberDTO);
        return memberDTO;
    }

    //이메일로 사용자 찾아오는 기능
    public MemberDTO selectUser(String email) {
        MemberDTO memberDTO = memberMapper.selectUser(email);
        return memberDTO;
    }

    //비밀번호 찾기 ->비밀번호 변경
    @Transactional
    public void updatePassword(MemberDTO memberDTO, String newPassword) {
        memberDTO.updatePassword(passwordEncoder.encode(newPassword));
        memberMapper.updatePassword(memberDTO);

    }
    
    //사용자가 구매한 목록 보여줌
    public List<OrderDTO> selectOrder(MemberDTO memberDTO){

        return memberMapper.selectOrder(memberDTO);
    }

    //사용자가 찜한 목록 보여줌
    public List<WishListDTO> selectWishList(MemberDTO memberDTO){
        return memberMapper.selectWishList(memberDTO);
    }

    //사용자가 구매한거 취소 기능
    public void  cancelPurchase(OrderDTO orderDTO) throws Exception{
         memberMapper.cancelPurchase(orderDTO);

    }
    // 사용자가 찜한거 취소 기능
    public void  cancelWishList(WishListDTO wishListDTO) throws Exception{
        memberMapper.cancelWishList(wishListDTO);

    }

    //회원 가입시 아이디 중복 확인
    public int idCheck(String userId) {
        int cnt = memberMapper.idCheck(userId);
        log.info("cnt = {}" ,(cnt));


        return cnt;
    }
    //회원 가입시 이메일 중복 확인
    public int emailCheck(String email) {
        int cnt = memberMapper.emailCheck(email);
        log.info("cnt = {}" ,(cnt));


        return cnt;
    }
}
