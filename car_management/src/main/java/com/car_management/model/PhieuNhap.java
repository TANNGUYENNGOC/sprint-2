package com.car_management.model;

import javax.persistence.*;

@Entity
public class PhieuNhap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String maPhieuNhap;
    private String ngayNhao;
    @ManyToOne
    private NhanVien nhanVien;
    @ManyToOne
    private NhaCungCap nhaCungCap;
}
