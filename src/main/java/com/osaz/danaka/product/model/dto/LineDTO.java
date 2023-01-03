package com.osaz.danaka.product.model.dto;

import java.sql.Date;

public class LineDTO extends ProductDTO{

    private String lineSize;
    private int price;

    public LineDTO() {
    }

    public LineDTO(int productNo, String categoryCode, int brandNo, String productName, Date productRegDate, int stock, String lineSize, int price) {
        super(productNo, categoryCode, brandNo, productName, productRegDate, stock);
        this.lineSize = lineSize;
        this.price = price;
    }

    public String getLineSize() {
        return lineSize;
    }

    public void setLineSize(String lineSize) {
        this.lineSize = lineSize;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "LineDTO{" +
                "lineSize='" + lineSize + '\'' +
                ", price=" + price +
                '}';
    }
}