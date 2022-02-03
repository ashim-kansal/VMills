package com.vmotors.domain.usecase;

import com.vmotors.domain.request.FirmApiResponse;
import com.vmotors.networking.NetworkError;
import com.vmotors.networking.NetworkService;

import javax.inject.Inject;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class SelectFirmService {

    private final NetworkService networkService;

    @Inject
    public SelectFirmService(NetworkService networkService) {
        this.networkService = networkService;
    }

    public Subscription getFirms(final ServiceCallback callback, final String searchtext) {

        return networkService.getFirms(searchtext)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<FirmApiResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onError(new NetworkError(e));

                    }

                    @Override
                    public void onNext(FirmApiResponse firmApiResponse) {
                        callback.onSuccess(firmApiResponse);

                    }
                });
    }

    public Subscription getCommodity(final ServiceCallback callback) {

        return networkService.getCommodities()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<FirmApiResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onError(new NetworkError(e));

                    }

                    @Override
                    public void onNext(FirmApiResponse firmApiResponse) {
                        callback.onSuccess(firmApiResponse);

                    }
                });
    }

    public interface ServiceCallback{
        void onSuccess(FirmApiResponse firmApiResponse);
        void onError(NetworkError networkError);
    }
}
