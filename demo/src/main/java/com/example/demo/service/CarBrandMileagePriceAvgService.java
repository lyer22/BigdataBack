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
public class CarBrandMileagePriceAvgService {

    public HashMap<String, Object> getMileagePriceData(Float maxMileage, int page, int size) {
        HashMap<String, Object> response = new HashMap<>();
        List<HashMap<String, Object>> data = new ArrayList<>();

        String countQuery = "SELECT COUNT(*) FROM car_brand_mileage_price_avg WHERE 1=1";
        String query = "SELECT brand, mileage, avg_price FROM car_brand_mileage_price_avg WHERE 1=1";
        List<Object> params = new ArrayList<>();

        // 动态拼接查询条件
        if (maxMileage != null) {
            countQuery += " AND mileage <= ?";
            query += " AND mileage <= ?";
            params.add(maxMileage);
        }
        query += " LIMIT ? OFFSET ?";

        try (Connection connection = MySQLUtil.getConnection()) {
            // 查询总数
            int total = 0;
            try (PreparedStatement countStmt = connection.prepareStatement(countQuery)) {
                if (maxMileage != null) {
                    countStmt.setFloat(1, maxMileage);
                }
                ResultSet countRs = countStmt.executeQuery();
                if (countRs.next()) {
                    total = countRs.getInt(1);
                }
            }

            // 查询分页数据
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                int paramIndex = 1;
                for (Object param : params) {
                    preparedStatement.setObject(paramIndex++, param);
                }
                preparedStatement.setInt(paramIndex++, size);
                preparedStatement.setInt(paramIndex, (page - 1) * size);

                ResultSet resultSet = preparedStatement.executeQuery();

                // 遍历查询结果
                while (resultSet.next()) {
                    HashMap<String, Object> record = new HashMap<>();
                    record.put("brand", resultSet.getString("brand"));
                    record.put("mileage", resultSet.getFloat("mileage"));
                    record.put("price", resultSet.getFloat("avg_price"));
                    data.add(record);
                }
            }

            response.put("code", 200);
            response.put("data", data);
            response.put("total", total);
            response.put("page", page);
            response.put("size", size);

        } catch (Exception e) {
            e.printStackTrace();
            response.put("code", 500);
            response.put("message", "服务器内部错误");
        }

        return response;
    }
}
