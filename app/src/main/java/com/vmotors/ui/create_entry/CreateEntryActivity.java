package com.vmotors.ui.create_entry;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.databinding.DataBindingUtil;

import com.vmotors.BaseApp;
import com.vmotors.R;
import com.vmotors.databinding.ActivityCreateEntryBinding;
import com.vmotors.domain.request.CreateEntryModel;
import com.vmotors.ui.select_firm.SelectFirmActivity;

public class CreateEntryActivity extends BaseApp {

    private ActivityCreateEntryBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getDeps().inject(this);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_create_entry);

        binding.tvIn.getButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CreateEntryModel createEntryModel = new CreateEntryModel();
                createEntryModel.setEntryType("IN");
                Intent i = new Intent(CreateEntryActivity.this, SelectDeliveryTypeActivity.class);
                i.putExtra("modal", createEntryModel);
                startActivity(i);
            }
        });

        binding.tvOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CreateEntryModel createEntryModel = new CreateEntryModel();
                createEntryModel.setEntryType("OUT");
                Intent i = new Intent(CreateEntryActivity.this, SelectDeliveryTypeActivity.class);
                i.putExtra("modal", createEntryModel);
                startActivity(i);
            }
        });
    }
}