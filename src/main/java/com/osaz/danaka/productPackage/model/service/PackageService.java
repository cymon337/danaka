package com.osaz.danaka.productPackage.model.service;

import com.osaz.danaka.productPackage.model.dao.PackageMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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


}
