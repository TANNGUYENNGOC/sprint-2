package com.car_management.model;

import javax.persistence.*;

@Entity
public class PhieuXuat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String maPhieuXuat;
    private String ngayXuat;
    @ManyToOne
    private NhanVien nhanVien;
    @ManyToOne
    private KhachHang khachHang;
}
