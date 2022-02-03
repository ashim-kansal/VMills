package com.vmotors.ui.select_comodity;

import android.content.Intent;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.vmotors.BaseApp;
import com.vmotors.R;
import com.vmotors.databinding.ActivitySelectFirmBinding;
import com.vmotors.domain.request.CreateEntryModel;
import com.vmotors.domain.request.Firm;
import com.vmotors.domain.request.FirmApiResponse;
import com.vmotors.ui.order_details.OderDetailActivity;
import com.vmotors.ui.select_firm.FirmAdapter;
import com.vmotors.ui.select_firm.SelectFirmActivity;
import com.vmotors.ui.select_firm.SelectFirmPresenter;
import com.vmotors.ui.select_firm.SelectFirmView;
import com.vmotors.utils.OnItemClickListener;

import javax.inject.Inject;

public class SelectComodityActivity extends BaseApp implements SelectFirmView {

    private ActivitySelectFirmBinding binding;
    private CreateEntryModel createEntryModel;

    @Inject
    SelectFirmPresenter selectFirmPresenter;
    private ComodityAdapter adapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getDeps().inject(this);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_select_firm);
        selectFirmPresenter.onBind(this);
        createEntryModel = (CreateEntryModel) getIntent().getSerializableExtra("modal");
        selectFirmPresenter.getCommodities();
    }

    @Override
    public void onFailure(String appErrorMessage) {

    }

    @Override
    public void onSuccess(FirmApiResponse firmApiResponse) {
        adapter = new ComodityAdapter(firmApiResponse.getData().getCommodities(), SelectComodityActivity.this, new OnItemClickListener() {
            @Override
            public void onItemClick(Firm item) {
                createEntryModel.setCommodityId(item.getId());
                Intent i = new Intent(SelectComodityActivity.this, OderDetailActivity.class);
                i.putExtra("modal", createEntryModel);
                startActivity(i);
            }
        });
        LinearLayoutManager manager = new LinearLayoutManager(this);
        binding.idRVCourses.setHasFixedSize(true);
        binding.idRVCourses.setLayoutManager(manager);
        binding.idRVCourses.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        selectFirmPresenter.onStop();
    }
}