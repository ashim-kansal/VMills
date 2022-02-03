package com.vmotors.ui.tracking;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.vmotors.BaseApp;
import com.vmotors.R;
import com.vmotors.databinding.ActivityTrackingBinding;
import com.vmotors.domain.request.JobModel;
import com.vmotors.storage.PreferencesApplicationStorage;
import com.vmotors.ui.enroll.login.LoginActivity;
import com.vmotors.utils.AppConstant;

import java.util.List;

import javax.inject.Inject;

public class TrackingActivity extends BaseApp implements TrackingView{

    @Inject
    TrackingPresenter trackingPresenter;

    private ActivityTrackingBinding binding;
    PreferencesApplicationStorage mApplicationStorage;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getDeps().inject(this);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_tracking);
        trackingPresenter.onBind(this);
        mApplicationStorage = new PreferencesApplicationStorage(getApplication().getSharedPreferences("vmotors_prefs", Context.MODE_PRIVATE));

        if (ActivityCompat.checkSelfPermission(TrackingActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(TrackingActivity.this, new
                    String[]{Manifest.permission.CAMERA}, 100);
        }

        if (ActivityCompat.checkSelfPermission(TrackingActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(TrackingActivity.this, new
                    String[]{Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE}, 100);
        }

        setSupportActionBar(binding.toolbar);

        trackingPresenter.getAssignedJobList();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.action_settings){
            if(mApplicationStorage != null){
                mApplicationStorage.clearValue(AppConstant.USER_ID);
                mApplicationStorage.clearValue(AppConstant.LOGIN_TIME);
            }
            startActivity(new Intent(TrackingActivity.this, LoginActivity.class));
            finish();
        }
        return true;
    }

    @Override
    protected void onStop() {
        super.onStop();
        trackingPresenter.onStop();
    }

    @Override
    public void onFailure(String appErrorMessage) {

    }

    @Override
    public void onSuccess(List<JobModel> orderInoviceResponse) {
        JobAdapter courseAdapter = new JobAdapter(this, orderInoviceResponse);

        // below line is for setting a layout manager for our recycler view.
        // here we are creating vertical list so we will provide orientation as vertical
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        // in below two lines we are setting layoutmanager and adapter to our recycler view.
        binding.idRVCourse.setLayoutManager(linearLayoutManager);
        binding.idRVCourse.setAdapter(courseAdapter);
    }


}