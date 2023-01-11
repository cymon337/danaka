package com.osaz.danaka.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/")
public class MainController {

    @GetMapping(value={"/", "/main", "/index"})
    public String main(HttpServletRequest request){
//
//        HttpSession session = request.getSession();
//        session.setAttribute("userNo", "1");
//        session.setAttribute("userName", "정그노");
//        session.setAttribute("phone", "010-1111-2222");
        return "main/index";
    }
}