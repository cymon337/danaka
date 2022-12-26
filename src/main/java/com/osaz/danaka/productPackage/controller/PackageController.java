package com.osaz.danaka.productPackage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class PackageController {

    @GetMapping("/package")
    public String packageMain(){ return "package/package"; }

//    1 상품명 검색
//    2 옵션 필터
//    3 상품 목록
//    4 견적 카트
//    5 선택상품삭제
//    6 전체삭제
//    7 장바구니담기
//    8 바로결제


}
