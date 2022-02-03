package com.vmotors.ui.order_details;

import android.util.Log;

import com.google.gson.Gson;
import com.vmotors.domain.request.CreateEntryModel;
import com.vmotors.domain.request.FirmApiResponse;
import com.vmotors.domain.request.JobModel;
import com.vmotors.domain.request.UploadEntryResponse;
import com.vmotors.domain.usecase.OrderDetailService;
import com.vmotors.domain.usecase.TrackingService;
import com.vmotors.networking.NetworkError;
import com.vmotors.storage.ApplicationStorage;
import com.vmotors.ui.tracking.TrackingView;
import com.vmotors.utils.AppConstant;

import java.io.File;
import java.util.List;

import javax.inject.Inject;

import okhttp3.ResponseBody;
import rx.Observable;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public class OrderDetailPresenter {

    private final OrderDetailService orderDetailService;
    private final ApplicationStorage mApplicationStorage;
    private OrderDetailView view;
    private final CompositeSubscription subscriptions;


    @Inject
    public OrderDetailPresenter(OrderDetailService service, ApplicationStorage mApplicationStorage) {
        this.orderDetailService= service;
        this.mApplicationStorage= mApplicationStorage;
        this.subscriptions = new CompositeSubscription();
    }

    public void onBind(OrderDetailView view){
        this.view = view;
    }


    public void onStop() {
        subscriptions.unsubscribe();
    }

    public void uploadEntry(File f1, File f2) {
        Log.e("on", "on uploadEntry");

        Subscription subscription = orderDetailService.uploadEntry(new OrderDetailService.ServiceCallbackForImages() {
            @Override
            public void onSuccess(List<FirmApiResponse> responseBody) {
                if(responseBody!=null) {
//                    view.onImagesSuccess(responseBody);
                }else{
                    view.onFailure("No Data found");
                }
            }

            @Override
            public void onError(NetworkError networkError) {

                view.onFailure(networkError.getAppErrorMessage());
            }

        },f1, f2);

        subscriptions.add(subscription);
    }

    public void uploadEntry(File f, int type) {
        Log.e("on", "on uploadEntry");


        Subscription subscription = orderDetailService.uploadEntry(new OrderDetailService.ServiceCallback() {
            @Override
            public void onSuccess(FirmApiResponse responseBody) {
                if(responseBody!=null) {
                    view.onUploadSuccess(responseBody, type);
                }else{
                    view.onFailure("No Data found");
                }
            }

            @Override
            public void onError(NetworkError networkError) {

                view.onFailure(networkError.getAppErrorMessage());
            }

        },f);

        subscriptions.add(subscription);
    }

    public void createEntry(CreateEntryModel createEntryModel) {
        Subscription subscription = orderDetailService.submitJob(new OrderDetailService.ServiceCallback() {
            @Override
            public void onSuccess(FirmApiResponse responseBody) {
                if(responseBody!=null) {
                    view.onSuccessJob(responseBody);
                }else{
                    view.onFailure("No Data found");
                }
            }

            @Override
            public void onError(NetworkError networkError) {

                view.onFailure(networkError.getAppErrorMessage());
            }

        }, createEntryModel);

        subscriptions.add(subscription);

    }

    public int getUserId(){
       return mApplicationStorage.getInteger(AppConstant.USER_ID, -1);
    }
}
