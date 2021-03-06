package com.vmotors;

import android.content.Context;
import androidx.multidex.MultiDex;


public class Application extends android.app.Application {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
