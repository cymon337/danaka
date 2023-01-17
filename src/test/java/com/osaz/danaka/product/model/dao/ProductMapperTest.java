package com.osaz.danaka.product.model.dao;

import com.osaz.danaka.common.Pagenation;
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

import static org.junit.jupiter.api.Assertions.*;

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
    public void 페이징_처리용_총_상품_개수_조회_매퍼_테스트() {

        // given
        HashMap<String, String> searchMap = new HashMap<>();
        searchMap.put("categoryCode", "RD");
        // when
        int result = productMapper.selectTotalCount(searchMap);
        System.out.println(result);
        // then
        assertTrue(result >= 0? true:false);

    }

    @Test
    public void 카테고리별_상품들_조회_매퍼_테스트() {

        // given
        String categoryCode = "RD";
        String orderCondition = "lowPrice";
        int pageNo = 1;

        HashMap<String, String> searchMap = new HashMap<>();
        searchMap.put("categoryCode", categoryCode);

        int totalCount = productMapper.selectTotalCount(searchMap);

        int limit = 10;
        int buttonAmount = 5;

        SelectCriteria selectCriteria = null;
        selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount, categoryCode, orderCondition);
        // when
        List<ProductDTO> productList = productMapper.selectListByCategory(selectCriteria);
        System.out.println("productList = " + productList);
        // then
        assertNotNull(productList); // 파라미터에 넣은 값이 null 이 아니면 통과
        for(ProductDTO product : productList) {
            System.out.println(product);
        }
    }

    @Test
    public void 상품_상세페이지용_조회_매퍼_테스트(){

        // given
        String productNo = "1";
        // when
        ProductDTO product = productMapper.selectOneProduct(productNo);
        // then
        assertNotNull(product);
    }

    @Test
    public void 상품_상세페이지용_옵션상품들_조회_매퍼_테스트() {

        // given
        String productName = "ROD01";
        // when
        List<ProductDTO> optionList = productMapper.selectOptionList(productName);
        // then
        assertNotNull(optionList);
    }

    @Test
    public void 상품_상세페이지용_관련상품들_조회_매퍼_테스트() {

        // given
        String productNo = "1";
        // when
        List<ProductDTO> refProductList = productMapper.selectRefProducts(productNo);
        // then
        assertNotNull(refProductList);
    }

    @Test
    public void 위시리스트_테이블에_추가_매퍼_테스트() {

        // given
        String userNo = "1";
        String productNo = "1";
        HashMap<String, String> wishMap  = new HashMap<>();
        wishMap.put("userNo", userNo);
        wishMap.put("productNo", productNo);
        // when
        int result = productMapper.insertWishProduct(wishMap);
        // then
        assertTrue(result >= 0? true:false);
    }

    @Test
    public void 장바구니_테이블에_추가_매퍼_테스트() {

        // given
        String userNo = "1";
        String productNo = "1";
        String amount = "1";
        HashMap<String, String> cartMap  = new HashMap<>();
        cartMap.put("userNo", userNo);
        cartMap.put("productNo", productNo);
        cartMap.put("amount", amount);
        // when
        int result = productMapper.insertCartProduct(cartMap);
        // then
        assertTrue(result >= 0? true:false);
    }
}