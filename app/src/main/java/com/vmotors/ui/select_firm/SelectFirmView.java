package com.vmotors.ui.select_firm;

import com.vmotors.domain.request.FirmApiResponse;

public interface SelectFirmView {

    void onFailure(String appErrorMessage);

    void onSuccess(FirmApiResponse firmApiResponse);

}
