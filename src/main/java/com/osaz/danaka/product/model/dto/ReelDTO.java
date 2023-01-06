package com.osaz.danaka.product.model.dto;

import lombok.Data;

@Data
public class ReelDTO{

    private String model;
    private String reelType;
    private int price;

    @Override
    public String toString() {
        return "모델명 : " + model + ", 릴타입 : " + reelType;
    }
}
