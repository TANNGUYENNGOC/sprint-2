package com.car_management.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class NhanVien {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String maNhanVien;
    private String tenNhanVien;
    private Boolean gioiTinh;
    private String sdt;
    private String diaChi;
    private double luongCoBan;
}
