package com.bizagi.app.workportal;

import android.app.Application;

import com.bizagi.app.workportal.inbox.di.DaggerInboxComponent;
import com.bizagi.app.workportal.inbox.di.InboxComponent;
import com.bizagi.app.workportal.inbox.di.InboxModules;
import com.bizagi.app.workportal.inbox.ui.InboxActivity;
import com.bizagi.app.workportal.inbox.ui.InboxView;
import com.bizagi.app.workportal.inbox.ui.adapters.OnItemClickListener;
import com.bizagi.app.workportal.libs.di.LibsModule;
import com.bizagi.app.workportal.splash.di.DaggerSplashComponent;
import com.bizagi.app.workportal.splash.di.SplashComponent;
import com.bizagi.app.workportal.splash.di.SplashModule;
import com.bizagi.app.workportal.splash.ui.SplashActivity;
import com.bizagi.app.workportal.splash.ui.SplashView;
import com.bizagi.app.workportal.vacation_request.di.DaggerVacationRequestComponent;
import com.bizagi.app.workportal.vacation_request.di.VacationRequestComponent;
import com.bizagi.app.workportal.vacation_request.di.VacationRequestModule;
import com.bizagi.app.workportal.vacation_request.ui.VacationRequestActivity;
import com.bizagi.app.workportal.vacation_request.ui.VacationRequestView;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.crashlytics.android.Crashlytics;
import io.fabric.sdk.android.Fabric;

/**
 * Created by alice on 7/8/16.
 */
public class App extends Application {
    public static final  String SP_WASLOADED = "loaded";
    private static final  String SHARED_PREFERENCES_NAME = "LocalPrefs";
    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());
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

    public InboxComponent getInboxComponente(InboxActivity activity, InboxView view, OnItemClickListener clickListener){
        return DaggerInboxComponent
                .builder()
                .inboxModules(new InboxModules(view,activity, clickListener))
                .libsModule(new LibsModule(activity))
                .build();
    }

    public VacationRequestComponent getRequestVacationComponet(VacationRequestActivity activity, VacationRequestView view) {
        return DaggerVacationRequestComponent
                .builder()
                .libsModule( new LibsModule(activity))
                .vacationRequestModule(new VacationRequestModule(view))
                .build();
    }
}
