package com.car_management.model;

import com.car_management.model.user.User;

import javax.persistence.*;

@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    private Car car;
    @ManyToOne
    private User user;
    private int numberOfVehicles; //Số lượng xe
    private boolean flag;
    private boolean status; //Thanh toán
    private String createDate;
    private String datePay;
    private String updateDate;

    public Cart() {
    }


    public Cart(Car car, User user) {
        this.car = car;
        this.user = user;
    }

    public Cart(int id, Car car, User user, int numberOfVehicles, boolean flag, boolean status, String createDate, String datePay, String updateDate) {
        this.id = id;
        this.car = car;
        this.user = user;
        this.numberOfVehicles = numberOfVehicles;
        this.flag = flag;
        this.status = status;
        this.createDate = createDate;
        this.datePay = datePay;
        this.updateDate = updateDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getNumberOfVehicles() {
        return numberOfVehicles;
    }

    public void setNumberOfVehicles(int numberOfVehicles) {
        this.numberOfVehicles = numberOfVehicles;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getDatePay() {
        return datePay;
    }

    public void setDatePay(String datePay) {
        this.datePay = datePay;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }
}
