package com.bizagi.app.workportal.splash.di;

import com.bizagi.app.workportal.libs.di.LibsModule;
import com.bizagi.app.workportal.splash.SplashPresenter;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by alice on 7/8/16.
 */
@Singleton
@Component(modules = { SplashModule.class, LibsModule.class})
public interface SplashComponent {
    SplashPresenter getPresenter ();
}
