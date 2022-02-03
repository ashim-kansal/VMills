package com.vmotors.domain.request;

import android.os.Parcelable;

import java.io.Serializable;

public class JobModel implements Serializable {


    /* {
    "id": 7,
    "job_name": "Test Job",
    "total_quantity": 32,
    "firm_name": "testfirm2\r\n",
    "address": "NY"
}*/

    private int id;
    private int total_quantity;
    private String job_name;
    private String firm_name;
    private String address;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTotal_quantity() {
        return total_quantity;
    }

    public void setTotal_quantity(int total_quantity) {
        this.total_quantity = total_quantity;
    }

    public String getJob_name() {
        return job_name;
    }

    public void setJob_name(String job_name) {
        this.job_name = job_name;
    }

    public String getFirm_name() {
        return firm_name;
    }

    public void setFirm_name(String firm_name) {
        this.firm_name = firm_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
