package com.example.demo.entity;

public class BrandAvgPrice {
    private int id; // 主键ID
    private String brand; // 品牌
    private float avgPrice; // 平均现价（万）

    // 构造函数
    public BrandAvgPrice(int id, String brand, float avgPrice) {
        this.id = id;
        this.brand = brand;
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

    public float getAvgPrice() {
        return avgPrice;
    }

    public void setAvgPrice(float avgPrice) {
        this.avgPrice = avgPrice;
    }
}
