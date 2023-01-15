package com.osaz.danaka.product.model.dto;

public class LineDTO {

    private int productNo;
    private String lineSize;
    private int price;

    public LineDTO() {
    }

    public LineDTO(int productNo, String lineSize, int price) {
        this.productNo = productNo;
        this.lineSize = lineSize;
        this.price = price;
    }

    public int getProductNo() {
        return productNo;
    }

    public void setProductNo(int productNo) {
        this.productNo = productNo;
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
                "productNo=" + productNo +
                ", lineSize='" + lineSize + '\'' +
                ", price=" + price +
                '}';
    }
}
