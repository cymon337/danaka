package com.osaz.danaka.productPackage.model.service;

import com.osaz.danaka.config.DanakaApplication;
import com.osaz.danaka.config.MybatisConfig;
import com.osaz.danaka.productPackage.model.dao.PackageMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;


@Slf4j
@SpringBootTest
@ContextConfiguration(classes = {DanakaApplication.class, MybatisConfig.class})
class PackageServiceTest {

    @Autowired
    PackageMapper packageMapper;

    @Test
    @Transactional
    void 로드옵션조회() {
        String[] result1 = packageMapper.selectRodBrandNameOption();
        String[] result2 = packageMapper.selectReelTypeOption();
        String[] result3 = packageMapper.selectRodLineMinOption();
        String[] result4 = packageMapper.selectRodLineMaxOption();
        log.info("selectBrandNameOption={}" , Arrays.toString(result1));
        log.info("selectBrandNameOption={}" , Arrays.toString(result2));
        log.info("selectBrandNameOption={}" , Arrays.toString(result3));
        log.info("selectBrandNameOption={}" , Arrays.toString(result4));

    }
}