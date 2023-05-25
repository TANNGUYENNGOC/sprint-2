package com.car_management.dto.cart;

public class CartDTO {
    private int id;
    private int  carId;
    private int userId;

    public CartDTO(int carId, int userId) {
        this.carId = carId;
        this.userId = userId;
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
}
