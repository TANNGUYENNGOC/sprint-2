package com.car_management.model;

import javax.persistence.*;

@Entity
public class OderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private boolean flag;
    private int quantity;
    @ManyToOne
    private Car car;
    @ManyToOne
    private Orders orders;
    private double price;
}
