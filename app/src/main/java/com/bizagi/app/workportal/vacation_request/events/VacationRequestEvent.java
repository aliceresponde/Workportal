package com.bizagi.app.workportal.vacation_request.events;

import com.bizagi.app.workportal.db.entities.Vacation;

/**
 * Created by alice on 7/9/16.
 */
public class VacationRequestEvent {


    Vacation vacation;
    String error;

    public VacationRequestEvent() {

    }

    public Vacation getVacation() {
        return vacation;
    }

    public void setVacation(Vacation vacation) {
        this.vacation = vacation;
    }
}
