package com.vmotors;

import android.app.Application;

import com.vmotors.navigation.AppNavigator;
import com.vmotors.navigation.NavigationRouter;

public class VMotorsApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        setupNavigation();
    }

    private void setupNavigation() {
        NavigationRouter.registerNavigator(new AppNavigator());

    }
}
