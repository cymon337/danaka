package com.osaz.danaka.productPackage.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReelDTO {
    // VIEW_PRODUCT_REEL 조회

    public String productNo;
    public String categoryCode;
    public String brandNo;
    public String productName;
    public String reelModel;
    public String reelType;
    public String reelPrice;
    public String productRegDate;
    public String stock;
}
