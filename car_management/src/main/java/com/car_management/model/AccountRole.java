package com.car_management.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class AccountRole {
    @Id
    private int id;
    @ManyToOne
    private Account account;
    @ManyToOne
    private Role role;
}
