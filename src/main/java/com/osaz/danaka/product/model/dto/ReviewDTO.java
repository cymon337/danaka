package com.osaz.danaka.product.model.dto;

import lombok.Data;

import java.sql.Date;

@Data
public class ReviewDTO {

    private int reviewNo;
    private int userNo;
    private String productName;
    private String userNickname;
    private String reviewTitle;
    private String reviewBody;
    private Date regDate;
}
