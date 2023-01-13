package com.osaz.danaka.member.model.dto;


import lombok.Data;


@Data
public class WishListDTO {

    private String wishNo;
    private String userNo;
    private String productNo;

    private String productName;
    private String savePath;
}
