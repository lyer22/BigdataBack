package com.example.demo.entity;

public class BrandStats {
    private int id; // 主键ID
    private String brand; // 品牌
    private int count; // 数量

    // 构造函数
    public BrandStats(int id, String brand, int count) {
        this.id = id;
        this.brand = brand;
        this.count = count;
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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}