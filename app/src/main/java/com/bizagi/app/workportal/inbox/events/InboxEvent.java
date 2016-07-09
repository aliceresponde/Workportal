package com.bizagi.app.workportal.inbox.events;

import com.bizagi.app.workportal.db.entities.Vacation;

import java.util.List;

/**
 * Created by alice on 7/9/16.
 */
public class InboxEvent {
    private int type;
    private List<Vacation> requestedVacationList;
    private String error ;

    public final static  int READ_EVENT = 0;
    public final static  int UPDATE_EVENT = 1;


    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<Vacation> getRequestedVacationList() {
        return requestedVacationList;
    }

    public void setRequestedVacationList(List<Vacation> requestedVacationList) {
        this.requestedVacationList = requestedVacationList;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
