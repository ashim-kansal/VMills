package com.vmotors.domain.usecase;

import android.util.Log;

import com.vmotors.domain.request.CreateEntryModel;
import com.vmotors.domain.request.FirmApiResponse;
import com.vmotors.networking.NetworkError;
import com.vmotors.networking.NetworkService;
import com.vmotors.utils.ImageUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func2;
import rx.schedulers.Schedulers;

public class OrderDetailService {

    private final NetworkService networkService;

    @Inject
    public OrderDetailService(NetworkService networkService) {
        this.networkService = networkService;
    }


    public Subscription uploadEntry(final ServiceCallback callback, final File file) {

        Log.e("on", "on upload" + file.getAbsolutePath());

        String path = ImageUtils.compressImage(file.getAbsolutePath());
        File img = new File(path);

        //            RequestBody qty = RequestBody.create(MediaType.parse("text/plain"), quantity);

        RequestBody reqFile = RequestBody.create(MediaType.parse("image/*"), img);
        MultipartBody.Part body1 = MultipartBody.Part.createFormData("image", img.getName(), reqFile);


        Log.e("on", "on upload");

        return networkService.upload(body1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnUnsubscribe(() -> {
                    Log.e("hh", "jjj");
                })
                .subscribe(new Subscriber<FirmApiResponse>() {
                    @Override
                    public void onCompleted() {
                        Log.e("on", "onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("on", "onError");
                        e.printStackTrace();
                        callback.onError(new NetworkError(e));
                    }

                    @Override
                    public void onNext(FirmApiResponse model) {
                        Log.e("on", "onNext");
                        callback.onSuccess(model);
                    }
                });
    }

    public Subscription uploadEntry(final ServiceCallbackForImages callback, final File file, final File file2) {

        File img1 = new File(ImageUtils.compressImage(file.getAbsolutePath()));
        RequestBody reqFile1 = RequestBody.create(MediaType.parse("image/*"), img1);
        MultipartBody.Part body1 = MultipartBody.Part.createFormData("image", img1.getName(), reqFile1);
        Observable<FirmApiResponse> api1 = networkService.upload(body1);

        File img2 = new File(ImageUtils.compressImage(file2.getAbsolutePath()));
        RequestBody reqFile2 = RequestBody.create(MediaType.parse("image/*"), img2);
        MultipartBody.Part body2 = MultipartBody.Part.createFormData("image", img2.getName(), reqFile2);
        Observable<FirmApiResponse> api2 = networkService.upload(body2);
        List<FirmApiResponse> list = new ArrayList<>();

        api1.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnUnsubscribe(() -> {
                    Log.e("hh", "jjj");
                })
                .subscribe(new Subscriber<FirmApiResponse>() {
                    @Override
                    public void onCompleted() {
                        Log.e("on", "onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("on", "onError");
                        e.printStackTrace();
                        callback.onError(new NetworkError(e));
                    }

                    @Override
                    public void onNext(FirmApiResponse model) {
                        Log.e("on", "onNext");
                        list.add(model);
                    }
                });
        api2.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnUnsubscribe(() -> {
                    Log.e("hh", "jjj");
                })
                .subscribe(new Subscriber<FirmApiResponse>() {
                    @Override
                    public void onCompleted() {
                        Log.e("on", "onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("on", "onError");
                        e.printStackTrace();
                        callback.onError(new NetworkError(e));
                    }

                    @Override
                    public void onNext(FirmApiResponse model) {
                        Log.e("on", "onNext");
                        list.add(model);
                        callback.onSuccess(list);
                    }
                });

        return null;
//        Observable<List<FirmApiResponse>> result =
//                Observable.zip(api1.subscribeOn(Schedulers.io()), api2.subscribeOn(Schedulers
//                        .io()), new Func2<FirmApiResponse, FirmApiResponse, List<FirmApiResponse>>() {
//                    @Override
//                    public List<FirmApiResponse> call(FirmApiResponse firmApiResponse, FirmApiResponse firmApiResponse2) {
//                        List<FirmApiResponse> list = new ArrayList<>();
//                        list.add(firmApiResponse);
//                        list.add(firmApiResponse2);
//                        return list;
//                    }
//
//                });


//        return result
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .doOnUnsubscribe(() -> { Log.e("hh","jjj");})
//                .subscribe(new Subscriber<List<FirmApiResponse>>() {
//                    @Override
//                    public void onCompleted() {
//                        Log.e("on", "onCompleted");
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Log.e("on", "onError");
//                        e.printStackTrace();
//                        callback.onError(new NetworkError(e));
//                    }
//
//                    @Override
//                    public void onNext(List<FirmApiResponse> model) {
//                        Log.e("on", "onNext");
//                        callback.onSuccess(model);
//                    }
//                });
    }

    public Observable<FirmApiResponse> uploadImage(final File file) {
        File img = new File(ImageUtils.compressImage(file.getAbsolutePath()));
        RequestBody reqFile = RequestBody.create(MediaType.parse("image/*"), img);
        MultipartBody.Part body1 = MultipartBody.Part.createFormData("image", img.getName(), reqFile);
        return networkService.upload( body1);
    }

    public Subscription submitJob(final ServiceCallback callback, final CreateEntryModel createEntryModel) {

        return networkService.submitJob(createEntryModel)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnUnsubscribe(() -> { Log.e("hh","jjj");})
                .subscribe(new Subscriber<FirmApiResponse>() {
                    @Override
                    public void onCompleted() {
                        Log.e("on", "onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("on", "onError");
                        e.printStackTrace();
                        callback.onError(new NetworkError(e));
                    }

                    @Override
                    public void onNext(FirmApiResponse model) {
                        Log.e("on", "onNext");
                        callback.onSuccess(model);
                    }
                });
    }

    public interface ServiceCallback{
        void onSuccess(FirmApiResponse responseBody);
        void onError(NetworkError networkError);
    }

    public interface ServiceCallbackForImages{
        void onSuccess(List<FirmApiResponse> responseBody);
        void onError(NetworkError networkError);
    }
}
