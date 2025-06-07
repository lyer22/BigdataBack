package com.example.demo.entity;

public class CarBrandAgePriceAvg {
    private int id; // 主键ID
    private String brand; // 品牌
    private float carAge; // 车龄（年）
    private float avgPrice; // 平均价格（万）

    // 构造函数
    public CarBrandAgePriceAvg(int id, String brand, float carAge, float avgPrice) {
        this.id = id;
        this.brand = brand;
        this.carAge = carAge;
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

    public float getCarAge() {
        return carAge;
    }

    public void setCarAge(float carAge) {
        this.carAge = carAge;
    }

    public float getAvgPrice() {
        return avgPrice;
    }

    public void setAvgPrice(float avgPrice) {
        this.avgPrice = avgPrice;
    }
}