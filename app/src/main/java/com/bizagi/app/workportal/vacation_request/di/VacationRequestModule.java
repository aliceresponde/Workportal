package com.bizagi.app.workportal.vacation_request.di;

import com.bizagi.app.workportal.vacation_request.VacationRequestInteractor;
import com.bizagi.app.workportal.vacation_request.VacationRequestInteractorImp;
import com.bizagi.app.workportal.vacation_request.VacationRequestPresenter;
import com.bizagi.app.workportal.vacation_request.VacationRequestPresenterImp;
import com.bizagi.app.workportal.vacation_request.VacationRequestRepository;
import com.bizagi.app.workportal.vacation_request.VacationRequestRepositoryImp;
import com.bizagi.app.workportal.vacation_request.ui.VacationRequestView;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by alice on 7/9/16.
 */
@Module
public class VacationRequestModule {
    VacationRequestView view;

    public VacationRequestModule(VacationRequestView view) {
        this.view = view;
    }

    //    ================VacationRequestPresenter=========================
    @Provides @Singleton
    VacationRequestPresenter providesVacationRequestPresente(EventBus eventBus, VacationRequestView view, VacationRequestInteractor interactor){
       return new VacationRequestPresenterImp(eventBus, view ,interactor);
    }

    @Provides @Singleton
    VacationRequestView providesVacationRequestView(){
        return this.view;
    }

    @Provides @Singleton
    VacationRequestInteractor providesVacationRequestInteractor(VacationRequestRepository repository){
        return  new VacationRequestInteractorImp(repository);
    }

    @Provides @Singleton
    VacationRequestRepository providesVacationRequestRepository(com.bizagi.app.workportal.libs.EventBus eventBus){
        return  new VacationRequestRepositoryImp(eventBus);
    }
}
