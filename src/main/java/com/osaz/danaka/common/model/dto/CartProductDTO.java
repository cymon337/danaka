package com.osaz.danaka.common.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartProductDTO {

    public String cartNo;  //장바구니번호
    public String userNo;  //회원번호
    public String productNo;   //상품번호
    public String amount;  //수량
    public String packageId;   //패키지ID
    public String brandName;
    public String productName;
    public String categoryName;
    public String option1;
    public String option2;
    public String option3;
    public String option4;
    public String price;


}
