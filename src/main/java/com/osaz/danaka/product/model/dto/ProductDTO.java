package com.osaz.danaka.product.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    private int productNo;
    private String categoryCode;
    private String brandNo;
    private String brandName;
    private String productName;
    private Date productRegDate;
    private LineDTO line;
    private ReelDTO reel;
    private RodDTO rod;
    private int stock;
    private String tSavePath;
    private String dSavePath;
}
