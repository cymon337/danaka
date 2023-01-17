package com.osaz.danaka.product.model.dao;

import com.osaz.danaka.common.paging.SelectCriteria;
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

    // 상품 상세페이지용 찜 체크 조회
    int selectWishCheck(HashMap<String, String> wishCheckMap);

    // 상품 상세페이지용 해당하는 상품의 옵션 상품들 조회
    List<ProductDTO> selectOptionList(String productName);

    // 상품 상세페이지용 관련상품들 조회
    List<ProductDTO> selectRefProducts(String productNo);

    // 상품 구매페이지용 카트 조회
    List<ProductCartDTO> selectCartList(HashMap<String, Object> map);

    // 위시리스트 테이블에 추가
    int insertWishProduct(Map<String, String> wishMap);

    // 위시리스트 테이블에서 제거
    int deleteWish(Map<String, String> wishMap);

    // 장바구니 테이블에 추가
    int insertCartProduct(Map<String, String> cartMap);

    // 구매내역 테이블에 추가
    int insertOrder(List<OrderDTO> orderList);

    // 상품 구매했는지 조회
    HashMap<String, String> selectOrder(Map<String, String> orderMap);

    // 상품 리뷰 총 개수 조회
    int selectTotalReviewCount(Map<String, String> map);

    // 상품 qna 총 개수 조회
    int selectTotalQnaCount(String productNo);

    // 상품 리뷰 조회
    List<ReviewDTO> selectReviewList(SelectCriteria selectCriteria);

    // 상품 qna 조회
    List<QnaDTO> selectQnaList(SelectCriteria selectCriteria);

    // 상품 리뷰 등록
    int insertReview(ReviewDTO review);

    // 상품 문의 등록
    int insertQna(QnaDTO qna);

    // 상품 리뷰 삭제
    int deleteReview(String reviewNo);

    // 상품 문의 삭제
    int deleteQna(String qnaNo);

    // 상품 리뷰 수정
    int updateReview(HashMap<String, String> updateMap);

    // 상품 문의 수정
    int updateQna(HashMap<String, String> updateMap);

    ///////////////성식 추가///////////////////
    /* 상품 등록 */
    void insertProduct(ProductDTO product);

    /* 상품 카테고리 등록 */
    void insertCategory(HashMap<String, String> categoryMap);

    /* 상품 테이블 마지막 상품 번호 조회 */
    String selectProductLastNum();

    /* 첨부파일 이미지 테이블에 저장 */
    void insertImgFile(imgPathDTO imgFile);

    /* 메인페이지용 최근등록 상품 4개 조회 */
    List<ProductDTO> selectTop4Product();

    // 메인페이지 상품 검색 페이징
    int selectMainTotalCount(Map<String, String> searchMap);

    // 메인페이지 상품 검색 조회
    List<ProductDTO> selectListByMainPage(SelectCriteria selectCriteria);
}
