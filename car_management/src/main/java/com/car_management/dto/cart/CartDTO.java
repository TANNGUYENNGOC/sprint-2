package com.car_management.dto.cart;

public class CartDTO {
    private int id;
    private int  carId;
    private int userId;
    private double sumPrice;

    public CartDTO(int carId, int userId) {
        this.carId = carId;
        this.userId = userId;
    }

    public CartDTO() {
    }

    public CartDTO(int carId, int userId, double sumPrice) {
        this.carId = carId;
        this.userId = userId;
        this.sumPrice = sumPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public double getSumPrice() {
        return sumPrice;
    }

    public void setSumPrice(double sumPrice) {
        this.sumPrice = sumPrice;
    }
}
