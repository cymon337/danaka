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


}
