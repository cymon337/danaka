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
    public String[] selectBrandNameOption() {
        String[] result = packageMapper.selectBrandNameOption();
        return result;
    }


    public String[] selectReelTypeOption() {
        String[] result = packageMapper.selectReelTypeOption();
        return result;
    }
    public String[] selectLineMinOption() {
        String[] result = packageMapper.selectLineMinOption();
        return result;
    }
    public String[] selectLineMaxOption() {
        String[] result = packageMapper.selectLineMaxOption();
        return result;
    }
}
