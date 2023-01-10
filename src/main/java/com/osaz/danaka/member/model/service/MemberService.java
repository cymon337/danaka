package com.osaz.danaka.member.model.service;


import com.osaz.danaka.member.model.dao.MemberMapper;
import com.osaz.danaka.member.model.dto.MemberDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.text.SimpleDateFormat;

@Service
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {

    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM HH:mm:sss");
    Date time = new Date();




    @Autowired
    MemberMapper memberMapper;


    @Transactional
    public void saveUserData(MemberDTO memberDTO) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
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
        if (memberDTO == null) {
            throw new UsernameNotFoundException("해당하는 유저를 찾을 수 없습니다.");
        }
        return memberDTO;
    }


    public MemberDTO findId(MemberDTO user) {
        System.out.println("user = " + user);
        System.out.println("이름" + user.getUserName());
        return memberMapper.findId(user);
    }

    public MemberDTO findPassword(MemberDTO user) {
        System.out.println("user = " + user);
        //System.out.println();

        return memberMapper.findPassword(user);
    }


    public MemberDTO updateUser(MemberDTO memberDTO){
        memberMapper.userUpdate(memberDTO);
        return memberDTO;
    }



//    public int userUpdate(MemberDTO memberDTO){
//        return memberMapper.userUpdate(memberDTO);
//    }
}
