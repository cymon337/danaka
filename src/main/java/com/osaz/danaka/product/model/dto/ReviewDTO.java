package com.osaz.danaka.product.model.dto;

import lombok.Data;

import java.sql.Date;

@Data
public class ReviewDTO {

    private String reviewNo;
    private String userNo;
    private String productNo;
    private String userNickname;
    private String reviewTitle;
    private String reviewBody;
    private Date buyDate;
    private Date regDate;
}
