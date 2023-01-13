package com.osaz.danaka.product.model.service;

import com.osaz.danaka.common.SelectCriteria;
import com.osaz.danaka.config.DanakaApplication;
import com.osaz.danaka.config.MybatisConfig;
import com.osaz.danaka.product.model.dto.ProductDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ContextConfiguration(classes = {DanakaApplication.class, MybatisConfig.class})
class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @Test
    public void 서비스_의존성_주입_테스트(){

        assertNotNull(productService);
    }

    @Test
    public void 페이징_처리용_총_상품개수_조회_서비스_테스트() {

        // given
        Map<String, String> searchMap = new HashMap<>();
        searchMap.put("categoryCode", "RD");
        // when
        int totalCount = productService.selectTotalCount(searchMap);
        // then
        assertTrue(totalCount > 0? true : false);
    }

    @Test
    public void 카테고리별_상품들_조회_서비스_테스트() {

        // given
        SelectCriteria selectCriteria = new SelectCriteria();
        selectCriteria.setCategoryCode("RD");
        selectCriteria.setOrderCondition("lowPrice");
        // when
        List<ProductDTO> productList = productService.selectListByCategory(selectCriteria);
        // then
        assertNotNull(productList);
        productList.forEach(product -> System.out.println("product = " + product));
    }

    @Test
    public void 상품_상세페이지용_조회_서비스_테스트(){

        // given
        String productNo = "1";
        // when
        ProductDTO product = productService.selectOneProduct(productNo);
        // then
        assertNotNull(product);
    }

    @Test
    public void 상품_상세페이지용_옵션_상품들_조회_서비스_테스트() {

        // given
        String productName = "REEL01";
        // when
        List<ProductDTO> optionList = productService.selectOptionList(productName);
        // then
        assertNotNull(optionList);
    }

    @Test
    public void 상품_상세페이지용_관련상품들_조회_서비스_테스트() {

        // given
        String productNo = "3";
        // when
        List<ProductDTO> refProductList = productService.selectRefProducts(productNo);
        // then
        assertNotNull(refProductList);
    }

    @Test
    public void 위시리스트_테이블에_추가_서비스_테스트() throws Exception {

        // given
        String userNo = "1";
        String productNo = "1";
        HashMap<String, String> wishMap  = new HashMap<>();
        wishMap.put("userNo", userNo);
        wishMap.put("productNo", productNo);
        // when
        boolean result = productService.insertWishProduct(wishMap);
        // then
        assertTrue(result);
    }

    @Test
    public void 장바구니_테이블에_추가_서비스_테스트() throws Exception {

        // given
        String userNo = "1";
        String productNo = "1";
        String amount = "3";
        HashMap<String, String> cartMap  = new HashMap<>();
        cartMap.put("userNo", userNo);
        cartMap.put("productNo", productNo);
        cartMap.put("amount", amount);
        // when
        boolean result = productService.insertWishProduct(cartMap);
        // then
        assertTrue(result);
    }
}