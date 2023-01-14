package com.osaz.danaka.product.model.service;

import com.osaz.danaka.common.SelectCriteria;
import com.osaz.danaka.product.model.dao.ProductMapper;
import com.osaz.danaka.product.model.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor = Exception.class)
public class ProductService {

    private final ProductMapper productMapper;

    @Autowired
    public ProductService(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }

    // 페이징 처리용 총 상품 개수 조회
    public int selectTotalCount(Map<String, String> searchMap) {

        return productMapper.selectTotalCount(searchMap);
    }

    // 카테고리별 상품들 조회
    public List<ProductDTO> selectListByCategory(SelectCriteria selectCriteria) {

        return productMapper.selectListByCategory(selectCriteria);
    }

    // 상품 상세페이지/구매페이지용 상품 조회
    public ProductDTO selectOneProduct(String productNo) {

        return productMapper.selectOneProduct(productNo);
    }

    // 상품 상세페이지용 해당하는 상품의 옵션 상품들 조회
    public List<ProductDTO> selectOptionList(String productName) {

        return productMapper.selectOptionList(productName);
    }

    // 상품 상세페이지용 관련상품들 조회
    public List<ProductDTO> selectRefProducts(String productNo) {

        return productMapper.selectRefProducts(productNo);
    }

    // 상품 구매페이지용 장바구니 목록 조회
    public List<ProductCartDTO> selectCartList(HashMap<String, Object> map) {

        return productMapper.selectCartList(map);
    }

    // 위시리스트 테이블에 추가
    public boolean insertWishProduct(Map<String, String> wishMap) throws Exception {

        int result = productMapper.insertWishProduct(wishMap);

        if (result <= 0) {
            throw new Exception("위시리스트 등록 실패");
        }
        return (result > 0) ? true : false;
    }

    // 장바구니 테이블에 추가
    public boolean insertCartProduct(Map<String, String> cartMap) throws Exception {

        int result = productMapper.insertCartProduct(cartMap);

        if (result <= 0) {
            throw new Exception("위시리스트 등록 실패");
        }
        return (result > 0) ? true : false;
    }

    // 구매내역 테이블에 추가
    public boolean insertOrder(List<OrderDTO> orderList) throws Exception {

        int result = productMapper.insertOrder(orderList);

        if (result <= 0) {
            throw new Exception("구매 실패");
        }
        return (result > 0) ? true : false;
    }

    // 상품 구매했는지 조회
    public boolean selectOrder(Map<String, String> orderMap) {

        int result = productMapper.selectOrder(orderMap);

        return (result > 0)? true : false;
    }

    // 상품 review 총 개수 조회
    public int selectTotalReviewCount(String productNo) {

        return productMapper.selectTotalReviewCount(productNo);
    }

    // 상품 qna 총 개수 조회
    public int selectTotalQnaCount(String productNo) {

        return productMapper.selectTotalQnaCount(productNo);
    }

    // 상품 리뷰 조회
    public List<ReviewDTO> selectReviewList(SelectCriteria selectCriteria) {

        return productMapper.selectReviewList(selectCriteria);
    }

    // 상품 문의 조회
    public List<QnaDTO> selectQnaList(SelectCriteria selectCriteria) {

        return productMapper.selectQnaList(selectCriteria);
    }
}
