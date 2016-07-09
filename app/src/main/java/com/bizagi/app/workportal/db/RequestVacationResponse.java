package com.bizagi.app.workportal.db;


import com.bizagi.app.workportal.db.entities.Vacation;

import java.util.List;

/**
 * Created by alice on 7/8/16.
 */
public class RequestVacationResponse {
    private List<Vacation> vacationList;

    public List<Vacation> getVacationList() {
        return vacationList;
    }

    public void setVacationList(List<Vacation> vacationList) {
        this.vacationList = vacationList;
    }
}
