package com.example.signin;

public class Customer {


    private String ID;
    private String name;
    private String age;
    private String date_joined;

    public Customer(String ID, String name, String age, String date_joined) {
        this.ID = ID;
        this.name = name;
        this.age = age;
        this.date_joined = date_joined;
    }


    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getDate_joined() {
        return date_joined;
    }

    public void setDate_joined(String date_joined) {
        this.date_joined = date_joined;
    }


}