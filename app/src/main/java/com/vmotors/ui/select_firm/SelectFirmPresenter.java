package com.vmotors.ui.select_firm;

import com.vmotors.domain.request.FirmApiResponse;
import com.vmotors.domain.usecase.SelectFirmService;
import com.vmotors.networking.NetworkError;
import com.vmotors.storage.ApplicationStorage;

import javax.inject.Inject;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public class SelectFirmPresenter {

    private final SelectFirmService selectFirmService;
    private final ApplicationStorage mApplicationStorage;
    private SelectFirmView view;
    private final CompositeSubscription subscriptions;


    @Inject
    public SelectFirmPresenter(SelectFirmService selectFirmService, ApplicationStorage mApplicationStorage) {
        this.selectFirmService= selectFirmService;
        this.mApplicationStorage= mApplicationStorage;
        this.subscriptions = new CompositeSubscription();
    }

    public void onBind(SelectFirmView  view){
        this.view = view;
    }

    public void getFirms() {
        Subscription subscription = selectFirmService.getFirms(new SelectFirmService.ServiceCallback() {
            @Override
            public void onSuccess(FirmApiResponse firmApiResponse) {
                if(firmApiResponse!= null && firmApiResponse.getStatus()==200) {
                    view.onSuccess(firmApiResponse);
                }else{
                    view.onFailure("Pls check your credentials");
                }
            }

            @Override
            public void onError(NetworkError networkError) {
                view.onFailure(networkError.getMessage());
            }

        },"");

        subscriptions.add(subscription);
    }

    public void getCommodities() {
        Subscription subscription = selectFirmService.getCommodity(new SelectFirmService.ServiceCallback() {
            @Override
            public void onSuccess(FirmApiResponse firmApiResponse) {
                if(firmApiResponse!= null && firmApiResponse.getStatus()==200) {
                    view.onSuccess(firmApiResponse);
                }else{
                    view.onFailure("Pls check your credentials");
                }
            }

            @Override
            public void onError(NetworkError networkError) {
                view.onFailure(networkError.getMessage());
            }

        });

        subscriptions.add(subscription);
    }

    public void onStop() {
        subscriptions.unsubscribe();
    }

}
