package com.vmotors.ui.order_details;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Toast;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.databinding.DataBindingUtil;

import com.canhub.cropper.CropImage;
import com.canhub.cropper.CropImageContract;
import com.canhub.cropper.CropImageView;
import com.vmotors.A;
import com.vmotors.BaseApp;
import com.vmotors.R;
import com.vmotors.databinding.ActivityFactoryUnloadingBinding;
import com.vmotors.domain.request.CreateEntryModel;
import com.vmotors.domain.request.FirmApiResponse;
import com.vmotors.ui.create_entry.CreateEntryActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.inject.Inject;

public class PreviousWeightActivity extends BaseApp implements OrderDetailView {

    @Inject
    OrderDetailPresenter orderDetailPresenter;
    File invardSlip = null;
    File kantaSlip = null;

    private CreateEntryModel createEntryModel;
    private ActivityFactoryUnloadingBinding binding;
    private int type = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getDeps().inject(this);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_factory_unloading);

        if (getIntent() != null && getIntent().hasExtra("modal")) {
            createEntryModel = (CreateEntryModel) getIntent().getSerializableExtra("modal");
        }

        orderDetailPresenter.onBind(this);

        binding.tvUpload.getButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if (binding.tvInvardNo.getText().length() == 0) {
//                    Toast.makeText(PreviousWeightActivity.this, "Please enter Invard Number", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                if (binding.tvKantaSlipNo.getText().length() == 0) {
//                    Toast.makeText(PreviousWeightActivity.this, "Please enter Kanta Slip Number", Toast.LENGTH_SHORT).show();
//                    return;
//                }

                if(invardSlip == null){
                    Toast.makeText(PreviousWeightActivity.this, "Please select Invard Slip", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(kantaSlip == null){
                    Toast.makeText(PreviousWeightActivity.this, "Please select Kanta Slip", Toast.LENGTH_SHORT).show();
                    return;
                }
                binding.tvUpload.setLoadingVisible(true);
                orderDetailPresenter.uploadEntry(invardSlip, 0);

            }
        });

        binding.ivSlip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickImageFromCamera(0);
            }
        });

        binding.ivKantaSlip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickImageFromCamera(1);
            }
        });


    }

    @Override
    public void onFailure(String appErrorMessage) {
        binding.tvUpload.setLoadingVisible(false);
        showDialog(appErrorMessage, 0);
    }

    private void showDialog(String appErrorMessage, int i) {
        AlertDialog.Builder alrt = new AlertDialog.Builder(PreviousWeightActivity.this);
        alrt.setCancelable(false);
        alrt.setTitle("Alert");
        alrt.setMessage(appErrorMessage);
        alrt.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int x) {
                dialogInterface.dismiss();
                if (i == 1) {
                    orderDetailPresenter.createEntry(createEntryModel);
                }
            }
        });
        alrt.create().show();
    }


    @Override
    public void onUploadSuccess(FirmApiResponse responseBody, int type) {
        if(type == 0) {
            createEntryModel.setPreviousSlip(responseBody.getData().getImage());
            orderDetailPresenter.uploadEntry(kantaSlip, 1);
        }else {
            createEntryModel.setKantaSlip(responseBody.getData().getImage());
            onImagesSuccess();
        }
    }

    @Override
    public void onSuccessJob(FirmApiResponse responseBody) {
        startActivity(new Intent(PreviousWeightActivity.this, CreateEntryActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
        finish();
    }

    private void pickImageFromCamera(int type) {
        this.type = type;

        if (ActivityCompat.checkSelfPermission(PreviousWeightActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(PreviousWeightActivity.this, "Please provide camera permission to proceed further", Toast.LENGTH_LONG).show();
            ActivityCompat.requestPermissions(PreviousWeightActivity.this, new
                    String[]{Manifest.permission.CAMERA}, 1000);
        } else {
            (new A()).openCam(cropImage);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1000) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "camera permission granted", Toast.LENGTH_LONG).show();
                (new A()).openCam(cropImage);
            } else {
                Toast.makeText(this, "camera permission denied", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    private ActivityResultLauncher cropImage = this.registerForActivityResult(new CropImageContract(), new ActivityResultCallback<CropImageView.CropResult>() {
        @Override
        public void onActivityResult(CropImageView.CropResult result) {
            if(result.isSuccessful()){
                if(type == 0){
                    invardSlip =  new File(result.getUriFilePath(PreviousWeightActivity.this, true));
                    binding.ivSlip.setImageURI(result.getUriContent());
                }else{
                    kantaSlip =  new File(result.getUriFilePath(PreviousWeightActivity.this, true));
                    binding.ivKantaSlip.setImageURI(result.getUriContent());
                }
            }
        }
    });


    public void onImagesSuccess() {
        binding.tvUpload.setLoadingVisible(false);
        if(binding.tvInvardNo.getText().length() > 0)
            createEntryModel.setPreviousSlipNo(Integer.parseInt(binding.tvInvardNo.getText().toString()));
        createEntryModel.setKantaSlipNo(binding.tvKantaSlipNo.getText().toString());
        showDialog("Images Uploaded successfully", 1);
    }
}