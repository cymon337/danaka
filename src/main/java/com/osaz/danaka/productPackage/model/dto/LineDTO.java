package com.osaz.danaka.productPackage.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LineDTO {
    // VIEW_PRODUCT_LINE 조회

    public String productNo;
    public String categoryCode;
    public String brandNo;
    public String productName;
    public String lineSize;
    public String linePrice;
    public String productRegDate;
    public String stock;
}
