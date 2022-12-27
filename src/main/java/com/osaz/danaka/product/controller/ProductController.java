package com.osaz.danaka.product.controller;

import com.osaz.danaka.product.model.dto.ProductDTO;
import com.osaz.danaka.product.model.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/list")
    public ModelAndView selectByCategory(@RequestParam String categoryCode, ModelAndView mv){

        List<ProductDTO> productList = productService.selectByCategory(categoryCode);

        mv.addObject(productList);
        mv.setViewName("product/list");

        return mv;
    }

    @GetMapping("/list/item")
    public ModelAndView selectOne(@RequestParam String productNo, ModelAndView mv){

        ProductDTO product = productService.selectOne(productNo);

        mv.addObject(product);
        mv.setViewName("product/list/item");

        return mv;
    }
}
