package com.example.signin;

public class Life {

    private String life_policy_id, existing_conditions, coverage, beneficiary, monthly_payment, start_date;

    public Life(String life_policy_id, String existing_conditions, String coverage, String beneficiary, String monthly_payment, String start_date) {
        this.life_policy_id = life_policy_id;
        this.existing_conditions = existing_conditions;
        this.coverage = coverage;
        this.beneficiary = beneficiary;
        this.monthly_payment = monthly_payment;
        this.start_date = start_date;
    }

    public String getLife_policy_id() {
        return life_policy_id;
    }

    public void setLife_policy_id(String life_policy_id) {
        this.life_policy_id = life_policy_id;
    }

    public String getExisting_conditions() {
        return existing_conditions;
    }

    public void setExisting_conditions(String existing_conditions) {
        this.existing_conditions = existing_conditions;
    }

    public String getCoverage() {
        return coverage;
    }

    public void setCoverage(String coverage) {
        this.coverage = coverage;
    }

    public String getBeneficiary() {
        return beneficiary;
    }

    public void setBeneficiary(String beneficiary) {
        this.beneficiary = beneficiary;
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
}
