package com.osaz.danaka.product.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductCartDTO {

    private int cartNo;
    private int productNo;   //상품번호
    private String categoryCode;
    private String brandName;
    private String productName;
    private RodDTO rod;
    private ReelDTO reel;
    private LineDTO line;
    private int amount;  //수량
    private String packageId;   //패키지ID
    private String savePath;



}
