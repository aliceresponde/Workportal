package com.bizagi.app.workportal.splash;

import android.util.Log;

import com.bizagi.app.workportal.splash.events.SplashEvent;
import com.bizagi.app.workportal.splash.ui.SplashView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * Created by alice on 7/8/16.
 */
public class SplashPresenterImp implements  SplashPresenter{

    private EventBus eventBus;
    private SplashView view;
    private SplashInteractor interactor;

    public SplashPresenterImp(EventBus eventBus, SplashView view, SplashInteractor interactor) {
        this.eventBus = eventBus;
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void onCreate() {
        eventBus.register(this);
    }

    @Override
    public void onDestroy() {
        eventBus.unregister(this);
        view  = null;
    }

    @Override
    @Subscribe
    public void onEvnetMainThread(SplashEvent event) {
        if (view != null) {
            Log.d("SplashPresenterImp", "onEvnetMainThread" + event.getType() + "");
            int type = event.getType();
            if (type == SplashEvent.DOWNLOAD_EVENT) {
                if (event.getVacationList()!= null) {
                    view.downloadDataSuccess();
                }else{
                    view.downloadDataError("No data from server");
                }
            } else {
                view.downloadDataError("");
            }
        }
    }

    @Override
    public void getData() {
        Log.d("SplashPresenterImp", "getData");
        if (view != null) {
            view.showProgress();
        }
      interactor.getData();
    }

    @Override
    public SplashView getView() {
        return this.view;
    }
}
