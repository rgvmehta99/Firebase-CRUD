package com.firebasecrud;

/**
 * Created by dhaval.mehta on 16-Jan-18.
 */

public class UserModel {

    public String id = "";
    public String name = "";
    public String email = "";
    public String mobile = "";
    public String address = "";
    public String city = "";
    public String country = "";
    public String dob = "";

    public UserModel() {
    }

    public UserModel(String id, String name, String email, String mobile, String address, String city, String country, String dob) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.address = address;
        this.city = city;
        this.country = country;
        this.dob = dob;
    }
}
