package com.osaz.danaka.product.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class imgPathDTO {

    private int imgNo;          // 이미지 번호
    private int productNo;      // 상품번호
    private String imgCategory; // 카테고리
    private String orgFileName; // 원본 파일명
    private String sysFileName; // 파일 저장명
    private String savePath;    // 저장경로
}
