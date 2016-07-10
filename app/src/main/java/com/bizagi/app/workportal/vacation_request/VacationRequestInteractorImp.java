package com.bizagi.app.workportal.vacation_request;

/**
 * Created by alice on 7/9/16.
 */
public class VacationRequestInteractorImp  implements VacationRequestInteractor{

     VacationRequestRepository repository;

     public VacationRequestInteractorImp(VacationRequestRepository repository) {
          this.repository = repository;
     }

     @Override
     public void createVacationRequest(String beginDate, String endDate, String requestedDays, int nVactionSaved, String employeeName) {
          repository.createVacationRequest(beginDate, endDate, requestedDays, nVactionSaved, employeeName);
     }
}
