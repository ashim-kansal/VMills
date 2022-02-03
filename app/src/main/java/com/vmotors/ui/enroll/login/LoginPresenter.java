package com.vmotors.ui.enroll.login;

import com.vmotors.domain.request.LoginRequestModel;
import com.vmotors.domain.request.LoginResponseModel;
import com.vmotors.domain.usecase.LoginService;
import com.vmotors.networking.NetworkError;
import com.vmotors.storage.ApplicationStorage;
import com.vmotors.utils.AppConstant;

import java.util.Calendar;

import javax.inject.Inject;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public class LoginPresenter {

    private final LoginService loginService;
    private final ApplicationStorage mApplicationStorage;
    private LoginView view;
    private final CompositeSubscription subscriptions;


    @Inject
    public LoginPresenter(LoginService loginService, ApplicationStorage mApplicationStorage) {
        this.loginService= loginService;
        this.mApplicationStorage= mApplicationStorage;
        this.subscriptions = new CompositeSubscription();
    }

    public void onBind(LoginView  view){
        this.view = view;
        view.setLoginDetails(mApplicationStorage.getString(AppConstant.EMAIL, "")
        ,mApplicationStorage.getString(AppConstant.AES_PASSWORD, "")
        ,mApplicationStorage.getBoolean(AppConstant.IS_REMEMBER_ME, false));
    }

    public void loginUser(LoginRequestModel requestModel) {
        Subscription subscription = loginService.login(new LoginService.ServiceCallback() {
            @Override
            public void onSuccess(LoginResponseModel loginResponseModel) {
                if(loginResponseModel!= null) {
                    mApplicationStorage.putInteger(AppConstant.USER_ID, loginResponseModel.getId());
                    mApplicationStorage.putString(AppConstant.LOGIN_TIME, ""+Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
                    view.onRegisterSuccess(loginResponseModel);
                }else{
                    view.onFailure("Pls check your credentials");
                }
            }

            @Override
            public void onError(NetworkError networkError) {
                view.onFailure(networkError.getMessage());
            }

        },requestModel);

        subscriptions.add(subscription);
    }
    public void onStop() {
        subscriptions.unsubscribe();
    }

}
