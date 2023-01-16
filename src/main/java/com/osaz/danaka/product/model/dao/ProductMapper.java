package com.osaz.danaka.product.model.dao;

import com.osaz.danaka.product.model.dto.ProductDTO;
import com.osaz.danaka.product.model.dto.imgPathDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper
public interface ProductMapper {

    List<ProductDTO> selectByCategory(String categoryCode);

    ProductDTO selectOne(String productNo);

    int registWishList(Map<String, String> parameter);


    void insertProduct(ProductDTO product);

    void insertCategory(HashMap<String, String> categoryMap);

    String selectProductLastNum();

    void insertImgFile(imgPathDTO imgFile);

}
