package com.vmotors.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;

import com.vmotors.BaseApp;
import com.vmotors.R;
import com.vmotors.storage.ApplicationStorage;
import com.vmotors.storage.PreferencesApplicationStorage;
import com.vmotors.ui.create_entry.CreateEntryActivity;
import com.vmotors.ui.enroll.login.LoginActivity;
import com.vmotors.ui.order_details.OderDetailActivity;
import com.vmotors.ui.tracking.TrackingActivity;
import com.vmotors.utils.AppConstant;

import java.util.Calendar;

import javax.inject.Inject;

public class SplashActivity extends BaseApp {


    PreferencesApplicationStorage mApplicationStorage;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        mApplicationStorage = new PreferencesApplicationStorage(getApplication().getSharedPreferences("vmotors_prefs", Context.MODE_PRIVATE));
        new Handler().postDelayed(
                new Runnable() {
                    @Override
                    public void run() {
                        if(!mApplicationStorage.getString(AppConstant.LOGIN_TIME, "").equals(""+Calendar.getInstance().get(Calendar.DAY_OF_MONTH))) {
                            startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                        }else{
                            startActivity(new Intent(SplashActivity.this, CreateEntryActivity.class));
                        }
                        finish();

                    }
                }, 3000
        );
    }
}
