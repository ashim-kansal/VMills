package com.vmotors.domain.request;

import java.util.ArrayList;

public class Data{

    private ArrayList<Firm> firms;
    private ArrayList<Firm> commodities;
    private int totalRecords;
    private String image;
    private String message;

    public ArrayList<Firm> getFirms() {
        return firms;
    }

    public void setFirms(ArrayList<Firm> firms) {
        this.firms = firms;
    }

    public int getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(int totalRecords) {
        this.totalRecords = totalRecords;
    }

    public ArrayList<Firm> getCommodities() {
        return commodities;
    }

    public void setCommodities(ArrayList<Firm> commodities) {
        this.commodities = commodities;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}