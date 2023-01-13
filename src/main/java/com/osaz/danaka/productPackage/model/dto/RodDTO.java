package com.osaz.danaka.productPackage.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RodDTO {
    // VIEW_PRODUCT_ROD 조회
    public String productNo;
    public String categoryCode;
    public String brandNo;
    public String productName;
    public String rodModel;
    public String reelType;
    public String lineMin;
    public String lineMax;
    public String rodPrice;
    public Date productRegDate;
    public String stock;
}
