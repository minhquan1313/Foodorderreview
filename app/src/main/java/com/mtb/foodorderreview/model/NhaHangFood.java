package com.mtb.foodorderreview.model;

public class NhaHangFood {
    private Integer id;
    private Integer idNhaHang;
    private Integer idFood;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdNhaHang() {
        return idNhaHang;
    }

    public void setIdNhaHang(Integer idNhaHang) {
        this.idNhaHang = idNhaHang;
    }

    public Integer getIdFood() {
        return idFood;
    }

    public void setIdFood(Integer idFood) {
        this.idFood = idFood;
    }

    public NhaHangFood(Integer id, Integer idNhaHang, Integer idFood) {
        this.id = id;
        this.idNhaHang = idNhaHang;
        this.idFood = idFood;
    }
}
