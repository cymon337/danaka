package com.osaz.danaka.product.model.dao;

import com.osaz.danaka.common.SelectCriteria;
import com.osaz.danaka.product.model.dto.ProductDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ProductMapper {

    int selectTotalCount(Map<String, String> searchMap);

    List<ProductDTO> selectListByCategory(SelectCriteria selectCriteria);

    ProductDTO selectOneProduct(String productNo);

    List<ProductDTO> selectOptionList(String productNo);

    int registWishList(Map<String, String> parameter);

}
