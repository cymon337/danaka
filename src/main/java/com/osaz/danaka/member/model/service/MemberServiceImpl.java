package com.osaz.danaka.member.model.service;

import com.osaz.danaka.member.model.dao.MemberMapper;
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

       /* MemberDTO member = MemberMapper.findMemberById(username);*/

        return null;
    }
    @Override
    public Map<String, List<String>> getPermitListMap() {
        return null;
    }

}
