package com.osaz.danaka.product.model.dao;

import com.osaz.danaka.config.DanakaApplication;
import com.osaz.danaka.config.MybatisConfig;
import com.osaz.danaka.product.model.dto.ProductDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ContextConfiguration(classes = {DanakaApplication.class, MybatisConfig.class})
class ProductMapperTest {

    @Autowired
    private ProductMapper productMapper;

    @Test
    public void 매퍼_인터페이스_의존성_주입_테스트() {

        assertNotNull(productMapper);
    }

    @Test
    public void 카테고리별_상품_조회용_매퍼_테스트() {

        // given
        String categoryCode = "RD";
        // when
        List<ProductDTO> productList = productMapper.selectByCategory(categoryCode);
        // then
        assertNotNull(productList); // 파라미터에 넣은 값이 null 이 아니면 통과
        System.out.println("productList = " + productList);
    }

    @Test
    public void 상품_조회용_매퍼_테스트(){

        // given
        String productNo = "1";
        // when
        ProductDTO product = productMapper.selectOne(productNo);
        // then
        assertNotNull(product);
    }
}