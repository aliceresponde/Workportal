package com.bizagi.app.workportal.splash;

import android.util.Log;

/**
 * Created by alice on 7/8/16.
 */
public class SplashInteractorImp implements SplashInteractor {
    private SplashRepository repository;

    public SplashInteractorImp(SplashRepository repository) {
        this.repository = repository;
    }

    @Override
    public void getData() {
        Log.d("SplashInteractorImp", "getData");
        repository.executeGetData();
    }
}
