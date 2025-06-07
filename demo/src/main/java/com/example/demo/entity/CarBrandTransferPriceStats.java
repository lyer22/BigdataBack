package com.example.demo.entity;

public class CarBrandTransferPriceStats {
    private int id; // 主键ID
    private String brand; // 品牌
    private int transferCount; // 过户次数
    private float minPrice; // 最低价格
    private float q1Price; // 第一四分位数
    private float medianPrice; // 中位数
    private float q3Price; // 第三四分位数
    private float maxPrice; // 最高价格

    // 构造函数
    public CarBrandTransferPriceStats(int id, String brand, int transferCount, float minPrice, float q1Price, float medianPrice, float q3Price, float maxPrice) {
        this.id = id;
        this.brand = brand;
        this.transferCount = transferCount;
        this.minPrice = minPrice;
        this.q1Price = q1Price;
        this.medianPrice = medianPrice;
        this.q3Price = q3Price;
        this.maxPrice = maxPrice;
    }

    // Getter 和 Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getTransferCount() {
        return transferCount;
    }

    public void setTransferCount(int transferCount) {
        this.transferCount = transferCount;
    }

    public float getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(float minPrice) {
        this.minPrice = minPrice;
    }

    public float getQ1Price() {
        return q1Price;
    }

    public void setQ1Price(float q1Price) {
        this.q1Price = q1Price;
    }

    public float getMedianPrice() {
        return medianPrice;
    }

    public void setMedianPrice(float medianPrice) {
        this.medianPrice = medianPrice;
    }

    public float getQ3Price() {
        return q3Price;
    }

    public void setQ3Price(float q3Price) {
        this.q3Price = q3Price;
    }

    public float getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(float maxPrice) {
        this.maxPrice = maxPrice;
    }
}