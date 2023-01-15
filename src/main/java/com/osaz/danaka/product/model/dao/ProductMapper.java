package com.osaz.danaka.product.model.dao;

import com.osaz.danaka.common.SelectCriteria;
import com.osaz.danaka.product.model.dto.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper
public interface ProductMapper {

    // 페이징 처리용 총 상품 개수 조회
    int selectTotalCount(Map<String, String> searchMap);
    
    // 카테고리별 상품들 조회
    List<ProductDTO> selectListByCategory(SelectCriteria selectCriteria);

    // 상품 상세페이지용 상품 조회
    ProductDTO selectOneProduct(String productNo);
    
    // 상품 상세페이지용 해당하는 상품의 옵션 상품들 조회
    List<ProductDTO> selectOptionList(String productName);
    
    // 상품 상세페이지용 관련상품들 조회
    List<ProductDTO> selectRefProducts(String productNo);

    List<ProductCartDTO> selectCartList(HashMap<String, Object> map);

    // 위시리스트 테이블에 추가
    int insertWishProduct(Map<String, String> wishMap);
    
    // 장바구니 테이블에 추가
    int insertCartProduct(Map<String, String> cartMap);

    // 구매내역 테이블에 추가
    int insertOrder(List<OrderDTO> orderList);

    // 상품 구매했는지 조회
    HashMap<String, String> selectOrder(Map<String, String> orderMap);

    // 상품 리뷰 총 개수 조회
    int selectTotalReviewCount(String productNo);

    // 상품 qna 총 개수 조회
    int selectTotalQnaCount(String productNo);

    // 상품 리뷰 조회
    List<ReviewDTO> selectReviewList(SelectCriteria selectCriteria);

    // 상품 qna 조회
    List<QnaDTO> selectQnaList(SelectCriteria selectCriteria);

    // 상품 리뷰 등록
    int insertReview(ReviewDTO review);
}
