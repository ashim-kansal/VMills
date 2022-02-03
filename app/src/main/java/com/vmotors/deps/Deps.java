package com.vmotors.deps;


import com.vmotors.networking.NetworkModule;
import com.vmotors.ui.SplashActivity;
import com.vmotors.ui.create_entry.CreateEntryActivity;
import com.vmotors.ui.create_entry.SelectDeliveryTypeActivity;
import com.vmotors.ui.enroll.login.LoginActivity;
import com.vmotors.ui.order_details.OderDetailActivity;
import com.vmotors.ui.order_details.PreviousWeightActivity;
import com.vmotors.ui.order_details.TotalBillActivity;
import com.vmotors.ui.select_comodity.SelectComodityActivity;
import com.vmotors.ui.select_firm.SelectFirmActivity;
import com.vmotors.ui.tracking.TrackingActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {NetworkModule.class, ApplicationModule.class})
public interface Deps {
 void inject(LoginActivity loginActivity);
 void inject(TrackingActivity trackingActivity);
 void inject(SplashActivity trackingActivity);
 void inject(OderDetailActivity oderDetailActivity);
 void inject(CreateEntryActivity createEntryActivity);
 void inject(SelectFirmActivity selectFirmActivity);
 void inject(SelectComodityActivity selectComodityActivity);
 void inject(SelectDeliveryTypeActivity selectDeliveryTypeActivity);
 void inject(PreviousWeightActivity previousWeightActivity);
 void inject(TotalBillActivity totalBillActivity);
}
