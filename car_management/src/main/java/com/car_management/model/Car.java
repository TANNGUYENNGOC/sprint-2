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


}
