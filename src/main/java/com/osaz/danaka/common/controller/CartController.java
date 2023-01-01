package com.osaz.danaka.common.controller;

import com.osaz.danaka.common.model.dto.CartDTO;
import com.osaz.danaka.common.model.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }


    @GetMapping({""})
    public String cartMain (){ return "common/cart"; }

// # update : 221229 작성중
// # title : selectAllCart 장바구니조회
// # author : 김찬영
// # description :  post method로 userNo 보내면 회원번호에 해당하는 장바구니목록 조회
    @PostMapping("selectAllCart")
    public ModelAndView selectAllCart(String userNo, ModelAndView mv){

        System.out.println("=== selectAllCart 컨트롤러 === userNo : " + userNo);

        List<CartDTO> cartList = cartService.selectAllCart(userNo);

        System.out.println("=== cartList ===");
        cartList.stream().forEach(cart -> System.out.println("cart = " + cart));

        mv.addObject("cartList", cartList);
        mv.setViewName("common/cart");

        return mv;
    }

    @PostMapping("registCart")
    public ModelAndView registCart(ModelAndView mv, List<CartDTO> cartList, RedirectAttributes rttr, Locale locale) throws Exception{
        cartService.registCart(cartList);
//        mv.setViewName("redirect:/common/cartList 화면구현 타임리프 만들기");
//        rttr.addFlashAttribute("successMessage", MessageSource.getMessage("selectAllCart", null, locale));

        return mv;
    }


}
