package com.example.signin;

public class Car {

    private String car_policy_id, make, model, vehicle_year, vin, mileage_py, coverage, monthly_payment, start_date, end_date;

    public Car(String car_policy_id, String make, String model, String vehicle_year, String vin, String mileage_py, String coverage, String monthly_payment, String start_date, String end_date) {
        this.car_policy_id = car_policy_id;
        this.make = make;
        this.model = model;
        this.vehicle_year = vehicle_year;
        this.vin = vin;
        this.mileage_py = mileage_py;
        this.coverage = coverage;
        this.monthly_payment = monthly_payment;
        this.start_date = start_date;
        this.end_date = end_date;
    }

    public String getCar_policy_id() {
        return car_policy_id;
    }

    public void setCar_policy_id(String car_policy_id) {
        this.car_policy_id = car_policy_id;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getVehicle_year() {
        return vehicle_year;
    }

    public void setVehicle_year(String vehicle_year) {
        this.vehicle_year = vehicle_year;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getMileage_py() {
        return mileage_py;
    }

    public void setMileage_py(String mileage_py) {
        this.mileage_py = mileage_py;
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
