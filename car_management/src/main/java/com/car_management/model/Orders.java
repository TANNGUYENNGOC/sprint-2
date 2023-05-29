package com.car_management.model;

import com.car_management.model.user.User;

import javax.persistence.*;

@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    private User user;
    private int totalOder;
    private String createDate;
    private String modifyDate;

    public Orders() {
    }

    public Orders(User user, int totalOder) {
        this.user = user;
        this.totalOder = totalOder;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getTotalOder() {
        return totalOder;
    }

    public void setTotalOder(int totalOder) {
        this.totalOder = totalOder;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(String modifyDate) {
        this.modifyDate = modifyDate;
    }
}
