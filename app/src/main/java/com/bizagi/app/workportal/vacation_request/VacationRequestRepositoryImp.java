package com.bizagi.app.workportal.vacation_request;

import com.bizagi.app.workportal.db.entities.Vacation;
import com.bizagi.app.workportal.libs.EventBus;
import com.bizagi.app.workportal.vacation_request.events.VacationRequestEvent;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by alice on 7/9/16.
 */
public class VacationRequestRepositoryImp implements  VacationRequestRepository {

    EventBus eventBus;

    public VacationRequestRepositoryImp(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    void post(Vacation vacation){
        VacationRequestEvent event = new VacationRequestEvent();
        event.setVacation(vacation);
        eventBus.post(event);
    }

    @Override
    public void createVacationRequest(String beginDate, String endDate, String requestedDays, int nVactionSaved, String employeeName) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String strToday = dateFormat.format(new Date());

        Vacation nVacation = new Vacation();

        nVacation.setProcessId(Vacation.K_PROCESS_ID);
        nVacation.setProcess(Vacation.K_PROCESS);
        nVacation.setActivityId(String.valueOf(nVactionSaved + 1));
        nVacation.setProcessId(Vacation.K_ACTIVITY);
        nVacation.setRequestDate(strToday);
        nVacation.setEmployee(employeeName);
        nVacation.setBeginDate(beginDate);
        nVacation.setEndDate(endDate);
        nVacation.setLastVacationOn(Vacation.K_LAST_VACATION_ON);
        nVacation.setApproved("");
        nVacation.save();

        post(nVacation);
    }
}
