package com.vmotors.domain.usecase;

import com.vmotors.domain.request.JobModel;
import com.vmotors.networking.NetworkError;
import com.vmotors.networking.NetworkService;

import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class TrackingService {

    private final NetworkService networkService;

    @Inject
    public TrackingService(NetworkService networkService) {
        this.networkService = networkService;
    }

    public Subscription getAssignedJobList(final TrackingService.ServiceCallback callback, final int userId) {

        return networkService.getAssignedJobs(userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<JobModel>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onError(new NetworkError(e));

                    }

                    @Override
                    public void onNext(List<JobModel>  orderInvoiceResponseModel) {
                        callback.onSuccess(orderInvoiceResponseModel);

                    }
                });
    }



    public interface ServiceCallback{
        void onSuccess(List<JobModel>  orderInvoiceResponseModel);
        void onError(NetworkError networkError);
    }
}
