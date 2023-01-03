package com.osaz.danaka.product.model.service;

import com.osaz.danaka.common.SelectCriteria;
import com.osaz.danaka.config.DanakaApplication;
import com.osaz.danaka.config.MybatisConfig;
import com.osaz.danaka.product.model.dto.ProductDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ContextConfiguration(classes = {DanakaApplication.class, MybatisConfig.class})
class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @Test
    public void testInit(){

        assertNotNull(productService);
    }

    @Test
    public void 카테고리별_상품_조회용_서비스_테스트(){

        // given
        SelectCriteria selectCriteria = new SelectCriteria();
        selectCriteria.setCategoryCode("RD");
        // when
        List<ProductDTO> productList = productService.selectListByCategory(selectCriteria);
        // then
        assertNotNull(productList);
        productList.forEach(product -> System.out.println("product = " + product));
    }

    @Test
    public void 상품_조회용_서비스_테스트(){

        // given
        String productNo = "1";
        // when
        ProductDTO product = productService.selectOneProduct(productNo);
        // then
        assertNotNull(product);
    }
}