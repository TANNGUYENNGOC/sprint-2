package com.car_management.model;

import com.car_management.model.user.User;

import javax.persistence.*;

@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    private User user;
    private int totalOder;
    private String createDate;
    private String modifyDate;

}
