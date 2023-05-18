package com.car_management.model;

import javax.persistence.*;

@Entity
public class Img {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String url;
    @ManyToOne
    private Car car;
}
