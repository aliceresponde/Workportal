package com.bizagi.app.workportal.vacation_request.di;

import com.bizagi.app.workportal.libs.di.LibsModule;
import com.bizagi.app.workportal.vacation_request.VacationRequestPresenter;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by alice on 7/9/16.
 */
@Singleton
@Component (modules = {VacationRequestModule.class, LibsModule.class})
public interface VacationRequestComponent {
    VacationRequestPresenter getPresenter();
}
