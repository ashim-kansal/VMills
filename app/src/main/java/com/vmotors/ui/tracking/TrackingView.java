package com.vmotors.ui.tracking;

import com.vmotors.domain.request.JobModel;

import java.util.List;

public interface TrackingView {

    void onFailure(String appErrorMessage);

    void onSuccess(List<JobModel> loginResponseModel);
}
