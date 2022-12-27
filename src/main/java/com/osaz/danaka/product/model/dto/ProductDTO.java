package com.osaz.danaka.product.model.dto;

import java.sql.Date;

public class ProductDTO {

    private int productNo;
    private String categoryCode;
    private int brandNo;
    private String productName;
    private RodDTO rod;
    private ReelDTO reel;
    private LineDTO line;
    private Date productRegDate;
    private int stock;

    public ProductDTO() {
    }

    public ProductDTO(int productNo, String categoryCode, int brandNo, String productName, RodDTO rod, ReelDTO reel, LineDTO line, Date productRegDate, int stock) {
        this.productNo = productNo;
        this.categoryCode = categoryCode;
        this.brandNo = brandNo;
        this.productName = productName;
        this.rod = rod;
        this.reel = reel;
        this.line = line;
        this.productRegDate = productRegDate;
        this.stock = stock;
    }

    public int getProductNo() {
        return productNo;
    }

    public void setProductNo(int productNo) {
        this.productNo = productNo;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public int getBrandNo() {
        return brandNo;
    }

    public void setBrandNo(int brandNo) {
        this.brandNo = brandNo;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public RodDTO getRodDTO() {
        return rod;
    }

    public void setRodDTO(RodDTO rodDTO) {
        this.rod = rod;
    }

    public ReelDTO getReelDTO() {
        return reel;
    }

    public void setReelDTO(ReelDTO reelDTO) {
        this.reel = reel;
    }

    public LineDTO getLineDTO() {
        return line;
    }

    public void setLineDTO(LineDTO line) {
        this.line = line;
    }

    public Date getProductRegDate() {
        return productRegDate;
    }

    public void setProductRegDate(Date productRegDate) {
        this.productRegDate = productRegDate;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "ProductDTO{" +
                "productNo=" + productNo +
                ", categoryCode='" + categoryCode + '\'' +
                ", brandNo=" + brandNo +
                ", productName='" + productName + '\'' +
                ", rod=" + rod +
                ", reelDTO=" + reel +
                ", lineDTO=" + line +
                ", productRegDate=" + productRegDate +
                ", stock=" + stock +
                '}';
    }
}
