package com.example.demo.service;

import com.example.demo.util.MySQLUtil;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class FuelTypeStatsService {

    public HashMap<String, Object> getFuelTypeStats() {
        HashMap<String, Object> response = new HashMap<>();
        List<HashMap<String, Object>> data = new ArrayList<>();

        String query = "SELECT fuel_type,count FROM fuel_type_stats";

        try (Connection connection = MySQLUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            int totalCount = 0;
            HashMap<String, Integer> fuelTypeCounts = new HashMap<>();

            // 遍历查询结果，统计每种燃料类型的数量
            while (resultSet.next()) {
                String fuelType = resultSet.getString("fuel_type");
                int count = resultSet.getInt("count");

                fuelTypeCounts.put(fuelType, count);
                totalCount += count;
            }

            // 计算每种燃料类型的百分比并构造返回数据
            for (String fuelType : fuelTypeCounts.keySet()) {
                HashMap<String, Object> fuelData = new HashMap<>();
                fuelData.put("type", fuelType);
                fuelData.put("count", fuelTypeCounts.get(fuelType));
                // 百分比保留一位小数
                double percentage = totalCount == 0 ? 0 : (fuelTypeCounts.get(fuelType) * 100.0) / totalCount;
                fuelData.put("percentage", String.format("%.1f", percentage));
                data.add(fuelData);
            }

            // 构造响应
            response.put("code", 200);
            response.put("data", data);

        } catch (Exception e) {
            e.printStackTrace();
            response.put("code", 500);
            response.put("message", "服务器内部错误");
        }

        return response;
    }
}