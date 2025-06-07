package com.example.demo.entity;

public class BrandMileageAgePublishStats {
    private int id; // 主键ID
    private String brand; // 品牌
    private float mileage_float; // 里程数（万公里）
    private float car_age; // 车龄（年）
    private int avg_publish_days; // 平均已发布天数

    // 构造函数
    public BrandMileageAgePublishStats(int id, String brand, float mileage, float carAge, int avgPublishDays) {
        this.id = id;
        this.brand = brand;
        this.mileage_float = mileage;
        this.car_age = carAge;
        this.avg_publish_days = avgPublishDays;
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
        return mileage_float;
    }

    public void setMileage(float mileage) {
        this.mileage_float = mileage;
    }

    public float getCarAge() {
        return car_age;
    }

    public void setCarAge(float carAge) {
        this.car_age = carAge;
    }

    public int getAvgPublishDays() {
        return avg_publish_days;
    }

    public void setAvgPublishDays(int avgPublishDays) {
        this.avg_publish_days = avgPublishDays;
    }
}