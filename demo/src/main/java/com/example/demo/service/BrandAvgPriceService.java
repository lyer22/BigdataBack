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
public class BrandAvgPriceService {

    public HashMap<String, Object> getBrandAvgPrice(String brand, int page, int size) {
        HashMap<String, Object> response = new HashMap<>();
        List<HashMap<String, Object>> data = new ArrayList<>();

        String countQuery = "SELECT COUNT(*) FROM brand_avg_price WHERE 1=1";
        String query = "SELECT brand, avg_price FROM brand_avg_price WHERE 1=1";
        List<Object> params = new ArrayList<>();

        if (brand != null && !brand.isEmpty()) {
            countQuery += " AND brand = ?";
            query += " AND brand = ?";
            params.add(brand);
        }
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
                    record.put("brand", resultSet.getString("brand"));
                    record.put("avgPrice", resultSet.getFloat("avg_price"));
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