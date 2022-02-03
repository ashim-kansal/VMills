package com.vmotors.networking;

import com.vmotors.domain.request.CreateEntryModel;
import com.vmotors.domain.request.FirmApiResponse;
import com.vmotors.domain.request.JobModel;
import com.vmotors.domain.request.LoginRequestModel;
import com.vmotors.domain.request.LoginResponseModel;
import com.vmotors.domain.request.UploadEntryResponse;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

public interface NetworkService {

    @POST("/api/auth")
    Observable<LoginResponseModel> login(@Body LoginRequestModel loginRequestModel);

    @POST("/api/jobs/submitJob")
    Observable<FirmApiResponse> submitJob(@Body CreateEntryModel createEntryModel);

    @GET("/api/jobs")
    Observable<List<JobModel>> getAssignedJobs(@Query("userId") int id);

    @GET("/api/common/firms")
    Observable<FirmApiResponse> getFirms(@Query("searchText") String text);

    @GET("/api/common/commodities")
    Observable<FirmApiResponse> getCommodities();


    @Multipart
    @POST("/api/common/uploadImage")
    Observable<FirmApiResponse> upload(
            @Part MultipartBody.Part file
            );

    @Multipart
    @POST("/api/common/uploadImage")
    Observable<UploadEntryResponse> uploadImage(
            @Part MultipartBody.Part file
            );

}
