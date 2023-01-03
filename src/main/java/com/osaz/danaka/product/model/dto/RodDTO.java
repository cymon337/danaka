package com.osaz.danaka.product.model.dto;

import java.sql.Date;

public class RodDTO extends ProductDTO{

    private String model;
    private String reelType;
    private int lineMin;
    private int lineMax;
    private int price;

    public RodDTO() {
    }

    public RodDTO(int productNo, String categoryCode, int brandNo, String productName, Date productRegDate, int stock, String model, String reelType, int lineMin, int lineMax, int price) {
        super(productNo, categoryCode, brandNo, productName, productRegDate, stock);
        this.model = model;
        this.reelType = reelType;
        this.lineMin = lineMin;
        this.lineMax = lineMax;
        this.price = price;
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
                "model='" + model + '\'' +
                ", reelType='" + reelType + '\'' +
                ", lineMin=" + lineMin +
                ", lineMax=" + lineMax +
                ", price=" + price +
                '}';
    }
}
