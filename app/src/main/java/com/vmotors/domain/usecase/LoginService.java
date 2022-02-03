package com.vmotors.domain.usecase;

import com.vmotors.domain.request.LoginRequestModel;
import com.vmotors.domain.request.LoginResponseModel;
import com.vmotors.networking.NetworkError;
import com.vmotors.networking.NetworkService;

import javax.inject.Inject;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class LoginService {

    private final NetworkService networkService;

    @Inject
    public LoginService(NetworkService networkService) {
        this.networkService = networkService;
    }

    public Subscription login(final ServiceCallback callback, final LoginRequestModel loginRequestModel) {

        return networkService.login(loginRequestModel)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<LoginResponseModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onError(new NetworkError(e));

                    }

                    @Override
                    public void onNext(LoginResponseModel loginResponseModel) {
                        callback.onSuccess(loginResponseModel);

                    }
                });
    }

    public interface ServiceCallback{
        void onSuccess(LoginResponseModel cityListResponse);
        void onError(NetworkError networkError);
    }
}
