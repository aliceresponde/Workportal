package com.bizagi.app.workportal.db.entities;

import com.bizagi.app.workportal.db.WorkPortalDataBase;
import com.google.gson.annotations.SerializedName;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by alice on 7/8/16.
 * ============================================================================
 * {
 * "activityId": "5",
 * "activity": "Approval",
 * "processId": "1",
 * "process": "Vacations",
 * "requestDate": "2013-02-28",
 * "employee": "Francesc Fabregas",
 * "beginDate": "2013-12-23",
 * "endDate": "2014-01-02",
 * "lastVacationOn": "2012-09-01",
 * "approved": ""
 * }
 * ============================================================================
 */
@Table(database = WorkPortalDataBase.class)
public class Vacation extends BaseModel {

    public static final String STATUS_ACCEPTED = "accepted ";
    public static final String STATUS_REJECTED = "rejected ";
    public static final String STATUS_NONE = "";

    @SerializedName("activityId")
    @PrimaryKey
    private String activityId;

    @SerializedName("activity")
    @Column
    private String activity;

    @SerializedName("processId")
    @Column
    private String processId;

    @SerializedName("process")
    @Column
    private String process;

    @SerializedName("employee")
    @Column
    private String employee;

    @SerializedName("beginDate")
    @Column
    private String beginDate;


    @SerializedName("requestedDays")
    private String requestedDays;

    @SerializedName("endDate")
    @Column
    private String endDate;

    public String getRequestedDays() {
        return requestedDays;
    }

    public void setRequestedDays(String requestedDays) {
        this.requestedDays = requestedDays;
    }

    @SerializedName("lastVacationOn")
    @Column
    private String lastVacationOn;

    @SerializedName("approved")
    @Column
    private String approved;

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getProcessId() {
        return processId;
    }

    public void setProcessId(String processId) {
        this.processId = processId;
    }

    public String getProcess() {
        return process;
    }

    public void setProcess(String process) {
        this.process = process;
    }

    public String getEmployee() {
        return employee;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getLastVacationOn() {
        return lastVacationOn;
    }

    public void setLastVacationOn(String lastVacationOn) {
        this.lastVacationOn = lastVacationOn;
    }

    public String getApproved() {
        return approved;
    }

    public void setApproved(String approved) {
        this.approved = approved;
    }


}
