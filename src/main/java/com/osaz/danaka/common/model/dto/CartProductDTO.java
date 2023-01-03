package com.osaz.danaka.common.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartProductDTO {

    public String productNo;
    public String brandName;
    public String productName;
    public String categoryName;
    public String option1;
    public String option2;
    public String option3;
    public String option4;
    public String price;


}
