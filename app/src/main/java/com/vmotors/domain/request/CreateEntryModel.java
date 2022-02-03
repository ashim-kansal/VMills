package com.vmotors.domain.request;

import java.io.Serializable;

public class CreateEntryModel implements Serializable {

    private String entryType;
    private String deliveryType;
    private String previousSlip;
    private String currentSlip;
    private String bill;
    private int firmId;
    private int jobId;
    private int commodityId;
    private int userId;
    private String billNo;
    private double currentGrossWeight;
    private double currentTareWeight;
    private double currentNetWeight;
    private int previousSlipNo;
    private int currentSlipNo;
    private String noofbags;
    private String truckNo;
    private String currentDate;
    private String kantaSlip;
    private String kantaSlipNo;

    public String getEntryType() {
        return entryType;
    }

    public void setEntryType(String entryType) {
        this.entryType = entryType;
    }

    public String getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(String deliveryType) {
        this.deliveryType = deliveryType;
    }

    public String getPreviousSlip() {
        return previousSlip;
    }

    public void setPreviousSlip(String previousSlip) {
        this.previousSlip = previousSlip;
    }

    public String getCurrentSlip() {
        return currentSlip;
    }

    public void setCurrentSlip(String currentSlip) {
        this.currentSlip = currentSlip;
    }

    public String getBill() {
        return bill;
    }

    public void setBill(String bill) {
        this.bill = bill;
    }

    public int getFirmId() {
        return firmId;
    }

    public void setFirmId(int firmId) {
        this.firmId = firmId;
    }

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public int getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(int commodityId) {
        this.commodityId = commodityId;
    }

    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    public double getCurrentGrossWeight() {
        return currentGrossWeight;
    }

    public void setCurrentGrossWeight(double currentGrossWeight) {
        this.currentGrossWeight = currentGrossWeight;
    }

    public double getCurrentTareWeight() {
        return currentTareWeight;
    }

    public void setCurrentTareWeight(double currentTareWeight) {
        this.currentTareWeight = currentTareWeight;
    }

    public double getCurrentNetWeight() {
        return currentNetWeight;
    }

    public void setCurrentNetWeight(double currentNetWeight) {
        this.currentNetWeight = currentNetWeight;
    }

    public int getPreviousSlipNo() {
        return previousSlipNo;
    }

    public void setPreviousSlipNo(int previousSlipNo) {
        this.previousSlipNo = previousSlipNo;
    }

    public int getCurrentSlipNo() {
        return currentSlipNo;
    }

    public void setCurrentSlipNo(int currentSlipNo) {
        this.currentSlipNo = currentSlipNo;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getNoofbags() {
        return noofbags;
    }

    public void setNoofbags(String noofbags) {
        this.noofbags = noofbags;
    }

    public String getTruckNo() {
        return truckNo;
    }

    public void setTruckNo(String truckNo) {
        this.truckNo = truckNo;
    }

    public String getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(String currentDate) {
        this.currentDate = currentDate;
    }

    public String getKantaSlip() {
        return kantaSlip;
    }

    public void setKantaSlip(String kantaSlip) {
        this.kantaSlip = kantaSlip;
    }

    public String getKantaSlipNo() {
        return kantaSlipNo;
    }

    public void setKantaSlipNo(String kantaSlipNo) {
        this.kantaSlipNo = kantaSlipNo;
    }
}
