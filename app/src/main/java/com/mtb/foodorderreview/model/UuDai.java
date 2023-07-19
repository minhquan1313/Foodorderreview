package com.mtb.foodorderreview.model;

import java.util.Date;

public class UuDai {
    private Integer id;
    private String noiDung;
    private Integer giaTri;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public Integer getGiaTri() {
        return giaTri;
    }

    public void setGiaTri(Integer giaTri) {
        this.giaTri = giaTri;
    }

    public Date getNgayBatdau() {
        return ngayBatdau;
    }

    public void setNgayBatdau(Date ngayBatdau) {
        this.ngayBatdau = ngayBatdau;
    }

    public Date getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(Date ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public String getLoai() {
        return loai;
    }

    public void setLoai(String loai) {
        this.loai = loai;
    }

    public Integer getIdNhaHangFood() {
        return idNhaHangFood;
    }

    public void setIdNhaHangFood(Integer idNhaHangFood) {
        this.idNhaHangFood = idNhaHangFood;
    }

    private Date ngayBatdau;
    private Date ngayKetThuc;
    private String hinhAnh;
    private String loai;
    private Integer idNhaHangFood;
}
