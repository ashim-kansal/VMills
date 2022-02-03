package com.vmotors.ui.select_firm;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;

import com.vmotors.BaseApp;
import com.vmotors.R;
import com.vmotors.databinding.ActivitySelectFirmBinding;
import com.vmotors.domain.request.CreateEntryModel;
import com.vmotors.domain.request.Firm;
import com.vmotors.domain.request.FirmApiResponse;
import com.vmotors.ui.select_comodity.SelectComodityActivity;
import com.vmotors.utils.OnItemClickListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.inject.Inject;

public class SelectFirmActivity extends BaseApp implements SelectFirmView{

    private ActivitySelectFirmBinding binding;
    private CreateEntryModel createEntryModel;

    @Inject
    SelectFirmPresenter selectFirmPresenter;
    private FirmAdapter adapter;
    ArrayList<Firm> arrayList;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getDeps().inject(this);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_select_firm);
        selectFirmPresenter.onBind(this);
        createEntryModel = (CreateEntryModel) getIntent().getSerializableExtra("modal");
        selectFirmPresenter.getFirms();
    }

    @Override
    public void onFailure(String appErrorMessage) {

    }

    @Override
    public void onSuccess(FirmApiResponse firmApiResponse) {
        arrayList = firmApiResponse.getData().getFirms();
        setAdapter();
    }

    private void setAdapter() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            arrayList.sort(new Comparator<Firm>() {
                @Override
                public int compare(Firm firm, Firm t1) {
                    return firm.getFirm_name().compareTo(t1.getFirm_name());
                }
            });
        }else{
            Collections.sort(arrayList, new Comparator<Firm>() {
                @Override
                public int compare(Firm firm, Firm t1) {
                    return firm.getFirm_name().compareTo(t1.getFirm_name());
                }
            });
        }
        adapter = new FirmAdapter(arrayList, SelectFirmActivity.this, new OnItemClickListener() {
            @Override
            public void onItemClick(Firm item) {
                createEntryModel.setFirmId(item.getId());
                Intent i = new Intent(SelectFirmActivity.this, SelectComodityActivity.class);
                i.putExtra("modal", createEntryModel);
                startActivity(i);
            }
        });
        LinearLayoutManager manager = new LinearLayoutManager(this);
        binding.idRVCourses.setHasFixedSize(true);
        binding.idRVCourses.setLayoutManager(manager);
        binding.idRVCourses.setAdapter(adapter);
        binding.tvSearch.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,             int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.getFilter().filter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        selectFirmPresenter.onStop();
    }
}