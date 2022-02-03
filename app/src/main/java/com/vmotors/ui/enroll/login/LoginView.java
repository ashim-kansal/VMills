package com.vmotors.ui.enroll.login;

import com.vmotors.domain.request.LoginResponseModel;

public interface LoginView {

    void onFailure(String appErrorMessage);

    void onRegisterSuccess(LoginResponseModel loginResponseModel);

    void setLoginDetails(String email, String password, boolean isRemember);
}
