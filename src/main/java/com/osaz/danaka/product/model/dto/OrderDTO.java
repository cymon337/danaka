package com.osaz.danaka.product.model.dto;

import lombok.Data;

@Data
public class OrderDTO {

    private String userNo;
    private String productNo;
    private String orderId;
    private String packageId;
    private String address;
    private String amount;
    private String totPrice;
    private String orderRequest;
    private String payType;
//    private List<ProductDTO> productDTOList;
}
