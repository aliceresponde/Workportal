package com.bizagi.app.workportal.inbox.ui;

import com.bizagi.app.workportal.db.entities.Vacation;

import java.util.List;

/**
 * Created by alice on 7/9/16.
 */
public interface InboxView {
    void  showProgressBar();
    void  hiddeProgressBar();


    void setVacationsRequest(List<Vacation> requestedVacationList);
    void showError( String error);
}

