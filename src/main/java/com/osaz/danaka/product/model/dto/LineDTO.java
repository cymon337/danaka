package com.osaz.danaka.product.model.dto;

import java.sql.Date;

public class LineDTO extends ProductDTO{

    private String lineSize;

    public LineDTO() {
    }

    public LineDTO(int productNo, String categoryCode, int brandNo, String productName, Date productRegDate, int stock, int price, String imgPath, String lineSize) {
        super(productNo, categoryCode, brandNo, productName, productRegDate, stock, price, imgPath);
        this.lineSize = lineSize;
    }

    public String getLineSize() {
        return lineSize;
    }

    public void setLineSize(String lineSize) {
        this.lineSize = lineSize;
    }

    @Override
    public String toString() {
        return "LineDTO{" +
                "lineSize='" + lineSize + '\'' +
                '}';
    }
}