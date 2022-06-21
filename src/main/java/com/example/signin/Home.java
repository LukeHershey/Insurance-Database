package com.example.signin;

public class Home {

    private String home_policy_id, address, area_sqft, number_bedrooms, number_bathrooms, price, coverage, monthly_payment, start_date, end_date;

    public Home(String home_policy_id, String address, String area_sqft, String number_bedrooms, String number_bathrooms, String price, String coverage, String monthly_payment, String start_date, String end_date) {
        this.home_policy_id = home_policy_id;
        this.address = address;
        this.area_sqft = area_sqft;
        this.number_bedrooms = number_bedrooms;
        this.number_bathrooms = number_bathrooms;
        this.price = price;
        this.coverage = coverage;
        this.monthly_payment = monthly_payment;
        this.start_date = start_date;
        this.end_date = end_date;
    }

    public String getHome_policy_id() {
        return home_policy_id;
    }

    public void setHome_policy_id(String home_policy_id) {
        this.home_policy_id = home_policy_id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getArea_sqft() {
        return area_sqft;
    }

    public void setArea_sqft(String area_sqft) {
        this.area_sqft = area_sqft;
    }

    public String getNumber_bedrooms() {
        return number_bedrooms;
    }

    public void setNumber_bedrooms(String number_bedrooms) {
        this.number_bedrooms = number_bedrooms;
    }

    public String getNumber_bathrooms() {
        return number_bathrooms;
    }

    public void setNumber_bathrooms(String number_bathrooms) {
        this.number_bathrooms = number_bathrooms;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCoverage() {
        return coverage;
    }

    public void setCoverage(String coverage) {
        this.coverage = coverage;
    }

    public String getMonthly_payment() {
        return monthly_payment;
    }

    public void setMonthly_payment(String monthly_payment) {
        this.monthly_payment = monthly_payment;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }
}
