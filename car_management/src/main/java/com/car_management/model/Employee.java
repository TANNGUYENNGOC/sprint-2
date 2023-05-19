package com.car_management.model;

import com.car_management.model.user.User;

import javax.persistence.*;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String address;
    private String birthday;
    private String email;
    private boolean flag;
    private String idCard;
    private String name;
    private String phoneNumber;
    private double salary;
    @ManyToOne
    private User user;
}
