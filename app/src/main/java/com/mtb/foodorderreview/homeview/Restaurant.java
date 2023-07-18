package com.mtb.foodorderreview.homeview;

import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    private int id;
    //    private static int ID = 0;
    private String name;
    private int image;
    private String address;

    private static List<Restaurant> list = new ArrayList<>();


    public Restaurant(int id, String name, int image, String address) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
