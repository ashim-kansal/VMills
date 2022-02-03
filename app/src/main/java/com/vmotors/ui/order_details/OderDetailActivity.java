package com.vmotors.ui.order_details;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.InputFilter;
import android.view.View;
import android.widget.DatePicker;
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
import com.vmotors.databinding.ActivityOderDetailBinding;
import com.vmotors.domain.request.CreateEntryModel;
import com.vmotors.domain.request.FirmApiResponse;
import com.vmotors.ui.create_entry.CreateEntryActivity;
import com.vmotors.utils.DecimalDigitsInputFilter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

public class OderDetailActivity extends BaseApp implements OrderDetailView {

    @Inject
    OrderDetailPresenter orderDetailPresenter;
    private File invardSlip = null;
    private File billSlip = null;
    private int type = 0;
    private Calendar myCalendar;

    private CreateEntryModel createEntryModel;
    private ActivityOderDetailBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getDeps().inject(this);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_oder_detail);

        if (getIntent() != null && getIntent().hasExtra("modal")) {
            createEntryModel = (CreateEntryModel) getIntent().getSerializableExtra("modal");
        }

        orderDetailPresenter.onBind(this);

        binding.title.setText("Upload Current Weight Receipt");

        binding.tvGrossQuantity.getEditText().setFilters(new InputFilter[] {new DecimalDigitsInputFilter(5,2)});
        binding.tvTareQuantity.getEditText().setFilters(new InputFilter[] {new DecimalDigitsInputFilter(5,2)});
        binding.tvNetQuantity.getEditText().setFilters(new InputFilter[] {new DecimalDigitsInputFilter(5,2)});


        binding.tvTareQuantity.getEditText().setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(binding.tvGrossQuantity.getText().length()==0) {
                    Toast.makeText(OderDetailActivity.this, "Please enter Gross Weight", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(binding.tvTareQuantity.getText().length() > 0){
                    BigDecimal d1 = BigDecimal.valueOf(Double.parseDouble(binding.tvGrossQuantity.getText()));
                    BigDecimal d2 = BigDecimal.valueOf(Double.parseDouble(binding.tvTareQuantity.getText()));
                    binding.tvNetQuantity.getEditText().setText(""+(d1.subtract(d2)));
                }
            }
        });
        myCalendar = Calendar.getInstance();

        binding.tvNetQuantity.getEditText().setEnabled(false);
        DatePickerDialog.OnDateSetListener date =new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH,month);
                myCalendar.set(Calendar.DAY_OF_MONTH,day);
                binding.tvDate.getEditText().setText(myCalendar.get(Calendar.YEAR)+"-"+(myCalendar.get(Calendar.MONTH)+1)+"-"+myCalendar.get(Calendar.DAY_OF_MONTH));
            }
        };
        binding.tvDate.getEditText().setFocusable(false);
        binding.tvDate.getEditText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(OderDetailActivity.this,date,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        binding.tvUpload.getButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if (binding.tvBillNo.getText().length() == 0) {
//                    Toast.makeText(OderDetailActivity.this, "Please enter Invoice Number", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                if (binding.tvGrossQuantity.getText().length() == 0) {
//                    Toast.makeText(OderDetailActivity.this, "Please enter Gross Weight", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                if (binding.tvTareQuantity.getText().length() == 0) {
//                    Toast.makeText(OderDetailActivity.this, "Please enter Tare Weight", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                if (binding.tvNetQuantity.getText().length() == 0) {
//                    Toast.makeText(OderDetailActivity.this, "Please enter Net Weight", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                if (binding.tvbags.getText().length() == 0) {
//                    Toast.makeText(OderDetailActivity.this, "Please enter No. Of Bags", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                if (binding.tvTruckNo.getText().length() == 0) {
//                    Toast.makeText(OderDetailActivity.this, "Please enter truck number", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                if (binding.tvDate.getText().length() == 0) {
//                    Toast.makeText(OderDetailActivity.this, "Please enter the entry Date", Toast.LENGTH_SHORT).show();
//                    return;
//                }
                if(invardSlip == null){
                    Toast.makeText(OderDetailActivity.this, "Please select Kanta Slip", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(billSlip == null){
                    Toast.makeText(OderDetailActivity.this, "Please select a Bill", Toast.LENGTH_SHORT).show();
                    return;
                }

                binding.tvUpload.setLoadingVisible(true);
                orderDetailPresenter.uploadEntry(invardSlip, 0);

            }
        });

        binding.ivKantaSlip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickImageFromCamera(0);
            }
        });

        binding.ivBillSlip.setOnClickListener(new View.OnClickListener() {
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
        AlertDialog.Builder alrt = new AlertDialog.Builder(OderDetailActivity.this);
        alrt.setCancelable(false);
        alrt.setTitle("Alert");
        alrt.setMessage(appErrorMessage);
        alrt.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int x) {
                dialogInterface.dismiss();
                if (i == 1) {

                    if(createEntryModel.getDeliveryType().equals("FOR")){
                        orderDetailPresenter.createEntry(createEntryModel);
                    }else {
                        Intent i1 = new Intent(OderDetailActivity.this, PreviousWeightActivity.class);
                        i1.putExtra("modal", createEntryModel);
                        startActivity(i1);
                    }
                }
            }
        });
        alrt.create().show();
    }

    @Override
    public void onUploadSuccess(FirmApiResponse responseBody, int type) {
        if(type == 0) {
            createEntryModel.setCurrentSlip(responseBody.getData().getImage());
            orderDetailPresenter.uploadEntry(billSlip, 1);
        }else {
            createEntryModel.setBill(responseBody.getData().getImage());
            onImagesSuccess();
        }
    }

    @Override
    public void onSuccessJob(FirmApiResponse responseBody) {
        startActivity(new Intent(OderDetailActivity.this, CreateEntryActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
        finish();
    }

    private void pickImageFromCamera(int type) {
        this.type = type;

        if (ActivityCompat.checkSelfPermission(OderDetailActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(OderDetailActivity.this, "Please provide Storage permission to proceed further", Toast.LENGTH_LONG).show();
            ActivityCompat.requestPermissions(OderDetailActivity.this, new
                    String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 1001);
            return;
        }

        if (ActivityCompat.checkSelfPermission(OderDetailActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(OderDetailActivity.this, "Please provide camera permission to proceed further", Toast.LENGTH_LONG).show();
            ActivityCompat.requestPermissions(OderDetailActivity.this, new
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
//                CropImage.activity().start(this);
                (new A()).openCam(cropImage);
            } else {
                Toast.makeText(this, "camera permission denied", Toast.LENGTH_LONG).show();
            }
        }
    }

    private ActivityResultLauncher  cropImage = this.registerForActivityResult(new CropImageContract(), new ActivityResultCallback<CropImageView.CropResult>() {
        @Override
        public void onActivityResult(CropImageView.CropResult result) {
            if(result.isSuccessful()){
                if(type == 0){
                        invardSlip =  new File(result.getUriFilePath(OderDetailActivity.this, true));
                        binding.ivKantaSlip.setImageURI(result.getUriContent());
                }else{
                        billSlip =  new File(result.getUriFilePath(OderDetailActivity.this, true));
                        binding.ivBillSlip.setImageURI(result.getUriContent());
                }
            }
        }
    });

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
        }
    }


    public void onImagesSuccess() {
        binding.tvUpload.setLoadingVisible(false);
        if(binding.tvGrossQuantity.getText().length() > 0)
            createEntryModel.setCurrentGrossWeight(Double.parseDouble(binding.tvGrossQuantity.getText().toString()));
        if(binding.tvTareQuantity.getText().length() > 0)
            createEntryModel.setCurrentTareWeight(Double.parseDouble(binding.tvTareQuantity.getText().toString()));
        if(binding.tvNetQuantity.getText().length() > 0)
            createEntryModel.setCurrentNetWeight(Double.parseDouble(binding.tvNetQuantity.getText().toString()));
        if(binding.tvKantaSlipNo.getText().length() > 0)
            createEntryModel.setCurrentSlipNo(Integer.parseInt(binding.tvKantaSlipNo.getText().toString()));
        createEntryModel.setNoofbags(binding.tvbags.getText().toString());
        createEntryModel.setTruckNo(binding.tvTruckNo.getText().toString());
        createEntryModel.setCurrentDate(binding.tvDate.getText().toString());
        createEntryModel.setBillNo(binding.tvBillNo.getText().toString());
        createEntryModel.setUserId(orderDetailPresenter.getUserId());

        showDialog("Images Uploaded successfully", 1);
    }
}