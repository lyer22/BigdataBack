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
public class BrandStatsService {

    public HashMap<String, Object> getBrandStats(int page, int size) {
        HashMap<String, Object> response = new HashMap<>();
        List<HashMap<String, Object>> data = new ArrayList<>();

        String countQuery = "SELECT COUNT(*) FROM brand_stats";
        String query = "SELECT brand, count FROM brand_stats";
        int totalCount = 0;

        // 查询总数
        try (Connection connection = MySQLUtil.getConnection();
             PreparedStatement countStmt = connection.prepareStatement(countQuery);
             ResultSet countRs = countStmt.executeQuery()) {
            if (countRs.next()) {
                totalCount = countRs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.put("code", 500);
            response.put("message", "服务器内部错误");
            return response;
        }

        // 查询分页数据
        query += " LIMIT ? OFFSET ?";
        try (Connection connection = MySQLUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, size);
            preparedStatement.setInt(2, (page - 1) * size);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String brand = resultSet.getString("brand");
                int count = resultSet.getInt("count");

                HashMap<String, Object> record = new HashMap<>();
                record.put("brand", brand);
                record.put("count", count);
                // 百分比基于总数
                double percentage = totalCount == 0 ? 0 : (count * 100.0) / totalCount;
                record.put("percentage", Math.round(percentage * 10.0) / 10.0);
                data.add(record);
            }

            response.put("code", 200);
            response.put("data", data);
            response.put("total", totalCount);
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
