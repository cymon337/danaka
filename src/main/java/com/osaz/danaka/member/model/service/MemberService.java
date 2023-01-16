package com.osaz.danaka.member.model.service;


import com.osaz.danaka.member.model.dao.MemberMapper;
import com.osaz.danaka.member.model.dto.MemberDTO;
import com.osaz.danaka.member.model.dto.OrderDTO;
import com.osaz.danaka.member.model.dto.WishListDTO;
import lombok.RequiredArgsConstructor;
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

@Service
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;


    @Autowired
    MemberMapper memberMapper;


    public void saveUserData(MemberDTO memberDTO) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM HH:mm:sss");
        Date time = new Date();

        memberDTO.setUserPwd(passwordEncoder.encode(memberDTO.getUserPwd()));
        memberDTO.setUserRole("MEMBER");
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


    public MemberDTO findId(MemberDTO user) {
        System.out.println("user = " + user);
        System.out.println("이름" + user.getMemberName());
        return memberMapper.findId(user);
    }

    public MemberDTO findPassword(MemberDTO user) {
        System.out.println("user = " + user);
        //System.out.println();

        return memberMapper.findPassword(user);
    }

    public MemberDTO deleteMember(MemberDTO memberDTO) {
        memberMapper.deleteMember(memberDTO);
        return memberDTO;
    }

    public MemberDTO updateUser(MemberDTO memberDTO) {
        memberMapper.userUpdate(memberDTO);
        return memberDTO;
    }

    public MemberDTO selectUser(String email) {
        MemberDTO memberDTO = memberMapper.selectUser(email);
        return memberDTO;
    }

//    public MemberDTO findPw(MemberDTO memberDTO) throws Exception{
//      BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//      String result = null;
//
//      MemberDTO memberDTO =
//    }


    //
    @Transactional
    public void updatePassword(MemberDTO memberDTO, String newPassword) {
        memberDTO.updatePassword(passwordEncoder.encode(newPassword));
        memberMapper.updatePassword(memberDTO);

    }


    public List<OrderDTO> selectOrder(MemberDTO memberDTO){

        return memberMapper.selectOrder(memberDTO);
    }

    public List<WishListDTO> selectWishList(MemberDTO memberDTO){
        return memberMapper.selectWishList(memberDTO);
    }
}
