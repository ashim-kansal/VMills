
package com.vmotors.domain.request;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class ItemsInformation {

    @SerializedName("in_stock")
    private String inStock;
    @SerializedName("item_code")
    private String itemCode;
    @SerializedName("item_name")
    private String itemName;
    @Expose
    private String location;
    @SerializedName("order_qty")
    private String orderQty;
    @SerializedName("remaining_qty")
    private Long remainingQty;
    @SerializedName("description")
    private String description;
    @SerializedName("customer_name")
    private String customer_name;

    public String getInStock() {
        return inStock;
    }

    public void setInStock(String inStock) {
        this.inStock = inStock;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getOrderQty() {
        return orderQty;
    }

    public void setOrderQty(String orderQty) {
        this.orderQty = orderQty;
    }

    public Long getRemainingQty() {
        return remainingQty;
    }

    public void setRemainingQty(Long remainingQty) {
        this.remainingQty = remainingQty;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }
}
