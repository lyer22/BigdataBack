package com.example.demo.controller;

import com.example.demo.service.BrandMileageAgePublishStatsService;
import com.example.demo.service.CarBrandAgePriceAvgService;
import com.example.demo.service.CarBrandMileagePriceAvgService;
import com.example.demo.service.FuelTypeStatsService;
import com.example.demo.service.BrandAvgPriceService;
import com.example.demo.service.CarBrandTransferPriceStatsService; // 新增
import com.example.demo.service.CarBrandPriceStatsService; // 新增
import com.example.demo.service.BrandStatsService; // 新增

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class MysqlController {

    private final FuelTypeStatsService fuelTypeStatsService;
    private final BrandMileageAgePublishStatsService statsService;
    private final CarBrandMileagePriceAvgService carBrandMileagePriceAvgService;
    private final CarBrandAgePriceAvgService carBrandAgePriceAvgService;
    private final BrandAvgPriceService brandAvgPriceService;
    private final CarBrandTransferPriceStatsService carBrandTransferPriceStatsService; // 新增
    private final CarBrandPriceStatsService carBrandPriceStatsService; // 新增
    private final BrandStatsService brandStatsService; // 新增

    public MysqlController(
        FuelTypeStatsService fuelTypeStatsService,
        BrandMileageAgePublishStatsService statsService,
        CarBrandMileagePriceAvgService carBrandMileagePriceAvgService,
        CarBrandAgePriceAvgService carBrandAgePriceAvgService,
        BrandAvgPriceService brandAvgPriceService,
        CarBrandTransferPriceStatsService carBrandTransferPriceStatsService,
        CarBrandPriceStatsService carBrandPriceStatsService,
        BrandStatsService brandStatsService // 新增
    ) {
        this.carBrandAgePriceAvgService = carBrandAgePriceAvgService;
        this.fuelTypeStatsService = fuelTypeStatsService;
        this.statsService = statsService;
        this.carBrandMileagePriceAvgService = carBrandMileagePriceAvgService;
        this.brandAvgPriceService = brandAvgPriceService;
        this.carBrandTransferPriceStatsService = carBrandTransferPriceStatsService;
        this.carBrandPriceStatsService = carBrandPriceStatsService;
        this.brandStatsService = brandStatsService; // 新增
    }

    @GetMapping("/fuel-type-ratio")
    public HashMap<String, Object> getFuelTypeStats() {
        return fuelTypeStatsService.getFuelTypeStats();
    }

    @GetMapping("/age-mileage-selltime")
    public HashMap<String, Object> getStats(
        @RequestParam(value = "brand", required = false) String brand,
        @RequestParam(value = "page", defaultValue = "1") int page,
        @RequestParam(value = "size", defaultValue = "10") int size) {
        return statsService.getStats(brand, page, size);
    }

    @GetMapping("/mileage-price")
    public HashMap<String, Object> getMileagePrice(
        @RequestParam(value = "maxMileage", required = false) Float maxMileage,
        @RequestParam(value = "page", defaultValue = "1") int page,
        @RequestParam(value = "size", defaultValue = "10") int size) {
        return carBrandMileagePriceAvgService.getMileagePriceData(maxMileage, page, size);
    }

    @GetMapping("/age-price")
    public HashMap<String, Object> getAgePrice(
        @RequestParam(value = "brand", required = false) String brand,
        @RequestParam(value = "page", defaultValue = "1") int page,
        @RequestParam(value = "size", defaultValue = "10") int size) {
        return carBrandAgePriceAvgService.getAgePriceData(brand, page, size);
    }

    @GetMapping("/brand-price")
    public HashMap<String, Object> getBrandAvgPrice(
        @RequestParam(value = "brand", required = false) String brand,
        @RequestParam(value = "page", defaultValue = "1") int page,
        @RequestParam(value = "size", defaultValue = "10") int size) {
        return brandAvgPriceService.getBrandAvgPrice(brand, page, size);
    }

    @GetMapping("/transfer-impact")
    public HashMap<String, Object> getTransferPriceStats(
        @RequestParam(value = "brand", required = false) String brand,
        @RequestParam(value = "transferCount", required = false) Integer transferCount,
        @RequestParam(value = "page", defaultValue = "1") int page,
        @RequestParam(value = "size", defaultValue = "10") int size) {
        return carBrandTransferPriceStatsService.getTransferPriceStats(brand, transferCount, page, size);
    }

    @GetMapping("/price-reduction")
    public HashMap<String, Object> getBrandPriceStats(
        @RequestParam(value = "brand", required = false) String brand,
        @RequestParam(value = "page", defaultValue = "1") int page,
        @RequestParam(value = "size", defaultValue = "10") int size) {
        return carBrandPriceStatsService.getBrandPriceStats(brand, page, size);
    }

    // 新增：品牌数量及占比接口
    @GetMapping("/brand-distribution")
    public HashMap<String, Object> getBrandStats(
    @RequestParam(value = "page", defaultValue = "1") int page,
    @RequestParam(value = "size", defaultValue = "10") int size) {
    return brandStatsService.getBrandStats(page, size);
    }
}
