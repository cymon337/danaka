package com.osaz.danaka.product.model.service;

import com.osaz.danaka.common.SelectCriteria;
import com.osaz.danaka.product.model.dao.ProductMapper;
import com.osaz.danaka.product.model.dto.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public int selectTotalCount(Map<String, String> searchMap) {

        return productMapper.selectTotalCount(searchMap);
    }

    public List<ProductDTO> selectListByCategory(SelectCriteria selectCriteria) {

        return productMapper.selectListByCategory(selectCriteria);
    }

    public ProductDTO selectOneProduct(String productNo) {

        return productMapper.selectOneProduct(productNo);
    }

    public List<ProductDTO> selectOptionList(String productName) {

        return productMapper.selectOptionList(productName);
    }

    public List<ProductDTO> selectRefProducts(String productNo) {

        return productMapper.selectRefProducts(productNo);
    }

    public boolean registWishList(Map<String, String> parameter) throws Exception {

        int result = productMapper.registWishList(parameter);

        if (result <= 0) {
            throw new Exception("위시리스트 등록 실패");
        }
        return (result > 0) ? true : false;
    }
}
