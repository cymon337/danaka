package com.osaz.danaka.product.model.dao;

import com.osaz.danaka.product.model.dto.ProductDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ProductMapper {

    List<ProductDTO> selectByCategory(String categoryCode);

    ProductDTO selectOne(String productNo);

    int registWishList(Map<String, String> parameter);

}
