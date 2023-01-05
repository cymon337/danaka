package com.osaz.danaka.member.model.service;

import com.osaz.danaka.member.model.dao.MemberMapper;
import com.osaz.danaka.member.model.dto.MemberDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class MemberServiceImpl implements MemberService {

    private  final MemberMapper memberMapper;

    @Autowired
    public MemberServiceImpl(MemberMapper memberMapper) {
        this.memberMapper = memberMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("호출확인");
        System.out.println("username" + username); //들어오는 확인

    MemberDTO member = memberMapper.findMemberById(username);
        System.out.println(member);

        return null;
    }
    @Override
    public Map<String, List<String>> getPermitListMap() {
        return null;
    }

}
