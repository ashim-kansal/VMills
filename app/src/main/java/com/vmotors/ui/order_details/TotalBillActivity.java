package com.vmotors.ui.order_details;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.databinding.DataBindingUtil;

import com.vmotors.BaseApp;
import com.vmotors.R;
import com.vmotors.databinding.ActivityOderDetailBinding;
import com.vmotors.domain.request.CreateEntryModel;
import com.vmotors.domain.request.FirmApiResponse;
import com.vmotors.ui.create_entry.CreateEntryActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;

import javax.inject.Inject;

public class TotalBillActivity extends BaseApp implements OrderDetailView {

    @Inject
    OrderDetailPresenter orderDetailPresenter;
    File f = null;
    int count = 0;

    private CreateEntryModel createEntryModel;
    private ActivityOderDetailBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getDeps().inject(this);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_oder_detail);


    }

    @Override
    protected void onStop() {
        super.onStop();
//        orderDetailPresenter.onStop();
    }

    @Override
    public void onFailure(String appErrorMessage) {
        binding.tvUpload.setLoadingVisible(false);
        showDialog(appErrorMessage, 0);
    }

    private void showDialog(String appErrorMessage, int i) {
        AlertDialog.Builder alrt = new AlertDialog.Builder(TotalBillActivity.this);
        alrt.setCancelable(false);
        alrt.setTitle("Alert");
        alrt.setMessage(appErrorMessage);
        alrt.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int x) {
                dialogInterface.dismiss();
                if(i==1){
                    orderDetailPresenter.createEntry(createEntryModel);
                }
//                OderDetailActivity.this.finish();
            }
        });
        alrt.create().show();
    }

    public void onSuccess(FirmApiResponse responseBody) {
        binding.tvUpload.setLoadingVisible(false);
        if(responseBody.getStatus()==200){
            createEntryModel.setBill(responseBody.getData().getImage());
            createEntryModel.setBillNo(binding.tvBillNo.getText().toString());
            createEntryModel.setUserId(orderDetailPresenter.getUserId());

        }
        showDialog("Entry Uploaded successfully", 1);
    }

    @Override
    public void onSuccessJob(FirmApiResponse responseBody) {
        startActivity(new Intent(TotalBillActivity.this, CreateEntryActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
        finish();
    }

    private void pickImageFromCamera(){
        if (ActivityCompat.checkSelfPermission(TotalBillActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(TotalBillActivity.this, "Please provide camera permission to proceed further", Toast.LENGTH_LONG).show();
            ActivityCompat.requestPermissions(TotalBillActivity.this, new
                    String[]{Manifest.permission.CAMERA}, 1000  );
        }else {
            Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(cameraIntent, 100);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1000)
        {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                Toast.makeText(this, "camera permission granted", Toast.LENGTH_LONG).show();
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, 100);
            }
            else
            {
                Toast.makeText(this, "camera permission denied", Toast.LENGTH_LONG).show();
            }
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode,resultCode,data);
        if (requestCode == 100 && resultCode == Activity.RESULT_OK)
        {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
//            binding.ivSlip.setImageBitmap(photo);
            f = savebitmap(photo);
        }
    }

    private File savebitmap(Bitmap bmp) {
        String extStorageDirectory = Environment.getExternalStorageDirectory().toString();
        OutputStream outStream = null;
        // String temp = null;
        File file = new File(extStorageDirectory, "temp.jpg");
        if (file.exists()) {
            file.delete();
            file = new File(extStorageDirectory, "temp.jpg");

        }

        try {
            outStream = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, outStream);
            outStream.flush();
            outStream.close();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return file;
    }

    @Override
    public void onUploadSuccess(FirmApiResponse responseBody, int type) {

    }

    public void onImagesSuccess(List<FirmApiResponse> body) {

    }
}