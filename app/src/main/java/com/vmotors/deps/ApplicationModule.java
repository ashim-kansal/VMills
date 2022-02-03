package com.vmotors.deps;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.vmotors.networking.LiveNetworkMonitor;
import com.vmotors.storage.ApplicationStorage;
import com.vmotors.storage.PreferencesApplicationStorage;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    private final Application application;

    public ApplicationModule(final Application application) {
        this.application = application;
    }

    @Provides
    Application provideApplication() {
        return application;
    }

    @Provides
    Context provideContext() {
        return application;
    }

    @Provides
    @Singleton
    SharedPreferences provideSharedPreferences() {
        return application.getSharedPreferences("vmotors_prefs", Context.MODE_PRIVATE);
    }

    @Provides
    @Singleton
    LiveNetworkMonitor provideLiveNetworkMonitor(){
        return new LiveNetworkMonitor(application);
    }


    @Provides
    @Singleton
    ApplicationStorage provideApplicationStorage(final SharedPreferences sharedPreferences) {
        return new PreferencesApplicationStorage(sharedPreferences);
    }


}
