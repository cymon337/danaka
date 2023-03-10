package com.osaz.danaka.common.model.service;

import com.osaz.danaka.common.model.dao.CartMapper;
import com.osaz.danaka.common.model.dto.CartDTO;
import com.osaz.danaka.common.model.dto.CartProductDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
public class CartService {

    private final CartMapper cartMapper;

//    @Autowired
//    public CartService(CartMapper cartMapper) { this.cartMapper = cartMapper; }

//1. selectAllCart 장바구니페이지에서 회원번호별 카트에 담긴 모든 상품 select method
    public List<CartProductDTO> selectAllCart(String userNo) {

        List<CartProductDTO> result = cartMapper.selectAllCart(userNo);

        return result;
    }


//2. registCart 회원번호별 다른페이지 요청에 의해 장바구니에 담는 insert method
    public boolean registCart(List<CartDTO> cartList) throws Exception {
        int result = cartMapper.registCart(cartList);

        if (result <= 0) {
            throw new Exception("장바구니 등록 실패");
        }

        return result > 0 ? true : false;
    }

//3. updateCart 장바구니페이지에서 선택된 장바구니 번호 별 수량을 수정하는 update method
    public boolean updateCart(String cartNo, String amount) throws Exception {
        int result = cartMapper.updateCart(cartNo, amount);

        if (result <= 0) {
            throw new Exception("장바구니 수량 변경 실패");
        }

        return result > 0 ? true : false;
    }

//4. deleteCart 장바구니페이지에서 선택된 장바구니 번호 별 삭제 delete method
    public boolean deleteCart(HashMap<String, Object> paramMap) throws Exception {
        int result = cartMapper.deleteCart(paramMap);

        if (result <= 0) {
            throw new Exception("장바구니 수량 변경 실패");
        }

        return result > 0 ? true : false;
    }




}
