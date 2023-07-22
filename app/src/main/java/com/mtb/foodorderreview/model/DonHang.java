package com.mtb.foodorderreview.model;

import java.time.LocalDateTime;

public class DonHang {
    private Integer id;
    private Integer trangThai;
    private LocalDateTime ngayGio;
    private String ghiChu;
    private Integer idUser;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Integer trangThai) {
        this.trangThai = trangThai;
    }

    public LocalDateTime getNgayGio() {
        return ngayGio;
    }

    public void setNgayGio(LocalDateTime ngayGio) {
        this.ngayGio = ngayGio;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public DonHang(Integer id, Integer trangThai, LocalDateTime ngayGio, String ghiChu, Integer idUser) {
        this.id = id;
        this.trangThai = trangThai;
        this.ngayGio = ngayGio;
        this.ghiChu = ghiChu;
        this.idUser = idUser;
    }
}
