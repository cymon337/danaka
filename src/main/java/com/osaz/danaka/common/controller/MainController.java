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

    // # update : 2023-01-18(최종수정)
    // # title : 메인페이지 연결, 상품 및 공지사항 조회
    // # author : 공성식
    // # description : 여러 URL 경로들을 index 메인페이지로 연결, 최근 상품 및 공지사항 조회하여 전달
    @GetMapping(value = {"/", "/main", "/main/index"})
    public ModelAndView main(ModelAndView mv) {

        List<NoticeDTO> noticeList = noticeService.selectTop5Notice();
        List<ProductDTO> productList = productService.selectTop4Product();

        if(noticeList != null && productList != null){
            mv.addObject("notices", noticeList);
            mv.addObject("productList", productList);
        }

        mv.setViewName("main/index");

        return mv;
    }

}

// # update : 2023-01-18(최종수정)
// # title :
// # author : 공성식
// # description :