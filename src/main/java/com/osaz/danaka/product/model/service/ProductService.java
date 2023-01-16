package com.osaz.danaka.product.model.service;

import com.osaz.danaka.product.model.dao.ProductMapper;
import com.osaz.danaka.product.model.dto.ProductDTO;
import com.osaz.danaka.product.model.dto.imgPathDTO;
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

    public List<ProductDTO> selectByCategory(String categoryCode){

        return productMapper.selectByCategory(categoryCode);
    };

    public ProductDTO selectOne(String productNo){

        return productMapper.selectOne(productNo);
    }

    public boolean registWishList(Map<String, String> parameter) throws Exception{

        int result = productMapper.registWishList(parameter);

        if (result <= 0) {
            throw new Exception("위시리스트 등록 실패");
        }
        return (result > 0)? true:false;
    }


    public void insertProduct(ProductDTO product) {
        productMapper.insertProduct(product);
    }

    public void insertCategory(HashMap<String, String> categoryMap) {
        productMapper.insertCategory(categoryMap);
    }

    public String selectProductLastNum() {
        return productMapper.selectProductLastNum();
    }

    public void saveFile(imgPathDTO imgFile) {
        productMapper.insertImgFile(imgFile);
    }
}
