package com.osaz.danaka.product.model.service;

import com.osaz.danaka.product.model.dao.ProductDAO;
import com.osaz.danaka.product.model.dto.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor = Exception.class)
public class ProductService {

    private final ProductDAO productDAO;

    @Autowired
    public ProductService(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    public List<ProductDTO> selectByCategory(String categoryCode){

        return productDAO.selectByCategory(categoryCode);
    };

    public ProductDTO selectOne(String productNo){

        return productDAO.selectOne(productNo);
    }

    public boolean registWishList(Map<String, String> parameter){

        int result = productDAO.registWishList(parameter);

        return (result > 0)? true:false;
    }
}
