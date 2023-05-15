package com.car_management.model;

import javax.persistence.*;

@Entity
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    private NhanVien nhanVien;
    @ManyToOne
    private Role role;
}
