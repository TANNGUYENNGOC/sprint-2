package com.car_management.model;

import javax.persistence.*;

@Entity
public class CTPhieuNhap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    private PhieuNhap phieuNhap;
    @ManyToOne
    private MatHang matHang;
    private int soLuongNhap;
    private double donGiaNhap;

}
