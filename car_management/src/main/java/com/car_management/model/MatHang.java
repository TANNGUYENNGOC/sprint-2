package com.car_management.model;

import javax.persistence.*;

@Entity
public class MatHang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String maMatHang;
    private String tenMatHang;
    @ManyToOne
    private NhaCungCap nhaCungCap;
    private String nhaSanXuat;
    private int soLuong;
    private String mota;
}
