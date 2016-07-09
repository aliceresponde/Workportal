package com.bizagi.app.workportal.splash.di;

import com.bizagi.app.workportal.api.APIClient;
import com.bizagi.app.workportal.api.APIService;
import com.bizagi.app.workportal.splash.SplashInteractor;
import com.bizagi.app.workportal.splash.SplashInteractorImp;
import com.bizagi.app.workportal.splash.SplashPresenter;
import com.bizagi.app.workportal.splash.SplashPresenterImp;
import com.bizagi.app.workportal.splash.SplashRepository;
import com.bizagi.app.workportal.splash.SplashRepositoryImp;
import com.bizagi.app.workportal.splash.ui.SplashView;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by alice on 7/8/16.
 */
@Module
public class SplashModule {
    SplashView view;

    public SplashModule(SplashView view) {
        this.view = view;
    }


    @Provides @Singleton
    SplashView providesSplashView(){
        return  this.view;
    }

//    =====================================PRESENTER=======================================================

    @Provides  @Singleton
    SplashPresenter providesSplashPresenter(EventBus eventBus, SplashView view, SplashInteractor interactor){
        return  new SplashPresenterImp(eventBus, view , interactor);
    }


    @Provides @Singleton
    SplashInteractor providesSplashInteractor(SplashRepository repository){
        return  new SplashInteractorImp(repository);
    }

    @Provides @Singleton
    SplashRepository providesSplashRepository(APIService service, EventBus eventBus){
        return  new SplashRepositoryImp(service, eventBus);
    }


    @Provides @Singleton
    APIService providesAPIService(){
        return  new APIClient().getAPIService();
    }



}
