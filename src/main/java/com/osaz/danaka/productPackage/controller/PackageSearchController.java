package com.osaz.danaka.productPackage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class PackageSearchController {

    @GetMapping("/package-search")
    public String packageSearchMain (){ return "package/package-search"; }


//    1 장비조합게시판





}
