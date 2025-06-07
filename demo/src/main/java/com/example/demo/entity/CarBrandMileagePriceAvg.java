package com.example.demo.entity;

public class CarBrandMileagePriceAvg {
    private int id; // 主键ID
    private String brand; // 品牌
    private float mileage; // 里程数（万公里）
    private float avgPrice; // 平均价格（万元）

    // 构造函数
    public CarBrandMileagePriceAvg(int id, String brand, float mileage, float avgPrice) {
        this.id = id;
        this.brand = brand;
        this.mileage = mileage;
        this.avgPrice = avgPrice;
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

    public float getMileage() {
        return mileage;
    }

    public void setMileage(float mileage) {
        this.mileage = mileage;
    }

    public float getAvgPrice() {
        return avgPrice;
    }

    public void setAvgPrice(float avgPrice) {
        this.avgPrice = avgPrice;
    }
}