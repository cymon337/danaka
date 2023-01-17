package com.osaz.danaka.common.controller;

import com.osaz.danaka.notice.model.dto.NoticeDTO;
import com.osaz.danaka.notice.model.service.NoticeService;
import com.osaz.danaka.product.model.dto.ProductDTO;
import com.osaz.danaka.product.model.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/")
public class MainController {

    private final NoticeService noticeService;
    private final ProductService productService;

    @Autowired
    public MainController(NoticeService noticeService, ProductService productService) {
        this.noticeService = noticeService;
        this.productService = productService;
    }

    /* 여러 경로들을 index페이지로 연결, 입력했을때 리턴값으로 이동 */
    @GetMapping(value = {"/", "/main", "/main/index"})
    public ModelAndView main(ModelAndView mv) {

        List<NoticeDTO> noticeList = noticeService.selectTop5Notice();
        List<ProductDTO> productList = productService.selectTop4Product();

        if(noticeList != null && productList != null){
            mv.addObject("notices", noticeList);
            mv.addObject("products", productList);
        }

        mv.setViewName("main/index");

        return mv;
    }

}

