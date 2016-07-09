package com.bizagi.app.workportal.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by alice on 7/8/16.
 */
public class APIClient {
    private Retrofit retrofit;
    private final static  String BASE_URL = "http://private-830d8-andre32.apiary-mock.com/";

    public APIClient() {
        this.retrofit = new Retrofit.Builder()
                            .baseUrl(BASE_URL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
    }


    public  APIService getAPIService(){
        return  this.retrofit.create(APIService.class);
    }
}
