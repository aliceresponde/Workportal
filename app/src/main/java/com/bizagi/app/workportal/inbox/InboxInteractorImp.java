package com.bizagi.app.workportal.inbox;

/**
 * Created by alice on 7/9/16.
 */
public class InboxInteractorImp implements InboxInteractor {
    private  InboxRepository repository;

    public InboxInteractorImp(InboxRepository repository) {
        this.repository = repository;
    }

    @Override
    public void executeGetVactionsRequest() {
        repository.getVacationsRequest();
    }
}
