package com.bizagi.app.workportal.vacation_request.ui;

import com.bizagi.app.workportal.db.entities.Vacation;

/**
 * Created by alice on 7/9/16.
 */
public interface VacationRequestView {
    void  showProgress();
    void  hideProgress();

    void disableUI();
    void enableUI();

    void cleanForm();

    void showFormError(String error);

    void showProcessError(String error);

    void  goBack(Vacation vacation);

    void showBeginDateError(String s);

    void showEndDateError(String s);

    void showRequestedDaysError(String s);

}
