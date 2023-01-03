package com.osaz.danaka.product.model.dto;

import java.sql.Date;

public class ReelDTO extends ProductDTO{

    private String model;
    private String reelType;
    private int price;

    public ReelDTO() {
    }

    public ReelDTO(int productNo, String categoryCode, int brandNo, String productName, Date productRegDate, int stock, String model, String reelType, int price) {
        super(productNo, categoryCode, brandNo, productName, productRegDate, stock);
        this.model = model;
        this.reelType = reelType;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "ReelDTO{" +
                "model='" + model + '\'' +
                ", reelType='" + reelType + '\'' +
                ", price=" + price +
                '}';
    }
}
