package com.osaz.danaka.productPackage.model.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface PackageMapper {

// rod option mapper
    String[] selectRodBrandNameOption();

    String[] selectRodReelTypeOption();

    String[] selectRodLineMinOption();

    String[] selectRodLineMaxOption();

// reel option mapper
    String[] selectReelBrandNameOption();

    String[] selectReelTypeOption();

// line option mapper
    String[] selectLineBrandNameOption();

    String[] selectLineSizeOption();





}
