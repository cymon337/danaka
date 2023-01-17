package com.osaz.danaka.member.model.dao;

import com.osaz.danaka.member.model.dto.MemberDTO;
import com.osaz.danaka.member.model.dto.OrderDTO;
import com.osaz.danaka.member.model.dto.WishListDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper //@Mapper 어노테이션은 해당 클래스가 Mapper 임을 명시함
public interface MemberMapper  {


        int insertMember(MemberDTO memberDTO); //회원가입 처리

      MemberDTO loginAction(String userId); //로그인

      MemberDTO findId(MemberDTO memberDTO);//아이디 찾기


      int userUpdate(MemberDTO memberDTO); //회원정보 수정

      int updatePassword(MemberDTO memberDTO);//비밀번호 변경

    MemberDTO selectUser(String email);//이메일로 사용자 찾기

    int deleteMember(MemberDTO memberDTO); //회원 삭제


    List<OrderDTO> selectOrder(MemberDTO memberDTO);//구매한 목록 가져오기

    List<WishListDTO> selectWishList(MemberDTO memberDTO);//찜한 목록 가져오기

    void cancelPurchase (OrderDTO orderNo); //구매 취소

    void cancelWishList(WishListDTO wishListDTO);//찜 취소

    int idCheck(String userId);// 회원가입 할때 아이디 중복 확인

    int emailCheck(String email);// 회원가입 할때 이메일 중복 확인
}
