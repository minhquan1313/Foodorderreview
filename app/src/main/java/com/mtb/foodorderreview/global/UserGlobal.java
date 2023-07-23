package com.mtb.foodorderreview.global;

public class UserGlobal {
    private int id;
    private String userName;
    private String name;
    private String tel;
    private int avatar;
    private String email;
    private String address;
    private String token;
    private boolean isAdmin;
    private static UserGlobal instance = new UserGlobal();

    //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
    public static UserGlobal getInstance() {
        return instance;
    }
    //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

    public void setData(int id, String userName, String name, String tel, int avatar, String email, String address, String token, boolean isAdmin) {
        this.id = id;
        this.userName = userName;
        this.name = name;
        this.tel = tel;
        this.avatar = avatar;
        this.email = email;
        this.address = address;
        this.token = token;
        this.isAdmin = isAdmin;
    }

    private UserGlobal() {
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

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public int getAvatar() {
        return avatar;
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
