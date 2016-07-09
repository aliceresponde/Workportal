package com.bizagi.app.workportal.api;

import com.bizagi.app.workportal.db.RequestVacationResponse;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by alice on 7/8/16.
 */
public interface APIService {

    @GET("requests")
    Call<RequestVacationResponse> get();
}
