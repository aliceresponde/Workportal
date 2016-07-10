package com.bizagi.app.workportal.vacation_request;

import com.bizagi.app.workportal.vacation_request.events.VacationRequestEvent;
import com.bizagi.app.workportal.vacation_request.ui.VacationRequestView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * Created by alice on 7/9/16.
 */
public class VacationRequestPresenterImp implements  VacationRequestPresenter {
    private EventBus eventBus;
    private VacationRequestView view;
    private VacationRequestInteractor interactor;


    public VacationRequestPresenterImp(EventBus eventBus, VacationRequestView view, VacationRequestInteractor interactor) {
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
        view  = null;
        eventBus.unregister(this);
    }

    @Subscribe
    @Override
    public void onEvnetMainThread(VacationRequestEvent event) {
        view.hideProgress();
        view.hideProgress();
        view.cleanForm();
        view.goBack( event.getVacation() );
    }

    @Override
    public VacationRequestView getView() {
        return null;
    }

    @Override
    public void createVaacationRequest(String beginDate, String endDate, String requestedDays, int nVactionSaved, String employeeName) {
        interactor.createVacationRequest(beginDate,endDate,requestedDays,nVactionSaved, employeeName);
    }
}
