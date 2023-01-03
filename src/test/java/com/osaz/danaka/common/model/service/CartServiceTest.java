package com.osaz.danaka.common.model.service;

import com.osaz.danaka.common.model.dao.CartMapper;
import com.osaz.danaka.common.model.dto.CartDTO;
import com.osaz.danaka.common.model.dto.CartProductDTO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class CartServiceTest {

    CartService cartService;
    CartMapper cartMapper;

    @Test
    void selectAllCart() {
    }

    @Test
    void selectCartProduct() throws Exception {
        // given
        List<CartDTO> cartList = new ArrayList<>();
        cartList.add(new CartDTO("1","1","1","1","1"));
        cartList.add(new CartDTO("1","1","2","1","1"));
        cartList.add(new CartDTO("1","1","3","1",""));
        cartList.add(new CartDTO("1","1","4","1",""));
        cartList.add(new CartDTO("1","1","5","1",""));
        cartList.add(new CartDTO("1","1","6","1",""));
        // when
        List<CartProductDTO> cartProductList = cartMapper.selectCartProduct(cartList);


        // then

        List<String> cartListPN = new ArrayList<>();
        for (CartDTO list:cartList) {
            cartListPN.add(list.getProductNo());
        }

        List<String> resultPN = new ArrayList<>();
        for (CartProductDTO list:cartProductList) {
            cartListPN.add(list.getProductNo());
        }

        Assertions.assertThat(cartListPN).isEqualTo(resultPN);


    }

    @Test
    void registCart() {
    }

    @Test
    void updateCart() {
    }

    @Test
    void deleteCart() {
    }
}