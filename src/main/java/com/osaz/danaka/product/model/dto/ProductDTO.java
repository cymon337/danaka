package com.osaz.danaka.product.model.dto;

import java.sql.Date;

public class ProductDTO {

    private int productNo;
    private String categoryCode;
    private int brandNo;
    private String productName;
    private Date productRegDate;
    private int stock;
    private int price;
    private String imgPath;

    public ProductDTO() {
    }

    public ProductDTO(int productNo, String categoryCode, int brandNo, String productName, Date productRegDate, int stock, int price, String imgPath) {
        this.productNo = productNo;
        this.categoryCode = categoryCode;
        this.brandNo = brandNo;
        this.productName = productName;
        this.productRegDate = productRegDate;
        this.stock = stock;
        this.price = price;
        this.imgPath = imgPath;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    @Override
    public String toString() {
        return "ProductDTO{" +
                "productNo=" + productNo +
                ", categoryCode='" + categoryCode + '\'' +
                ", brandNo=" + brandNo +
                ", productName='" + productName + '\'' +
                ", productRegDate=" + productRegDate +
                ", price=" + price +
                ", stock=" + stock +
                '}';
    }
}
