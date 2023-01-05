package com.osaz.danaka.product.model.dto;

import lombok.Data;

@Data
public class LineDTO{

    private String lineSize;
    private int price;

    @Override
    public String toString() {
        return "라인 크기 : " + lineSize;
    }
}