package com.bizagi.app.workportal;

import android.app.Application;

import com.bizagi.app.workportal.libs.di.LibsModule;
import com.bizagi.app.workportal.splash.di.DaggerSplashComponent;
import com.bizagi.app.workportal.splash.di.SplashComponent;
import com.bizagi.app.workportal.splash.di.SplashModule;
import com.bizagi.app.workportal.splash.ui.SplashActivity;
import com.bizagi.app.workportal.splash.ui.SplashView;
import com.raizlabs.android.dbflow.config.FlowManager;

/**
 * Created by alice on 7/8/16.
 */
public class App extends Application {
    public static final  String SP_WASLOADED = "loaded";
    private static final  String SHARED_PREFERENCES_NAME = "LocalPrefs";
    @Override
    public void onCreate() {
        super.onCreate();
        initDB();
    }

    private void  initDB() {
        FlowManager.init(this);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        DBTearDown();
    }

    private void DBTearDown() {
        FlowManager.destroy();
    }

    public static String getSharedPreferencesWasloaded() {
        return SP_WASLOADED;
    }

    public String getSharedPreferencesName() {
        return SHARED_PREFERENCES_NAME;
    }


    public SplashComponent getSplashComponent(SplashActivity activity, SplashView view){
        return DaggerSplashComponent
                .builder()
                .libsModule(new LibsModule(activity))
                .splashModule(new SplashModule(view))
                .build();
    }
}
