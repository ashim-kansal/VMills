package com.vmotors.ui.tracking;

import com.vmotors.domain.request.JobModel;
import com.vmotors.domain.usecase.TrackingService;
import com.vmotors.networking.NetworkError;
import com.vmotors.storage.ApplicationStorage;
import com.vmotors.utils.AppConstant;

import java.util.List;

import javax.inject.Inject;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public class TrackingPresenter {

    private final TrackingService trackingService;
    private final ApplicationStorage mApplicationStorage;
    private TrackingView view;
    private final CompositeSubscription subscriptions;


    @Inject
    public TrackingPresenter(TrackingService service, ApplicationStorage mApplicationStorage) {
        this.trackingService= service;
        this.mApplicationStorage= mApplicationStorage;
        this.subscriptions = new CompositeSubscription();
    }

    public void onBind(TrackingView view){
        this.view = view;
    }

    public void getAssignedJobList() {


        Subscription subscription = trackingService.getAssignedJobList(new TrackingService.ServiceCallback() {
            @Override
            public void onSuccess(List<JobModel> loginResponseModel) {
                if(loginResponseModel!=null) {
                    view.onSuccess(loginResponseModel);
                }else{
                    view.onFailure("No Data found");
                }
            }

            @Override
            public void onError(NetworkError networkError) {
                view.onFailure(networkError.getAppErrorMessage());
            }

        },mApplicationStorage.getInteger(AppConstant.USER_ID, -1));

        subscriptions.add(subscription);
    }

    public void onStop() {
        subscriptions.unsubscribe();
    }

}
