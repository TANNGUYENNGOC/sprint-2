package com.car_management.dto.oder;

public class OderDTO {
    private int idUser;
    private int totalOder;

    public OderDTO() {
    }

    public OderDTO(int idUser, int totalOder) {
        this.idUser = idUser;
        this.totalOder = totalOder;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getTotalOder() {
        return totalOder;
    }

    public void setTotalOder(int totalOder) {
        this.totalOder = totalOder;
    }
}
