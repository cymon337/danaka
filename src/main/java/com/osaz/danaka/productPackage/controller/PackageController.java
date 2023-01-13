package com.osaz.danaka.productPackage.controller;

import com.osaz.danaka.productPackage.model.service.PackageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;

@Slf4j
@Controller
@RequestMapping("/package")
@RequiredArgsConstructor
public class PackageController {

    private final PackageService packageService;

    @GetMapping
    public String packageMain(){ return "package/package"; }

//    1 상품명 검색 (카테고리 조건에 따라 로드, 릴, 라인 으로 검색)

//    2 옵션 필터 (카테고리 조건에 따라 로드, 릴, 라인 옵션 호출)
    @RequestMapping(value = "/rod-option", method = RequestMethod.POST)
    public ModelAndView selectRodOption(ModelAndView mv, String category) {
        log.info("selectRodOption={}", category);
        String[] brands = packageService.selectBrandNameOption();
        String[] rodReelType = packageService.selectReelTypeOption();
        String[] lineMin = packageService.selectLineMinOption();
        String[] lineMax = packageService.selectLineMaxOption();

        mv.addObject("brands", brands);
        mv.addObject("rodReelType", rodReelType);
        mv.addObject("lineMin", lineMin);
        mv.addObject("lineMax", lineMax);
        log.info("brands={}", Arrays.toString(brands));
        log.info("rodReelType={}", Arrays.toString(rodReelType));
        log.info("lineMin={}", Arrays.toString(lineMin));
        log.info("lineMax={}", Arrays.toString(lineMax));

        mv.setViewName("/common/fragment/package-fragment :: rodOption");
                        //반환할 페이지 주소 :: fragment id 타임리프는 th:fragment="id값"
        return mv;
    }
//    3 상품 목록

//    4 견적 카트

//    5 선택상품삭제

//    6 전체삭제

//    7 장바구니담기

//    8 바로결제


}
