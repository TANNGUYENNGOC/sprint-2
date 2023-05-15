package com.car_management.model;

import javax.persistence.*;

@Entity
public class CTPhieuXuat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    private PhieuXuat phieuXuat;
    @ManyToOne
    private MatHang matHang;
    private int soLuongXuat;
    private double donGiaXuat;

}
