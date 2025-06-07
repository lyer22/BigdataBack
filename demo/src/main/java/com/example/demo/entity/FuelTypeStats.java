package com.example.demo.entity;



public class FuelTypeStats {

    private int id;  // 行键（如 fuelType + 时间戳）

    private String fuelType;
    
    private int count;
    
    // Getters & Setters


    // 必须有无参构造（Spark DataFrame转换依赖）
    public FuelTypeStats() {}

    // 带参构造（可选）
    public FuelTypeStats(int id, String fuelType, int count) {
        this.id = id;
        this.fuelType = fuelType;
        this.count = count;
    }

    // Getter & Setter（Spark依赖getter方法）
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    // 使用@Column注解明确字段映射（若集成JPA或MyBatis等框架）
    // @Column(name = "fuel_type")
    public String getFuelType() { return fuelType; }
    public void setFuelType(String fuelType) { this.fuelType = fuelType; }

    public int getCount() { return count; }
    public void setCount(int count) {
        if (count < 0) throw new IllegalArgumentException("统计值不能为负数");
        this.count = count;
    }

    @Override
    public String toString() {
        return String.format("FuelTypeStats{id=%d, fuelType='%s', count=%d}", id, fuelType, count);
    }
}
