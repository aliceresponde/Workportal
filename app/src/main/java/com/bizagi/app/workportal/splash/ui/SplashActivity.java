package com.bizagi.app.workportal.splash.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.bizagi.app.workportal.App;
import com.bizagi.app.workportal.R;
import com.bizagi.app.workportal.inbox.ui.InboxActivity;
import com.bizagi.app.workportal.splash.SplashPresenter;
import com.bizagi.app.workportal.splash.di.SplashComponent;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashActivity extends AppCompatActivity implements SplashView {

    @BindView(R.id.cordinator)
    RelativeLayout cordinator;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    private App app;
    private boolean wasDataLoaded = false;
    SharedPreferences sharedPreferences;

    private SplashPresenter presenter;
    private SplashComponent component; // letMe  use injection

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        setupInjection();
        wasDataLoaded = sharedPreferences.getBoolean(app.SP_WASLOADED, false);
        Log.d("================",wasDataLoaded +" wasDataLoaded" );

        presenter.onCreate();
        if (!wasDataLoaded) {
            presenter.getData();
        }else {
            navigationToInboxScreen();
        }
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    private void setupInjection() {
        app = (App) getApplication();
        sharedPreferences = getSharedPreferences(app.getSharedPreferencesName(), MODE_PRIVATE);
        component = app.getSplashComponent(this, this);
        presenter = getPresenter();
    }


    @Override
    public void downloadDataSuccess() {
        hideProgress();
        sharedPreferences.edit().putBoolean(App.SP_WASLOADED, true).commit();
        Snackbar.make(cordinator, getString(R.string.splash_api_success_message) , Snackbar.LENGTH_SHORT).show();
        navigationToInboxScreen();
    }

    @Override
    public void downloadDataError(String error) {
        hideProgress();
        Snackbar.make(cordinator, error , Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void navigationToInboxScreen() {
        startActivity(new Intent(this, InboxActivity.class));
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    public SplashPresenter getPresenter() {
        return component.getPresenter();
    }
}
