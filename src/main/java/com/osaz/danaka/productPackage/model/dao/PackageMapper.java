package com.osaz.danaka.productPackage.model.dao;

import com.osaz.danaka.productPackage.model.dto.SearchProductDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

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

// search product mapper
    List<SearchProductDTO> selectProduct(String categoryCode);

// search prouct option mapper
    List<SearchProductDTO> selectProductOption(String categoryCode, String categoryOption);


// search product mapper



}
