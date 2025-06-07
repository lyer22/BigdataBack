package com.example.demo.entity;

public class CarBrandPriceStats {
    private int id; // 主键ID
    private String brand; // 品牌
    private float avgNewPrice; // 平均新车价（万元）
    private float avgCurrentPrice; // 平均现价（万元）
    private float priceDropPercent; // 平均降幅（百分比）

    // 构造函数
    public CarBrandPriceStats(int id, String brand, float avgNewPrice, float avgCurrentPrice, float priceDropPercent) {
        this.id = id;
        this.brand = brand;
        this.avgNewPrice = avgNewPrice;
        this.avgCurrentPrice = avgCurrentPrice;
        this.priceDropPercent = priceDropPercent;
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

    public float getAvgNewPrice() {
        return avgNewPrice;
    }

    public void setAvgNewPrice(float avgNewPrice) {
        this.avgNewPrice = avgNewPrice;
    }

    public float getAvgCurrentPrice() {
        return avgCurrentPrice;
    }

    public void setAvgCurrentPrice(float avgCurrentPrice) {
        this.avgCurrentPrice = avgCurrentPrice;
    }

    public float getPriceDropPercent() {
        return priceDropPercent;
    }

    public void setPriceDropPercent(float priceDropPercent) {
        this.priceDropPercent = priceDropPercent;
    }
}