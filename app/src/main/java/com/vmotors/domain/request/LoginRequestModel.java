package com.vmotors.domain.request;

import com.google.gson.annotations.SerializedName;

public class LoginRequestModel {
    /**
     * email : abc@dsexample.com
     * password : password
     */

    @SerializedName("email")
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
