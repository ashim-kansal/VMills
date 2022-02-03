package com.vmotors.ui.order_details;

import com.vmotors.domain.request.FirmApiResponse;
import com.vmotors.domain.request.JobModel;
import com.vmotors.domain.request.UploadEntryResponse;

import java.util.List;

import okhttp3.ResponseBody;

public interface OrderDetailView {

    void onFailure(String appErrorMessage);

    void onSuccessJob(FirmApiResponse responseBody);

    void onUploadSuccess(FirmApiResponse responseBody, int type);
}
