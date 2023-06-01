package com.car_management.dto.cart;

public class CartDTO1 {
    private Integer id;
    private Integer carId;
    private String carName;
    private String carType;
    private String carSeries;
    private String img;
    private Integer numberOfVehicles;
    private Double sumPrice;

    public CartDTO1() {
    }

    public CartDTO1(Integer id, Integer carId, String carName, String carType, String carSeries, String img, Integer numberOfVehicles, Double sumPrice) {
        this.id = id;
        this.carId = carId;
        this.carName = carName;
        this.carType = carType;
        this.carSeries = carSeries;
        this.img = img;
        this.numberOfVehicles = numberOfVehicles;
        this.sumPrice = sumPrice;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public String getCarSeries() {
        return carSeries;
    }

    public void setCarSeries(String carSeries) {
        this.carSeries = carSeries;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Integer getNumberOfVehicles() {
        return numberOfVehicles;
    }

    public void setNumberOfVehicles(Integer numberOfVehicles) {
        this.numberOfVehicles = numberOfVehicles;
    }

    public Double getSumPrice() {
        return sumPrice;
    }

    public void setSumPrice(Double sumPrice) {
        this.sumPrice = sumPrice;
    }
}
