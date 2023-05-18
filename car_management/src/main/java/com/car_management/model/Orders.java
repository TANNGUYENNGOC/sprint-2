package com.car_management.model;

import javax.persistence.*;

@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    private Customer customer;
    private int totalOder;
    private String createDate;
    private String modifyDate;
}
