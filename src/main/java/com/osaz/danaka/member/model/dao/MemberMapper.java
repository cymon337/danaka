package com.osaz.danaka.member.model.dao;

import com.osaz.danaka.member.model.dto.MemberDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSession;

@Mapper //@Mapper 어노테이션은 해당 클래스가 Mapper 임을 명시함
public interface MemberMapper  {


/*    MemberDTO findMemberById(String memberId);*/
//    int insertMember(MemberDTO memberDTO); //회원가입 처리
        int insertMember(MemberDTO memberDTO); //회원가입 처리

      MemberDTO loginAction(String userId); //로그인

      MemberDTO findId(MemberDTO memberDTO);//아이디 찾기

    MemberDTO findPassword(MemberDTO memberDTO);//비밀번호 찾기

      int userUpdate(MemberDTO memberDTO);


    //  쿼리 수정 후 받아올 결과 값이 따로 없기 때문에 interface의 리턴 값은 void로 설정



}
