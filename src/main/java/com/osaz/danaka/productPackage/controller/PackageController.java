package com.osaz.danaka.productPackage.controller;

import com.osaz.danaka.productPackage.model.service.PackageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/package")
@RequiredArgsConstructor
public class PackageController {

    private final PackageService packageService;

    @GetMapping
    public String packageMain(){ return "package/package"; }

//    1 상품명 검색 (카테고리 조건에 따라 로드, 릴, 라인 으로 검색)

//    2 옵션 필터 (카테고리 조건에 따라 로드, 릴, 라인 옵션 호출)

//    3 상품 목록

//    4 견적 카트

//    5 선택상품삭제

//    6 전체삭제

//    7 장바구니담기

//    8 바로결제


}
