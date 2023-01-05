package com.osaz.danaka.product.model.dto;

import lombok.Data;

@Data
public class RodDTO{

    private String model;
    private String reelType;
    private int lineMin;
    private int lineMax;
    private int price;

    @Override
    public String toString() {
        return  "모델명 : " + model + '\'' +
                ", 릴타입 : " + reelType + '\'' +
                ", 최소 라인 : " + lineMin +
                ", 최대 라인 : " + lineMax;
    }
}
