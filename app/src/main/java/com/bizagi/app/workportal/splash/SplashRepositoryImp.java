package com.bizagi.app.workportal.splash;

import android.util.Log;

import com.bizagi.app.workportal.api.APIService;
import com.bizagi.app.workportal.db.RequestVacationResponse;
import com.bizagi.app.workportal.db.entities.Vacation;
import com.bizagi.app.workportal.splash.events.SplashEvent;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by alice on 7/8/16.
 */
public class SplashRepositoryImp implements SplashRepository {
    private APIService service;
    private EventBus eventBus;

    public SplashRepositoryImp(APIService service, EventBus eventBus) {
        this.service = service;
        this.eventBus = eventBus;
    }

    @Override
    public void executeGetData() {
        Log.d("SplashRepositoryImp" , "executeGetData");
        Call<RequestVacationResponse> call = service.get();
        Callback<RequestVacationResponse> callback = new Callback<RequestVacationResponse>() {

            @Override
            public void onResponse(Call<RequestVacationResponse> call, Response<RequestVacationResponse> response) {
                Log.d("****","Response");
                if (response.isSuccessful()){
                    RequestVacationResponse getResponse = response.body();
                    if (getResponse.getVacationList().size() == 0 ){
                        post("", SplashEvent.DOWNLOAD_EVENT, null );
                    }else {
                        for (Vacation vacation : getResponse.getVacationList() ){
                            vacation.save();
                        }
                        post(null, SplashEvent.DOWNLOAD_EVENT,  getResponse.getVacationList() );
                    }
                }
            }

            @Override
            public void onFailure(Call<RequestVacationResponse> call, Throwable t) {
                Log.d("****","ResponseFail" );
                post( t.getLocalizedMessage(), SplashEvent.DOWNLOAD_EVENT, null );
            }
        };

        call.enqueue(callback);
    }


    private  void  post (String error, int eventType, List<Vacation> vacationList){
        Log.d("SplashRepositoryImp", "post ");

        SplashEvent event = new SplashEvent();
        event.setType(eventType);
        event.setError(error);
        event.setVacationList(vacationList);
        eventBus.post(event);
    }
}
