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
public class CarBrandPriceStatsService {

    public HashMap<String, Object> getBrandPriceStats(String brand, int page, int size) {
        HashMap<String, Object> response = new HashMap<>();
        List<HashMap<String, Object>> data = new ArrayList<>();

        String countQuery = "SELECT COUNT(*) FROM car_brand_price_stats WHERE 1=1";
        String query = "SELECT brand, avg_new_price, avg_current_price, price_drop_percent FROM car_brand_price_stats WHERE 1=1";
        List<Object> params = new ArrayList<>();

        // 动态拼接查询条件
        if (brand != null && !brand.isEmpty()) {
            countQuery += " AND brand = ?";
            query += " AND brand = ?";
            params.add(brand);
        }
        // 保证所有字段都不为空
        query += " AND avg_new_price IS NOT NULL AND avg_current_price IS NOT NULL AND price_drop_percent IS NOT NULL";
        countQuery += " AND avg_new_price IS NOT NULL AND avg_current_price IS NOT NULL AND price_drop_percent IS NOT NULL";
        query += " LIMIT ? OFFSET ?";

        try (Connection connection = MySQLUtil.getConnection()) {
            // 查询总数
            int total = 0;
            try (PreparedStatement countStmt = connection.prepareStatement(countQuery)) {
                if (brand != null && !brand.isEmpty()) {
                    countStmt.setString(1, brand);
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

                while (resultSet.next()) {
                    HashMap<String, Object> record = new HashMap<>();
                    record.put("model", resultSet.getString("brand"));
                    record.put("newPrice", resultSet.getFloat("avg_new_price"));
                    record.put("usedPrice", resultSet.getFloat("avg_current_price"));
                    record.put("reductionRate", resultSet.getFloat("price_drop_percent"));
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
