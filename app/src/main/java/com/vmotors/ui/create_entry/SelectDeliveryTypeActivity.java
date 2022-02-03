package com.vmotors.ui.create_entry;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.vmotors.BaseApp;
import com.vmotors.R;
import com.vmotors.databinding.ActivitySelectDeliveryTypeBinding;
import com.vmotors.domain.request.CreateEntryModel;
import com.vmotors.ui.select_firm.SelectFirmActivity;

public class SelectDeliveryTypeActivity extends BaseApp {

    private ActivitySelectDeliveryTypeBinding binding;
    private CreateEntryModel createEntryModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getDeps().inject(this);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_select_delivery_type);

        createEntryModel = (CreateEntryModel) getIntent().getSerializableExtra("modal");
        binding.tvFor.getButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createEntryModel.setDeliveryType("FOR");
                Intent i = new Intent(SelectDeliveryTypeActivity.this, SelectFirmActivity.class);
                i.putExtra("modal", createEntryModel);
                startActivity(i);
            }
        });

        binding.tvEx.getButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createEntryModel.setDeliveryType("Ex-Mill");
                Intent i = new Intent(SelectDeliveryTypeActivity.this, SelectFirmActivity.class);
                i.putExtra("modal", createEntryModel);
                startActivity(i);
            }
        });
    }
}