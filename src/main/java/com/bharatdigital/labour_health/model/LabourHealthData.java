package com.bharatdigital.labour_health.model;

import jakarta.persistence.*;

@Entity
@Table(name = "labour_health_data")
public class LabourHealthData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int s_no;
    private String dispensary_name;
    private String state;
    private String district;
    private String address;
    private String pincode;
    private int opd_total_static;
    private int opd_total_mobile;
    private int ipd_total;
    private int male_opd_total;
    private int female_opd_total;
    private int children_opd_total;
    private int referred_outside;
    private double medicine_value;

    // Getters and Setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public int getS_no() {
        return s_no;
    }
    public void setS_no(int s_no) {
        this.s_no = s_no;
    }

    public String getDispensary_name() {
        return dispensary_name;
    }
    public void setDispensary_name(String dispensary_name) {
        this.dispensary_name = dispensary_name;
    }

    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }

    public String getDistrict() {
        return district;
    }
    public void setDistrict(String district) {
        this.district = district;
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public String getPincode() {
        return pincode;
    }
    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public int getOpd_total_static() {
        return opd_total_static;
    }
    public void setOpd_total_static(int opd_total_static) {
        this.opd_total_static = opd_total_static;
    }

    public int getOpd_total_mobile() {
        return opd_total_mobile;
    }
    public void setOpd_total_mobile(int opd_total_mobile) {
        this.opd_total_mobile = opd_total_mobile;
    }

    public int getIpd_total() {
        return ipd_total;
    }
    public void setIpd_total(int ipd_total) {
        this.ipd_total = ipd_total;
    }

    public int getMale_opd_total() {
        return male_opd_total;
    }
    public void setMale_opd_total(int male_opd_total) {
        this.male_opd_total = male_opd_total;
    }

    public int getFemale_opd_total() {
        return female_opd_total;
    }
    public void setFemale_opd_total(int female_opd_total) {
        this.female_opd_total = female_opd_total;
    }

    public int getChildren_opd_total() {
        return children_opd_total;
    }
    public void setChildren_opd_total(int children_opd_total) {
        this.children_opd_total = children_opd_total;
    }

    public int getReferred_outside() {
        return referred_outside;
    }
    public void setReferred_outside(int referred_outside) {
        this.referred_outside = referred_outside;
    }

    public double getMedicine_value() {
        return medicine_value;
    }
    public void setMedicine_value(double medicine_value) {
        this.medicine_value = medicine_value;
    }
}
