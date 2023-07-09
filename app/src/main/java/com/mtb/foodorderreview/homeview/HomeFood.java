package com.mtb.foodorderreview.homeview;

public class HomeFood {
    private int id;
    private static int ID = 0;
    private String name;
    private int image;

    public HomeFood(int id, String name, int image) {
        if (id > ID) ID = id;

        this.id = id;
        this.name = name;
        this.image = image;
    }

    public HomeFood(String name, int image) {
        this.id = ++ID;
        this.name = name;
        this.image = image;
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
}
