package com.osaz.danaka.member.model.dao;

import com.osaz.danaka.member.model.dto.MemberDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {

    MemberDTO findMemberById(String memberId);
}
