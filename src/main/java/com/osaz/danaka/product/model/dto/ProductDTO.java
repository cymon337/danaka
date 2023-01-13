package com.osaz.danaka.product.model.dto;

import lombok.Data;

import java.sql.Date;

@Data
public class ProductDTO {

    private int productNo;
    private String categoryCode;
    private String brandName;
    private String productName;
    private Date productRegDate;
    private LineDTO line;
    private ReelDTO reel;
    private RodDTO rod;
    private int stock;
    private String savePath;
}
