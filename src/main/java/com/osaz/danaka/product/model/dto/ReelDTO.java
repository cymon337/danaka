package com.osaz.danaka.product.model.dto;

import java.sql.Date;

public class ReelDTO extends ProductDTO{

    private String model;
    private String reelType;

    public ReelDTO() {
    }

    public ReelDTO(int productNo, String categoryCode, int brandNo, String productName, Date productRegDate, int stock, int price, String imgPath, String model, String reelType) {
        super(productNo, categoryCode, brandNo, productName, productRegDate, stock, price, imgPath);
        this.model = model;
        this.reelType = reelType;
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

    @Override
    public String toString() {
        return "ReelDTO{" +
                "model='" + model + '\'' +
                ", reelType='" + reelType + '\'' +
                '}';
    }
}
