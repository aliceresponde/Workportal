package com.bizagi.app.workportal.splash;

import com.bizagi.app.workportal.splash.events.SplashEvent;
import com.bizagi.app.workportal.splash.ui.SplashView;

/**
 * Created by alice on 7/8/16.
 */
public interface SplashPresenter {

    //    ==============================Events======================

    void onCreate();
    void onDestroy();

    void onEvnetMainThread(SplashEvent event);

    //    ==============================API======================
    void getData();

    //    ==============================VIEW======================

    SplashView getView();
}
