package com.car_management.dto.cart;

public interface ICartDTO {
    Integer getId();
    Integer getCarId();
    String getCarName();
    String getCarType();
    String getCarSeries();
    String getImg();
    Integer getNumberOfVehicles();
    Double getSumPrice();
}
