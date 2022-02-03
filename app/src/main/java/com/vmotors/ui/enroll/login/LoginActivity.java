package com.vmotors.ui.enroll.login;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.databinding.DataBindingUtil;

import com.vmotors.BaseApp;
import com.vmotors.R;
import com.vmotors.databinding.ActivityLoginBinding;
import com.vmotors.domain.request.LoginRequestModel;
import com.vmotors.domain.request.LoginResponseModel;
import com.vmotors.ui.create_entry.CreateEntryActivity;
import com.vmotors.ui.tracking.TrackingActivity;

import javax.inject.Inject;

public class LoginActivity extends BaseApp implements LoginView{

    private ActivityLoginBinding binding;

    @Inject
    LoginPresenter loginPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getDeps().inject(this);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);

        loginPresenter.onBind(this);
        binding.tvLogin.setEnabled(true);


        binding.tvLogin.getButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    LoginRequestModel loginRequestModel = new LoginRequestModel();
                    loginRequestModel.setUsername(binding.tvEmail.getText());
                    loginRequestModel.setPassword(binding.tvPassword.getText());
                    binding.tvLogin.setLoadingVisible(true);
                    loginPresenter.loginUser(loginRequestModel);
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        loginPresenter.onStop();
    }

    @Override
    public void onFailure(String appErrorMessage) {
        binding.tvLogin.setLoadingVisible(false);
        binding.tvPassword.setError("Please check the password");
        binding.tvEmail.setError("Please check your email");

        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
        builder.setTitle(appErrorMessage);
        builder.setCancelable(false);
        builder.setPositiveButton("ok", (dialogInterface, i) -> {
           dialogInterface.dismiss();
        });
        builder.create().show();
    }

    @Override
    public void onRegisterSuccess(LoginResponseModel loginResponseModel) {
        binding.tvLogin.setLoadingVisible(false);
            Intent intent = new Intent(LoginActivity.this, CreateEntryActivity.class);
            startActivity(intent);
            finish();
    }


    @Override
    public void setLoginDetails(String email, String password, boolean isRemember) {
        if(isRemember){
            binding.tvEmail.getEditText().setText(email);
            binding.tvPassword.getEditText().setText(password);
        }
    }
}
