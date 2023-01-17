package com.osaz.danaka.member.model.dto;

import lombok.Data;

import java.sql.Date;

@Data
public class OrderDTO {

    private String orderNo;

    private String userNo;
    private String productNo;
    private String productName;
    private String orderId;
    private String amount;
    private String totPrice;
    private String orderRequest;
    private Date regDate;

    private String savePath;
}
