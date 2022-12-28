package com.osaz.danaka.member.model.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Map;

public interface MemberService extends UserDetailsService {

    Map<String, List<String>> getPermitListMap();
}
