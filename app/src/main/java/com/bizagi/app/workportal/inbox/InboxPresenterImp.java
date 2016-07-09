package com.bizagi.app.workportal.inbox;

import com.bizagi.app.workportal.db.entities.Vacation;
import com.bizagi.app.workportal.inbox.events.InboxEvent;
import com.bizagi.app.workportal.inbox.ui.InboxView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

/**
 * Created by alice on 7/9/16.
 */
public class InboxPresenterImp  implements  InboxPresenter{
    private InboxView view;
    private EventBus eventBus;
    private InboxInteractor interactor;

    public InboxPresenterImp(InboxView view, EventBus eventBus, InboxInteractor interactor) {
        this.view = view;
        this.eventBus = eventBus;
        this.interactor = interactor;
    }

    @Override
    public void onCreate() {
        eventBus.register(this);
    }

    @Override
    public void onDestroy() {
        eventBus.unregister(this);
        view = null;
    }

    @Override
    public void getVactionsRequest() {
        view.showProgressBar();
        interactor.executeGetVactionsRequest();
    }

    @Subscribe
    @Override
    public void onEventMainThread(InboxEvent event) {
        view.hiddeProgressBar();

        List<Vacation> requestedVacationList = event.getRequestedVacationList();
        if (requestedVacationList != null){
            view.setVacationsRequest( requestedVacationList);
        }else{
            view.showError(event.getError());
        }
    }

    @Override
    public InboxView getView() {
        return view;
    }

}
