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
public class CarBrandTransferPriceStatsService {

    public HashMap<String, Object> getTransferPriceStats(String brand, Integer transferCount, int page, int size) {
        HashMap<String, Object> response = new HashMap<>();
        List<HashMap<String, Object>> data = new ArrayList<>();

        String countQuery = "SELECT COUNT(*) FROM car_brand_transfer_price_stats WHERE 1=1";
        String query = "SELECT transfer_count, min_price, q1_price, median_price, q3_price, max_price FROM car_brand_transfer_price_stats WHERE 1=1";
        List<Object> params = new ArrayList<>();

        if (brand != null && !brand.isEmpty()) {
            countQuery += " AND brand = ?";
            query += " AND brand = ?";
            params.add(brand);
        }
        if (transferCount != null) {
            countQuery += " AND transfer_count = ?";
            query += " AND transfer_count = ?";
            params.add(transferCount);
        }
        query += " LIMIT ? OFFSET ?";

        try (Connection connection = MySQLUtil.getConnection()) {
            // 查询总数
            int total = 0;
            try (PreparedStatement countStmt = connection.prepareStatement(countQuery)) {
                int countParamIndex = 1;
                for (Object param : params) {
                    countStmt.setObject(countParamIndex++, param);
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
                    HashMap<String, Object> priceStats = new HashMap<>();
                    priceStats.put("min", resultSet.getFloat("min_price"));
                    priceStats.put("q1", resultSet.getFloat("q1_price"));
                    priceStats.put("median", resultSet.getFloat("median_price"));
                    priceStats.put("q3", resultSet.getFloat("q3_price"));
                    priceStats.put("max", resultSet.getFloat("max_price"));

                    HashMap<String, Object> record = new HashMap<>();
                    record.put("transferTimes", resultSet.getInt("transfer_count"));
                    record.put("priceStats", priceStats);

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