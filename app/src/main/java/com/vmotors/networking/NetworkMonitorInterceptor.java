package com.vmotors.networking;

import androidx.annotation.NonNull;

import java.io.IOException;

import javax.inject.Inject;

import okhttp3.Interceptor;
import okhttp3.Response;

public class NetworkMonitorInterceptor implements Interceptor {

    private final LiveNetworkMonitor mLiveNetworkMonitor;


    @Inject
    public NetworkMonitorInterceptor(LiveNetworkMonitor liveNetworkMonitor) {
        this.mLiveNetworkMonitor = liveNetworkMonitor;
    }

    @Override
    public Response intercept(@NonNull final Chain chain) throws IOException {
        if (mLiveNetworkMonitor.isConnected()) {
            return chain.proceed(chain.request());
        } else {
            throw new NoNetworkException();
        }
    }
}