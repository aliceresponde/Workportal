package com.bizagi.app.workportal.vacation_request;

import com.bizagi.app.workportal.vacation_request.events.VacationRequestEvent;
import com.bizagi.app.workportal.vacation_request.ui.VacationRequestView;

/**
 * Created by alice on 7/9/16.
 */
public interface VacationRequestPresenter {
    void onCreate();
    void onDestroy();
    void onEvnetMainThread(VacationRequestEvent event);

    VacationRequestView getView();
    void createVaacationRequest(String beginDate, String endDate, String requestedDays, int nVactionSaved, String employeeName);
}
