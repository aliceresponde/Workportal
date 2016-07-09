package com.bizagi.app.workportal.db;


import com.bizagi.app.workportal.db.entities.Vacation;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alice on 7/8/16.
 */
public class RequestVacationResponse {
    @SerializedName("vacation")
    @Expose
    private List<Vacation> vacationList = new ArrayList<Vacation>();

    public List<Vacation> getVacationList() {
        return vacationList;
    }

    public void setVacationList(List<Vacation> vacationList) {
        this.vacationList = vacationList;
    }
}
