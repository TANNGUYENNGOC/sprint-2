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
}
