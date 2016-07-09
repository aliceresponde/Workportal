package com.bizagi.app.workportal.splash.ui;

/**
 * Created by alice on 7/8/16.
 */
public interface SplashView {

    void downloadDataSuccess();
    void downloadDataError(String error);

    void navigationToInboxScreen();

    void showProgress();
    void hideProgress();
}
