package com.osaz.danaka.product.model.dto;

public class ReelDTO {

    private int productNo;
    private String model;
    private String reelType;
    private int price;

    public ReelDTO() {
    }

    public ReelDTO(int productNo, String model, String reelType, int price) {
        this.productNo = productNo;
        this.model = model;
        this.reelType = reelType;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "ReelDTO{" +
                "productNo=" + productNo +
                ", model='" + model + '\'' +
                ", reelType='" + reelType + '\'' +
                ", price=" + price +
                '}';
    }
}
