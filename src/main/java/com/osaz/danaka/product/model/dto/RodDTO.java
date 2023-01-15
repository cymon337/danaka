package com.osaz.danaka.product.model.dto;

public class RodDTO {

    private int productNo;
    private String model;
    private String reelType;
    private int lineMin;
    private int lineMax;
    private int price;

    public RodDTO() {
    }

    public RodDTO(int productNo, String model, String reelType, int lineMin, int lineMax, int price) {
        this.productNo = productNo;
        this.model = model;
        this.reelType = reelType;
        this.lineMin = lineMin;
        this.lineMax = lineMax;
        this.price = price;
    }

    public int getProductNo() {
        return productNo;
    }

    public void setProductNo(int productNo) {
        this.productNo = productNo;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getReelType() {
        return reelType;
    }

    public void setReelType(String reelType) {
        this.reelType = reelType;
    }

    public int getLineMin() {
        return lineMin;
    }

    public void setLineMin(int lineMin) {
        this.lineMin = lineMin;
    }

    public int getLineMax() {
        return lineMax;
    }

    public void setLineMax(int lineMax) {
        this.lineMax = lineMax;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "RodDTO{" +
                "productNo=" + productNo +
                ", model='" + model + '\'' +
                ", reelType='" + reelType + '\'' +
                ", lineMin=" + lineMin +
                ", lineMax=" + lineMax +
                ", price=" + price +
                '}';
    }
}
