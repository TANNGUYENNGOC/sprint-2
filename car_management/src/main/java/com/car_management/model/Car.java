package com.car_management.model;

import javax.persistence.*;

@Entity
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @ManyToOne
    private CarType carType;
    private double price;
    private String fuel; //Nhiên liệu
    private String discount; //Giảm giá
    @ManyToOne
    private CarSeries carSeries;
    private String origin; //Xuất sứ
    private int numberOfSeats; //Số chỗ ngồi

    private int quantity; //Số lượng

    private String img;

    public Car() {
    }

    public Car(int id, String name, CarType carType, double price, String fuel, String discount, CarSeries carSeries, String origin) {
        this.id = id;
        this.name = name;
        this.carType = carType;
        this.price = price;
        this.fuel = fuel;
        this.discount = discount;
        this.carSeries = carSeries;
        this.origin = origin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CarType getCarType() {
        return carType;
    }

    public void setCarType(CarType carType) {
        this.carType = carType;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public CarSeries getCarSeries() {
        return carSeries;
    }

    public void setCarSeries(CarSeries carSeries) {
        this.carSeries = carSeries;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
