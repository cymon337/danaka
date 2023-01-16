package com.osaz.danaka.productPackage.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchProductDTO {


    public String productNo;   //상품번호
    public String brandName;
    public String productName;
    public String option1;
    public String option2;
    public String option3;
    public String option4;
    public String price;


}
