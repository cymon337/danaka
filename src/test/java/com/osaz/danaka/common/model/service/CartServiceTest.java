package com.osaz.danaka.common.model.service;

import com.osaz.danaka.common.model.dao.CartMapper;
import com.osaz.danaka.common.model.dto.CartDTO;
import com.osaz.danaka.common.model.dto.CartProductDTO;
import com.osaz.danaka.config.DanakaApplication;
import com.osaz.danaka.config.MybatisConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@ContextConfiguration(classes = {DanakaApplication.class, MybatisConfig.class})
class CartServiceTest {

    @Autowired
    CartMapper cartMapper;

    @Test
    @Transactional
    void selectAllCart() {
    }

    @Test
    @Transactional
    void selectCartProduct() throws Exception {
        // given   리스트 넘길때 productNo 중복 제거
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

        List cartListPN = new ArrayList();
        for (CartDTO list:cartList) {
            cartListPN.add(list.getProductNo());
        }

        List resultPN = new ArrayList();
        for (CartProductDTO list:cartProductList) {
            resultPN.add(list.getProductNo());
        }

        System.out.println("cartProductList = " + cartProductList);
        System.out.println("cartListPN" + cartListPN);
        System.out.println("resultPN" + resultPN);



    }

    @Test
    @Transactional
    void registCart() {
        List<CartDTO> cartList = new ArrayList<>();
        cartList.add(new CartDTO("1","1","1","1","1"));
        cartList.add(new CartDTO("1","1","2","1","1"));

        int result = cartMapper.registCart(cartList);

        org.junit.jupiter.api.Assertions.assertNotEquals(result, 0, "null");
    }

    @Test
    @Transactional
    void updateCart() {
        String cartNo = "1";
        String amont = "15";

        int result = cartMapper.updateCart(cartNo, amont);

        org.junit.jupiter.api.Assertions.assertEquals(result, 1);
    }

    @Test
    @Transactional
    void deleteCart() {
        String cartNo = "1";

        int result = cartMapper.deleteCart(cartNo);

        org.junit.jupiter.api.Assertions.assertEquals(result, 1);
    }
}