package com.osaz.danaka.common.controller;

import com.osaz.danaka.common.model.dto.CartDTO;
import com.osaz.danaka.common.model.dto.CartProductDTO;
import com.osaz.danaka.common.model.service.CartService;
import com.osaz.danaka.member.model.dto.MemberDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    @Autowired  //cosntructor DI
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }


//    @GetMapping  // 장바구니 페이지
//    public String cartMain (){ return "common/cart"; }

// # update : 221229 작성중
// # title : 장바구니페이지 - userNo 조회해서 리스트 출력
// # author : 김찬영
// # description :  post method로 userNo 보내면 회원번호에 해당하는 장바구니목록 조회
    @GetMapping
    public ModelAndView cartMain(ModelAndView mv, @AuthenticationPrincipal MemberDTO member) throws Exception {

        // 회원별 T_CART 내역 조회

//        String userNo;
//
//        userNo = member.getUserNo(); 유저넘버 널일경우 처리?

        String userNo = "1";
        List<CartProductDTO> cartList = cartService.selectAllCart(userNo);
        cartList.stream().forEach(cart -> log.info("cart ={}", cart));

        mv.addObject("cartList", cartList);
        mv.setViewName("common/cart");

        return mv;
    }

    @ResponseBody
    @PostMapping("regist-cart")
    public ModelAndView registCart(ModelAndView mv, List<CartDTO> cartList) throws Exception{
        cartService.registCart(cartList);

        mv.addObject("sucess");
        mv.setViewName("common/fragment/success");

        return mv;
    }


    @ResponseBody
    @PostMapping("update-db")
    public ModelAndView updateCart(ModelAndView mv, String cartNo, String amount) throws Exception{
        log.info("updateDB={}", "start");
        log.info("cartNo={}", cartNo);
        log.info("amount={}", amount);

        Boolean result = cartService.updateCart(cartNo, amount);

        mv.addObject(result);
        mv.setViewName("common/fragment/success");

        return mv;

    }

    @ResponseBody
    @PostMapping("delete-db")
    public ModelAndView deleteCart(ModelAndView mv, String[] cartNoList) throws Exception{
        log.info("deleteDB={}", "start");

        for (String cartNo:cartNoList) {
            log.info("cartNoList={}", cartNo);
        };
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("cartNo",cartNoList);

        Boolean result = cartService.deleteCart(paramMap);

        mv.addObject(result);
        mv.setViewName("common/fragment/success");

        return mv;

    }



}
