package com.osaz.danaka.common.controller;

import com.osaz.danaka.common.model.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @PostMapping("selectAllCart")
    public ModelAndView selectAllCart(ModelAndView mv, int userNo, RedirectAttributes rttr, Locale locale) throws Exception{
        cartService.selectAllCart(userNo);
//        mv.setViewName("redirect:/common/cartList 화면구현 타임리프 만들기");
//        rttr.addFlashAttribute("successMessage", MessageSource.getMessage("selectAllCart", null, locale));

        return mv;
    }



}
