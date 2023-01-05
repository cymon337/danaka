package com.osaz.danaka.common.model.dao;

import com.osaz.danaka.common.model.dto.CartDTO;
import com.osaz.danaka.common.model.dto.CartProductDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CartMapper {

    //1. selectAllCart 장바구니페이지에서 회원번호별 카트에 담긴 모든 상품 select method
    List<CartProductDTO> selectAllCart(String userNo);

    //2. registCart 회원번호별 다른페이지 요청에 의해 장바구니에 담는 insert method
    int registCart(List<CartDTO> cartList);

    //3. updateCart 장바구니페이지에서 선택된 장바구니 번호 별 수량을 수정하는 update method
    int updateCart(String cartNo, String amount);

    //4. deleteCart 장바구니페이지에서 선택된 장바구니 번호 별 삭제 delete method
    int deleteCart(String cartNo);

    //5. selectCartProduct 조회된 장바구니 리스트에서 프로덕트넘버 넘겨서 프로덕트인포호출
    List<CartProductDTO> selectCartProduct(List<CartDTO> cartList);
}
