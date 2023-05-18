package com.car_management.model;

import javax.persistence.*;

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String address;
    private String dateOfBirth;
    private String name;
    private String email;
    private boolean flag;
    private int gender;
    private String phoneNumber;
    @ManyToOne
    private Account account;

}
