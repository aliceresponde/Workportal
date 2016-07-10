package com.bizagi.app.workportal.vacation_request;

/**
 * Created by alice on 7/9/16.
 */
public interface VacationRequestRepository {
    void createVacationRequest(String beginDate, String endDate, String requestedDays, int nVactionSaved, String employeeName);
}
