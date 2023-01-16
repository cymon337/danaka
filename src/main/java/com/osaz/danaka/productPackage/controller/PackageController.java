package com.osaz.danaka.productPackage.controller;

import com.osaz.danaka.productPackage.model.service.PackageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;

@Slf4j
@Controller
@RequestMapping("/package")
@RequiredArgsConstructor
public class PackageController {

    private final PackageService packageService;

    @GetMapping
    public ModelAndView packageMain(ModelAndView mv) {
        log.info("load category option={}","start");

//        rod option
        String[] rodBrands = packageService.selectRodBrandNameOption();
        String[] rodReelType = packageService.selectRodReelTypeOption();
        String[] rodLineMin = packageService.selectRodLineMinOption();
        String[] rodLineMax = packageService.selectRodLineMaxOption();

        mv.addObject("rodBrands", rodBrands);
        mv.addObject("rodReelType", rodReelType);
        mv.addObject("rodLineMin", rodLineMin);
        mv.addObject("rodLineMax", rodLineMax);
        log.info("rodBrands={}", Arrays.toString(rodBrands));
        log.info("rodReelType={}", Arrays.toString(rodReelType));
        log.info("rodLineMin={}", Arrays.toString(rodLineMin));
        log.info("rodLineMax={}", Arrays.toString(rodLineMax));

//      reel option
        String[] reelBrands = packageService.selectReelBrandNameOption();
        String[] reelType = packageService.selectReelTypeOption();
        mv.addObject("reelBrands", reelBrands);
        mv.addObject("reelType", reelType);
        log.info("reelBrands={}", Arrays.toString(reelBrands));
        log.info("reelType={}", Arrays.toString(reelType));

//      line option

        String[] lineBrands = packageService.selectLineBrandNameOption();
        String[] lineSize = packageService.selectLineSizeOption();
        mv.addObject("lineBrands", lineBrands);
        mv.addObject("lineSize", lineSize);
        log.info("lineBrands={}", Arrays.toString(lineBrands));
        log.info("lineSize={}", Arrays.toString(lineSize));

        mv.setViewName("package/package");
        return mv;
    }




}
