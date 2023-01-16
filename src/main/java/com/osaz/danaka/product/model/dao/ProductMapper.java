package com.osaz.danaka.product.model.dao;

import com.osaz.danaka.common.SelectCriteria;
import com.osaz.danaka.product.model.dto.OrderDTO;
import com.osaz.danaka.product.model.dto.ProductDTO;
import com.osaz.danaka.product.model.dto.imgPathDTO;
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
    
    // 위시리스트 테이블에 추가
    int insertWishProduct(Map<String, String> wishMap);
    
    // 장바구니 테이블에 추가
    int insertCartProduct(Map<String, String> cartMap);

    // 구매내역 테이블에 추가
    int insertOrder(OrderDTO order);

    void insertProduct(ProductDTO product);

    void insertCategory(HashMap<String, String> categoryMap);

    String selectProductLastNum();

    void insertImgFile(imgPathDTO imgFile);
}
