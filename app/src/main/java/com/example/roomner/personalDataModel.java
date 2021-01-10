package com.example.roomner;

import java.io.Serializable;

public class personalDataModel implements Serializable
{
    private String uid;
    private String name;
    private String number;
    private String age;
    private String gender;
    private String city;
    private String email;
    private String password;

    public personalDataModel(){

    }

    public personalDataModel(String uid, String email, String password, String name, String gender, String age, String number, String city) {
        this.uid = uid;
        this.name = name;
        this.number = number;
        this.age = age;
        this.gender = gender;
        this.city = city;
        this.email = email;
        this.password = password;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
