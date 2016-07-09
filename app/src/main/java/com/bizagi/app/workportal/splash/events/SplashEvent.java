package com.bizagi.app.workportal.splash.events;

import com.bizagi.app.workportal.db.entities.Vacation;

import java.util.List;

/**
 * Created by alice on 7/8/16.
 */
public class SplashEvent {
    private int type;
    private String error;
    private List<Vacation> vacationList;
    public final static  int DOWNLOAD_EVENT = 0;


    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<Vacation> getVacationList() {
        return vacationList;
    }

    public void setVacationList(List<Vacation> vacationList) {
        this.vacationList = vacationList;
    }
}
