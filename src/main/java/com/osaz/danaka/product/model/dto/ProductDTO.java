package com.osaz.danaka.product.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    private int productNo;
    private String categoryCode;
    private String brandNo;
    private String brandName;
    private String productName;
    private RodDTO rod;
    private ReelDTO reel;
    private LineDTO line;
    private Date productRegDate;
    private int stock;
    private String savePath;

    private List<ProductDTO> fileList;

}
