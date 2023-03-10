package com.osaz.danaka.productPackage.model.service;

import com.osaz.danaka.productPackage.model.dao.PackageMapper;
import com.osaz.danaka.productPackage.model.dto.SearchProductDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
public class PackageService {

    private final PackageMapper packageMapper;
    // @RequiredArgsConstructor 사용하면 아래 생략
//    @Autowired
//    public PackageService(PackageMapper packageMapper) { this.packageMapper = packageMapper; }

//    rod option service
    public String[] selectRodBrandNameOption() {
        String[] result = packageMapper.selectRodBrandNameOption();
        return result;
    }


    public String[] selectRodReelTypeOption() {
        String[] result = packageMapper.selectRodReelTypeOption();
        return result;
    }
    public String[] selectRodLineMinOption() {
        String[] result = packageMapper.selectRodLineMinOption();
        return result;
    }
    public String[] selectRodLineMaxOption() {
        String[] result = packageMapper.selectRodLineMaxOption();
        return result;
    }

//    reel option service
    public String[] selectReelBrandNameOption() {
        String[] result = packageMapper.selectReelBrandNameOption();
        return result;
    }

    public String[] selectReelTypeOption() {
        String[] result = packageMapper.selectReelTypeOption();
        return result;
    }

//    line option service
    public String[] selectLineBrandNameOption() {
        String[] result = packageMapper.selectLineBrandNameOption();
        return result;
    }

    public String[] selectLineSizeOption() {
        String[] result = packageMapper.selectLineSizeOption();
        return result;
    }


    public List<SearchProductDTO> selectProduct(String categoryCode) {
        log.info("selectProduct service init");

        List<SearchProductDTO> productList = packageMapper.selectProduct(categoryCode);

        return productList;
    }

    public List<SearchProductDTO> selectProductOption(String categoryCode, String categoryOption) {

        List<SearchProductDTO> productList = packageMapper.selectProductOption(categoryCode, categoryOption);

        return productList;
    }
}
